package com.app.cleartv;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by Dell on 3/4/2018.
 */

public class CommonMethods {

    /**
     *
     * @param context Activity context to be passed
     * @param drawable the drawable file that is to be converted
     * @param color a string color parameter to be passed
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

}
