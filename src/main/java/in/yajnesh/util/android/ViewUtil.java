package in.yajnesh.util.android;

/*
This file is part of AndroidUtils.

AndroidUtils is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

Foobar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with AndroidUtils. If not, see <https://www.gnu.org/licenses/>.

(ɔ) Yajnesh T
*/

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * General utils related to views
 * <p>
 *
 * @author Yajnesh T
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class ViewUtil {

    private static int sScreenWidth;
    private static int sScreenHeight;

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float dpToPx(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * (float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into dp
     * @return A float value to represent dp equivalent to px value
     */
    public static float pxToDp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * This method converts device specific pixels to scale independent pixels.
     *
     * @param sp A value in sp unit. Which we need to convert into pixel
     * @return A float value to represent sp equivalent to px value
     */
    public static float spToPx(float sp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                sp, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into dp
     * @return A float value to represent dp equivalent to px value
     */
    public static float pxToSp(float px) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return px / Resources.getSystem().getDisplayMetrics().scaledDensity;
    }


    /**
     * Compute Screen width and height and save it statically, call this once from application class<br/>
     * access this value by <br/>
     * {@link ViewUtil#getScreenWidth()} and {@link ViewUtil#getScreenHeight()}
     *
     * @param c Context
     */
    public static void computeCommonParams(Context c) {
        sScreenWidth = computeScreenWidth(c);
        sScreenHeight = computeScreenHeight(c);
    }

    /**
     * Compute the screen width
     *
     * @param c Context
     * @return width in pixels
     */
    private static int computeScreenWidth(Context c) {
        try {
            return getPoint(c).x;
        } catch (Throwable e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Compute the screen height
     *
     * @param c Context
     * @return width in height
     */
    private static int computeScreenHeight(Context c) {
        try {
            return getPoint(c).y;
        } catch (Throwable e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get point for screen dimension calculation
     *
     * @param c Context
     * @return point which has screen dimension info
     */
    private static Point getPoint(Context c) {
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            return size;
        } else {
            return new Point();
        }
    }

    /**
     * Get the screen width (Make sure you have called {@link ViewUtil#computeCommonParams(Context)} at-least once before invoking this
     *
     * @return the screen width
     */
    public static int getScreenWidth() {
        return sScreenWidth;
    }

    /**
     * Get the screen height (Make sure you have called {@link ViewUtil#computeCommonParams(Context)} at-least once before invoking this
     *
     * @return the screen height
     */
    public static int getScreenHeight() {
        return sScreenHeight;
    }

    /**
     * Open keyboard
     *
     * @param v with respect to this view
     */
    public static void openKeyBoard(View v) {
        if (v == null) {
            return;
        }
        try {
            v.clearFocus();
            v.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.toggleSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
            }
        } catch (Throwable e) {
            //Not the end of the world. Keyboard didn't show up, that's it.
            e.printStackTrace();
        }
    }

    /**
     * Hide keyboard, the view is automatically detected from the activity
     *
     * @param a Activity
     */
    public static void hideKeyboard(Activity a) {
        try {
            if (a == null) {
                return;
            }
            View view = a.getCurrentFocus();
            if (view == null) {
                return;
            }
            view.clearFocus();
            InputMethodManager inputManager = (InputMethodManager) a.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

        } catch (Throwable e) {
            //Not the end of the world. Keyboard didn't hide, that's it.
            e.printStackTrace();
        }
    }

    /**
     * Hide keyboard, the view is automatically detected from the activity
     *
     * @param f Fragment
     */
    public static void hideKeyboard(Fragment f) {
        if (f == null) {
            return;
        }
        hideKeyboard(f.getActivity());
    }

    /**
     * Hide keyboard
     *
     * @param view with respect to this view
     */
    public static void hideKeyboard(View view) {
        // Check if no view has focus:
        try {
            if (view != null && view.getContext() != null) {
                view.clearFocus();
                InputMethodManager inputManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputManager != null) {
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        } catch (Throwable e) {
            //Not the end of the world. Keyboard didn't hide, that's it.
            e.printStackTrace();
        }
    }

    /**
     * Safely remove global listener set to view depending on OS version
     *
     * @param v        The view from which the global listener to be removed
     * @param listener The listener to be removed
     */
    public static void removeGlobalLayoutListener(View v, ViewTreeObserver.OnGlobalLayoutListener listener) {
        if (v == null || v.getViewTreeObserver() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            v.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
        } else {
            //noinspection deprecation , os version checked
            v.getViewTreeObserver().removeGlobalOnLayoutListener(listener);
        }
    }

    /**
     * Get LinearLayout params out of view
     *
     * @param v view
     * @return LinearLayout params
     */
    @NonNull
    public static LinearLayout.LayoutParams getLinearLayoutParams(View v) {
        LinearLayout.LayoutParams genericParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (v == null) {
            return genericParam;
        }
        ViewGroup.LayoutParams viewParams = v.getLayoutParams();
        if (viewParams instanceof LinearLayout.LayoutParams) {
            return (LinearLayout.LayoutParams) viewParams;
        }

        return genericParam;
    }

    /**
     * Get RelativeLayout params out of view
     *
     * @param v view
     * @return RelativeLayout params
     */
    public static RelativeLayout.LayoutParams getRelativeLayoutParams(View v) {
        RelativeLayout.LayoutParams genericParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (v == null) {
            return genericParam;
        }
        ViewGroup.LayoutParams viewParams = v.getLayoutParams();
        if (viewParams instanceof RelativeLayout.LayoutParams) {
            return (RelativeLayout.LayoutParams) viewParams;
        }

        return genericParam;
    }

    /**
     * Set visibility of views
     *
     * @param state State of the views to be set, must be one of View.VISIBLE View.INVISIBLE or View.GONE
     * @param views varargs of views to set the state
     */
    public static void setVisibility(/*@android.view.View.Visibility*/ int state, @Nullable View... views) {
        if (GenericUtil.isEmpty(views) ||
                (state != View.VISIBLE && state != View.INVISIBLE && state != View.GONE)
                ) {
            ALog.e("param error");
            return;
        }

        for (View v : views) {
            if (v != null) {
                v.setVisibility(state);
            }
        }
    }

    /**
     * Hides the views by setting the height and weight to zero
     *
     * @param views vararg views to hide
     */
    public static void hideView(View... views) {


        if (GenericUtil.isEmpty(views)) {
            ALog.e("param error");
            return;
        }

        for (View v : views) {
            if (v != null) {
                ViewGroup.LayoutParams lp = v.getLayoutParams();
                if (lp == null) {
                    lp = new ViewGroup.LayoutParams(0, 0);
                } else {
                    lp.height = lp.width = 0;
                }
                v.setLayoutParams(lp);
            }
        }
    }

    /**
     * Dismiss dialogs
     *
     * @param dialogs vararg of dialogs
     */
    public static void dismissDialog(DialogInterface... dialogs) {

        if (GenericUtil.isEmpty(dialogs)) {
            ALog.e("param error");
            return;
        }

        for (DialogInterface d : dialogs) {
            try {
                if (d != null) {
                    d.dismiss();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Set transparency to the dialog window
     *
     * @param window window of the dialog
     */
    public static void setTransparencyToDialog(Window window) {

        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.CENTER;
            window.setAttributes(wlp);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        }
    }
}
