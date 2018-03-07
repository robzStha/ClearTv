package com.app.cleartv;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Robz Stha on 13/11/2017.
 */

public class ViewUtils {
    /**
     * @param context Activity/Fragment context
     * @param message Message to be displayed
     */
    public static void showToastMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method for removing the keyboard if touched outside the editview.
     *
     * @param view     root view
     * @param activity parent activity
     */
    public static void setupUI(final View view, final Activity activity) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    view.clearFocus();
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, activity);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        // Check if no view has focus: and hide the soft keyboard
        View view = activity.getCurrentFocus();
        //Checking if view!=null
        // to prevent java.lang.NullPointerException: Attempt to invoke virtual method 'android.os.IBinder android.view.View.getWindowToken()' on a null object reference
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static boolean handleEditTextInsideScrollView(View v, MotionEvent event) {
        v.getParent().getParent().requestDisallowInterceptTouchEvent(true);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                v.getParent().getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return false;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A int value to represent px equivalent to dp depending on device density
     */
    public static int convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @return A int value to represent dp equivalent to px value
     */
    public static int convertPixelsToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return (int) dp;
    }
}
