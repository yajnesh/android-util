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

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;


/**
 * ProgressDialog utils
 * <p>
 *
 * @author Yajnesh T
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class ProgressUtil {

    private static ProgressDialog sProgressDialog = null;


    /**
     * Safe Progress Dialog
     *
     * @param c            context
     * @param titleResId   title resource id to be shown
     * @param textResId    text resource id to be shown
     * @param isCancelable allow user to dismiss before completion?
     */
    public static void showProgressDialog(Context c, int titleResId, int textResId, boolean isCancelable) {
        if (c == null) {
            ALog.e("Unable to create dialog, context is null");
            return;
        }
        String title = null;
        String text = null;
        try {
            title = c.getString(titleResId);
        } catch (Resources.NotFoundException ignored) {
        }

        try {
            text = c.getString(textResId);
        } catch (Resources.NotFoundException ignored) {
        }
        showProgressDialog(c, title, text, isCancelable);
    }

    /**
     * Safe Progress Dialog
     *
     * @param c          context
     * @param titleResId title resource id to be shown
     * @param textResId  text resource id to be shown
     */
    public static void showProgressDialog(Context c, int titleResId, int textResId) {
        showProgressDialog(c, titleResId, textResId, true);
    }

    /**
     * Safe Progress Dialog
     *
     * @param c context
     */
    public static void showProgressDialog(Context c) {
        showProgressDialog(c, null, null, true);
    }

    /**
     * Safe Progress Dialog
     *
     * @param c     context
     * @param title title to be displayed
     * @param text  text to be displayed
     */
    public static void showProgressDialog(Context c, String title, String text) {
        showProgressDialog(c, title, text, true);
    }

    /**
     * Safe Progress Dialog
     *
     * @param c            context
     * @param title        title to be displayed
     * @param text         text to be displayed
     * @param isCancelable allow user to dismiss?
     */
    public static void showProgressDialog(Context c, String title, String text, boolean isCancelable) {
        try {
            if (c == null) {
                ALog.e("Unable to create dialog, context is null");
                return;
            }

            if (title == null) {
                title = "Please Wait";
            }
            if (text == null) {
                title = "Loading";
            }
            if (sProgressDialog != null && sProgressDialog.isShowing()) {
                sProgressDialog.dismiss();
            }


            sProgressDialog = new ProgressDialog(c);

            sProgressDialog.setTitle(title);
            sProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            sProgressDialog.setMessage(text);
            sProgressDialog.setCancelable(isCancelable);

            sProgressDialog.show();
        } catch (Throwable e) {
            //Gotta catch em all
            e.printStackTrace();
            ALog.e("Unable to showProgressDialog");
        }
    }


    /**
     * Dismiss showing progress dialog
     */
    public static void dismissProgressDialog() {
        try {
            if (sProgressDialog != null && sProgressDialog.isShowing()) {
                sProgressDialog.dismiss();
            }
        } catch (Throwable e) {
            //Gotta catch em all
            e.printStackTrace();
            ALog.e("Unable to dismissProgressDialog");
        }
    }
}
