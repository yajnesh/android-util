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
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.WindowManager;

/**
 * General utils related to activities
 * <p>
 *
 * @author Yajnesh T
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ActivityUtil {

    /**
     * Safe Start activity for result with ActivityOptionsCompacts
     *
     * @param a           current activity
     * @param i           intent to launch
     * @param requestCode requestCode
     * @param options     ActivityOptionsCompat
     */
    public static void startActivityForResult(Activity a,
                                              Intent i,
                                              int requestCode,
                                              ActivityOptionsCompat options) {
        if (a == null) {
            return;
        }
        if (options != null) {
            a.startActivityForResult(
                    i,
                    requestCode,
                    options.toBundle()
            );
        } else {
            a.startActivityForResult(
                    i,
                    requestCode);
        }
    }

    /**
     * Safe Start activity for result with ActivityOptionsCompacts
     *
     * @param f           current fragment
     * @param i           intent to launch
     * @param requestCode requestCode
     * @param options     ActivityOptionsCompat
     */
    public static void startActivityForResult(Fragment f,
                                              Intent i,
                                              int requestCode,
                                              ActivityOptionsCompat options) {
        if (f == null) {
            return;
        }
        if (options != null) {
            f.startActivityForResult(
                    i,
                    requestCode,
                    options.toBundle()
            );
        } else {
            f.startActivityForResult(
                    i,
                    requestCode);
        }
    }

    /**
     * Safe Start activity for result with ActivityOptionsCompacts
     *
     * @param a       current activity
     * @param i       intent to launch
     * @param options ActivityOptionsCompat
     */
    public static void startActivity(Activity a,
                                     Intent i,
                                     ActivityOptionsCompat options) {
        if (a == null) {
            return;
        }
        if (options != null) {
            a.startActivity(i, options.toBundle());
        } else {
            a.startActivity(i);
        }
    }

    /**
     * Get the transition animation between activity for view
     *
     * @param activity current activity
     * @param v        view on which animation is bound
     * @param name     custom name of animation
     * @return ActivityOptionsCompat constructed
     */
    public static ActivityOptionsCompat getTransitionAnimationForProduct(Activity activity, View v, String name) {
        try {
            if (activity == null || v == null) {
                return null;
            }
            ViewCompat.setTransitionName(v, name);

            //noinspection unchecked
            return ActivityOptionsCompat.
                    makeSceneTransitionAnimation(activity,
                            Pair.create(v, ViewCompat.getTransitionName(v))
                    );
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Hide keyboard on launch
     *
     * @param a Activity
     */
    public static void hideKeyBoardOnLaunch(Activity a) {
        if (a != null && a.getWindow() != null) {
            a.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

}
