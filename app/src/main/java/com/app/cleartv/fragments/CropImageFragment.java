package com.app.cleartv.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.cleartv.R;
import com.app.cleartv.utils.AppContract;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;


/**
 * Created by core I5 on 6/26/2017.
 */

public class CropImageFragment extends DialogFragment {
    public static final String TAG = CropImageFragment.class.getSimpleName();
    private CropImageListener mCropImageListener;
    private String mImagePath;
    private TextView tvCrop, tvCancel;
    private CropImageView civMain;

    public static CropImageFragment newInstance(@NonNull String imagePath) {
        CropImageFragment mCropImageFragment = new CropImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(AppContract.Extras.DATA, imagePath);
        mCropImageFragment.setArguments(bundle);
        return mCropImageFragment;
    }

    /**
     * Set the callback
     *
     * @param listener cropImageListener
     */
    public void setCropImageListener(@NonNull CropImageListener listener) {
        this.mCropImageListener = listener;
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == tvCrop.getId()) {
                //Save the cropped image based on the current crop window to the given uri.
                civMain.saveCroppedImageAsync(Uri.fromFile(new File(mImagePath)));
            } else if (v.getId() == tvCancel.getId()) {
                if (getDialog() != null) {
                    dismiss();
                }
            }
        }
    };

    private CropImageView.OnCropImageCompleteListener cropImageCompleteListener = new CropImageView.OnCropImageCompleteListener() {
        @Override
        public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
            if (mCropImageListener == null) {
                throw new IllegalArgumentException("mOnCropImageCompleteListener is not set");
            } else {
                if (!TextUtils.isEmpty(mImagePath)) {
                    mCropImageListener.imageCropCompleted(mImagePath);
                } else {
                    Log.d(TAG, AppContract.Errors.IMAGE_ERROR);
                }
            }
            if (getDialog() != null) {
                dismiss();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        mImagePath = getArguments().getString(AppContract.Extras.DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_cropper, container, false);
        tvCrop = view.findViewById(R.id.tv_crop);
        tvCancel = view.findViewById(R.id.tv_cancel);
        civMain = view.findViewById(R.id.civ_main);

        //Set image from uri in the crop image view
        civMain.setImageUriAsync(Uri.fromFile(new File(mImagePath)));

        tvCancel.setOnClickListener(clickListener);
        tvCrop.setOnClickListener(clickListener);

        //Setting cropImageCompleteListener
        civMain.setOnCropImageCompleteListener(cropImageCompleteListener);

        return view;
    }

    public interface CropImageListener {
        void imageCropCompleted(@NonNull String croppedImagePath);
    }
}
