package com.app.cleartv;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.cleartv.fragments.ImageHelperFragment;
import com.app.cleartv.utils.AppContract;
import com.app.cleartv.utils.CustomAlertDialog;
import com.app.cleartv.utils.FileUtils;
import com.app.cleartv.utils.Payload;
import com.app.cleartv.utils.ViewUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CapturePhotos extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.iv_applicant)
    ImageView iv_applicant;

    @BindView(R.id.iv_box_card)
    ImageView iv_box_card;

    @BindView(R.id.btn_next)
    Button btn_next;

    @BindView(R.id.spn_box_cable_photo)
    Spinner spn_box_cable_photo;

    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_photos);
        ButterKnife.bind(this);
        iv_applicant.setOnClickListener(this);
        iv_box_card.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    ImageView iv_selected;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_applicant:
                iv_selected = iv_applicant;
                accessCamera();
                break;
            case R.id.iv_box_card:
                iv_selected = iv_box_card;
                accessCamera();
                break;
            case R.id.btn_next:
                if (iv_applicant.getDrawable() == null && iv_box_card.getDrawable() == null)
                    CustomAlertDialog.showAlertDialog(this, AppContract.Errors.APPLICANT_BOX_CARD);
                else if (spn_box_cable_photo.getSelectedItemId() == 0 && iv_box_card.getDrawable() == null)
                    CustomAlertDialog.showAlertDialog(this, AppContract.Errors.BOX);
                else if (spn_box_cable_photo.getSelectedItemId() == 1 && iv_box_card.getDrawable() == null)
                    CustomAlertDialog.showAlertDialog(this, AppContract.Errors.CARD);
                else if (iv_applicant.getDrawable() == null)
                    CustomAlertDialog.showAlertDialog(this, AppContract.Errors.APPLICANT);
                else {
                    if(spn_box_cable_photo.getSelectedItemId() == 0){
                        Payload.boxPhoto = encoded;
                        Payload.cardPhoto = "";
                    }else {
                        Payload.cardPhoto = encoded;
                        Payload.boxPhoto = "";
                    }
                    Intent i = new Intent(this, SubscriberApplication.class);
                    CapturePhotos.this.startActivity(i);
                }
                break;
            default:
                break;
        }
    }

    private void accessCamera() {
        if (ActivityCompat.checkSelfPermission(CapturePhotos.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(CapturePhotos.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Request permission to read write external storage and use camera.
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA},
                    AppContract.Permission.CAMERA);
            return;
        }
        openCamera();
    }
//    private ImageHelperFragment imageHelperFragment;
//
//    private void initImageHelperFragment() {
//        imageHelperFragment = ImageHelperFragment.newInstance(true, true);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(imageHelperFragment, ImageHelperFragment.TAG);
//        transaction.commit();
//        imageHelperFragment.setListener(new ImageHelperFragment.ImageHelperListener() {
//            @Override
//            public void onImageSuccess(String imagePath) {
//                Toast.makeText(CapturePhotos.this, "imagePath: "+imagePath, Toast.LENGTH_SHORT).show();
//            }
//        });

//        imageHelperFragment.setListener(imagePath -> {
//            Log.d("path", "onImageSuccess: " + imagePath);
//            Toast.makeText(this, "imagePath: "+imagePath, Toast.LENGTH_SHORT).show();
////            presenter.uploadImage(imagePath);
////            iv_applicant.setImageURI(Uri.parse(imagePath));
////            setProfileImage(imagePath, false);
//        });
//    }


    private void openCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri outputImageUri;
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            final File photoFile = new File(FileUtils.getImageDirectory(), FileUtils.getUniqueImageName());
            imagePath = photoFile.getPath();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputImageUri = FileProvider.getUriForFile(this, getPackageName() + ".provider", photoFile);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                outputImageUri = Uri.fromFile(photoFile);
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputImageUri);
            // Create the File where the photo should go
            startActivityForResult(takePictureIntent, AppContract.RequestCode.CAMERA);
        } else {
            ViewUtils.showToastMessage(this, "No camera app available");
        }
    }
//    private void accessCamera() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            // comment here
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
//        } else CommonMethods.openCamera(CapturePhotos.this, imgName);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == 0) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                CommonMethods.openCamera(CapturePhotos.this, imgName);
//            }
//        }
//    }

//    private static File getOutputMediaFile() {
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "CameraDemo");
//
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                return null;
//            }
//        }
//
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        return new File(mediaStorageDir.getPath() + File.separator +
//                "IMG_" + timeStamp + ".jpg");
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case AppContract.Permission.CAMERA: {
                // If request is cancelled, the results arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0]) && !shouldShowRequestPermissionRationale(permissions[1])) {
                    CustomAlertDialog.showPermissionRequestDialog(CapturePhotos.this, R.string.permission_rationale_camera_storage);
                } else {
                    ViewUtils.showToastMessage(CapturePhotos.this, "You need to grant access to open camera");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case AppContract.RequestCode.CAMERA:
                    sendBackImagePath();
                    break;
            }
        }
    }

    /**
     * Set callback
     *
     * @param listener {@link  ImageHelperFragment.ImageHelperListener}
     */
    public void setListener(@NonNull ImageHelperFragment.ImageHelperListener listener) {
        this.listener = listener;
    }


    private ImageHelperFragment.ImageHelperListener listener;
    private boolean showCropView, compressImage;

    private void sendBackImagePath() {
//        if (listener == null) {
//            throw new IllegalArgumentException("ImageHelperListener is not set");
//        } else {
        if (imagePath != null) {
            imagePath = FileUtils.resizeAndCompressImageBeforeSend(this, imagePath);

            Toast.makeText(this, "Path: " + imagePath, Toast.LENGTH_SHORT).show();
//                if (showCropView) {
            final File imgFile = new File(imagePath);
            final Bitmap[] myBitmap = new Bitmap[1];
            if (imgFile.exists()) {
                new Handler().post(new Runnable() {

                    @Override
                    public void run() {
                        myBitmap[0] = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        myBitmap[0] = manageOrientation(myBitmap[0]);
                        setImageToPayload(myBitmap[0]);
                        iv_selected.setImageBitmap(myBitmap[0]);
                    }
                });
//78329


            }
//                CropImage.activity(Uri.parse(imagePath))
//                        .start(this);

//                CropImageFragment cropImageFragment = CropImageFragment.newInstance(imagePath);
//                cropImageFragment.show(getSupportFragmentManager(), CropImageFragment.TAG);
//                cropImageFragment.setCropImageListener(new CropImageFragment.CropImageListener() {
//                    @Override
//                    public void imageCropCompleted(@NonNull String croppedImagePath) {
//                        if (compressImage) {
//                            imagePath = FileUtils.resizeAndCompressImageBeforeSend(CapturePhotos.this, croppedImagePath);
//                        }
//                        listener.onImageSuccess(imagePath);
//                    }
//                });
//                } else {
//                    if (compressImage) {
//                        imagePath = FileUtils.resizeAndCompressImageBeforeSend(this, imagePath);
//                    }
//                    listener.onImageSuccess(imagePath);
//                }
        } else {
            CustomAlertDialog.showAlertDialog(this, AppContract.Errors.IMAGE_ERROR);
        }
    }
    String encoded;
    private void setImageToPayload(Bitmap myBitmap) {
        encoded = FileUtils.convertBitmapToBase64(myBitmap);
        if (iv_selected == iv_box_card) {
//            Payload.boxCardPhoto = encoded;
            if (spn_box_cable_photo.getSelectedItemId() == 0) Payload.boxPhoto = encoded;
            else Payload.cardPhoto = encoded;
        } else Payload.applicantPhoto = encoded;
    }



    private Bitmap manageOrientation(Bitmap myBitmap) {
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED);

        return rotateBitmap(myBitmap, orientation);
    }

    //    }
    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }

}
