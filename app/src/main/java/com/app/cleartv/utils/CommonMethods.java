package com.app.cleartv.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by Dell on 3/4/2018.
 */

public class CommonMethods {

    /**
     * @param context  Activity context to be passed
     * @param drawable the drawable file that is to be converted
     * @param color    a string color parameter to be passed
     * @return A drawable with color changed
     */
    public static Drawable ChangeDrawableColor(Context context, int drawable, String color) {
        int c = 0xff0000;
        int colorValue = Color.parseColor(color);
        Drawable mDrawable = context.getResources().getDrawable(drawable);
//        mDrawable = DrawableCompat.wrap(mDrawable);
//        DrawableCompat.setTint(mDrawable, Color.GREEN); // Set whatever color you want
//        DrawableCompat.setTintMode(mDrawable, PorterDuff.Mode.SRC_OVER);

        mDrawable.setColorFilter(new PorterDuffColorFilter(colorValue, PorterDuff.Mode.MULTIPLY));
        return mDrawable;
    }



    public static void openCamera(Activity activity, String fileName) {
        String imagePath;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri outputImageUri;
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            final File photoFile = new File(getImageDirectory(), fileName);
            imagePath = photoFile.getPath();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputImageUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", photoFile);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                outputImageUri = Uri.fromFile(photoFile);
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputImageUri);
            // Create the File where the photo should go
            activity.startActivityForResult(takePictureIntent, 100);
        } else {
            Toast.makeText(activity, "No camera available.", Toast.LENGTH_SHORT).show();
        }
    }


    private static final String IMAGE_PREFIX = "img_";
    private static final String IMAGE_POSTFIX = ".jpg";

    /**
     * Get unique filename for photo.
     *
     * @return the unique filename for the photo
     */
    public static String getUniqueImageName() {
        try {
            return File.createTempFile(IMAGE_PREFIX, IMAGE_POSTFIX).getName();
        } catch (IOException e) {
            return IMAGE_PREFIX + System.currentTimeMillis() + IMAGE_POSTFIX;
        }
    }

    public static File getImageDirectory() {
        File filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), File.separator + "CareCareer");

        if (!filePath.exists()) {
            if (!(filePath.mkdirs() || filePath.isDirectory())) {
                return null;
            }
        }
        return filePath;
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Method for removing the keyboard if touched outside the editview.
     *
     * @param view
     * @param baseActivity
     */
    public static void keyboardSetup(View view, final Activity baseActivity) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
//                    baseActivity.closeKeyboard();
                    hideSoftKeyboard(baseActivity);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                keyboardSetup(innerView, baseActivity);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {

        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
