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

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.util.ArraySet;
import android.support.v4.view.PagerAdapter;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.Collection;
import java.util.Map;

import in.yajnesh.util.java.JUtil;


/**
 * Collection of common Generic Util methods
 *
 * @author Yajnesh T
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class GenericUtil extends JUtil {


    /**
     * @param arraySet     The array set
     * @param index        the index at which the item needs to be fetched
     * @param defaultValue in case of array is null or index out of bound, return this value
     * @return item if found, defaultValue otherwise
     */
    /*@Nullable*/
    public static <T> T get(/*@Nullable*/ ArraySet<T> arraySet, int index, /*@Nullable*/ T defaultValue) {
        if (arraySet == null) {
            return defaultValue;
        }

        if (index < 0 || index >= GenericUtil.size(arraySet)) {
            return defaultValue;
        }
        return arraySet.valueAt(index);
    }

    /**
     * @param arraySet The array set
     * @param index    the index at which the item needs to be fetched
     * @return item if found, null otherwise
     */
    /*@Nullable*/
    public static <T> T get(/*@Nullable*/ ArraySet<T> arraySet, int index) {
        return get(arraySet, index, null);
    }


    /**
     * @param vg    The ViewGroup
     * @param index the index at which the item needs to be fetched
     * @return item if found, null otherwise
     */
    /*@Nullable*/
    public static View get(/*@Nullable*/ ViewGroup vg, int index) {
        return get(vg, index, null);
    }


    /**
     * @param vg           The ViewGroup
     * @param index        the index at which the item needs to be fetched
     * @param defaultValue in case of ViewGroup is null or index out of bound, return this value
     * @return item if found, defaultValue otherwise
     */
    /*@Nullable*/
    public static View get(/*@Nullable*/ ViewGroup vg, int index, /*@Nullable*/ View defaultValue) {
        if (vg == null) {
            return defaultValue;
        }

        if (index < 0 || index >= size(vg)) {
            return defaultValue;
        }
        return vg.getChildAt(index);
    }


    /**
     * Safely get an item from JSONArray
     * <p>
     * same as calling {@link #get(JSONArray, int, Object) get(jsonArray,index,null)}
     * </p>
     *
     * @param ja    The JSONArray
     * @param index the index at which the item needs to be fetched
     * @return item if found, null otherwise
     * @see #get(JSONArray, int, Object) get(jsonArray,index,null)
     */
    public static Object get(JSONArray ja, int index) {
        return get(ja, index, null);
    }

    /**
     * Safely get an item from JSONArray
     *
     * @param ja           The JSONArray
     * @param index        the index at which the item needs to be fetched
     * @param defaultValue in case of JSONArray is null or index out of bound, return this value
     * @return item if found, defaultValue otherwise
     * @see #get(JSONArray, int) get(jsonArray,index)
     */
    public static Object get(JSONArray ja, int index, Object defaultValue) {
        if (isEmpty(ja)) {
            return defaultValue;
        }
        if (index < 0 || index >= size(ja)) {
            return defaultValue;
        }
        return ja.opt(index);
    }

    /**
     * Safely get last item from ArraySet
     * <p>
     * same as calling {@link #getLastItem(ArraySet, Object)}  getLastItem(arraySet,null)}
     * </p>
     *
     * @param as The ArraySet
     * @return item if found, null otherwise
     * @see #getLastItem(ArraySet, Object)  getLastItem(arraySet,defaultValue)
     */
    public static <T> T getLastItem(/*@Nullable*/ ArraySet<T> as) {
        return getLastItem(as, null);
    }

    /**
     * Safely get last item from ArraySet
     *
     * @param as           The ArraySet
     * @param defaultValue in case of string is null or empty, return this value
     * @return item if found, defaultValue otherwise
     * @see #getLastItem(ArraySet)  getLastItem(arraySet)
     */
    public static <T> T getLastItem(/*@Nullable*/ ArraySet<T> as, T defaultValue) {
        return get(as, GenericUtil.size(as) - 1, defaultValue);
    }

    /**
     * Safely get last item from ViewGroup
     * <p>
     * same as calling {@link #getLastItem(ViewGroup, View)}  getLastItem(viewGroup,null)}
     * </p>
     *
     * @param vg The ViewGroup
     * @return item if found, null otherwise
     * @see #getLastItem(ViewGroup, View)  getLastItem(viewGroup,defaultValue)
     */
    public static View getLastItem(/*@Nullable*/ ViewGroup vg) {
        return getLastItem(vg, null);
    }

    /**
     * Safely get last item from ViewGroup
     *
     * @param vg           The ViewGroup
     * @param defaultValue in case of string is null or empty, return this value
     * @return item if found, defaultValue otherwise
     * @see #getLastItem(ViewGroup)  getLastItem(viewGroup)
     */
    public static View getLastItem(/*@Nullable*/ ViewGroup vg, View defaultValue) {
        return get(vg, size(vg) - 1, defaultValue);
    }


    /**
     * Safely get last item from jsonArray
     * <p>
     * same as calling {@link #getLastItem(JSONArray, Object) getLastItem(jsonArray,null)}
     * </p>
     *
     * @param ja The JSONArray
     * @return item if found, null otherwise
     * @see #getLastItem(JSONArray, Object)  getLastItem(jsonArray,defaultValue)
     */
    public static Object getLastItem(JSONArray ja) {
        return getLastItem(ja, null);
    }

    /**
     * Safely get last item from jsonArray
     *
     * @param ja           The JSONArray
     * @param defaultValue in case of jsonArray is null or empty, return this value
     * @return item if found, null otherwise
     * @see #getLastItem(JSONArray, Object)  getLastItem(jsonArray,defaultValue)
     */
    public static Object getLastItem(JSONArray ja, Object defaultValue) {
        return get(ja, size(ja) - 1, defaultValue);
    }

    /**
     * Get the size of the ViewGroup
     *
     * @param vg The ViewGroup
     * @return 0 if the ViewGroup is null, actual size of the ViewGroup otherwise
     * @see #size(ArraySet)
     * @see #size(Bundle)
     * @see #size(Object[])
     * @see #size(String)
     * @see #size(CharSequence)
     * @see #size(JSONArray)
     * @see #size(Collection)
     */
    public static int size(/*@Nullable*/ ViewGroup vg) {
        return vg == null ? 0 : vg.getChildCount();
    }


    /**
     * Get the size of the ArraySet
     *
     * @param as The ArraySet
     * @return 0 if the ArraySet is null, actual size of the ArraySet otherwise
     * @see #size(ViewGroup)
     * @see #size(Bundle)
     * @see #size(Object[])
     * @see #size(String)
     * @see #size(CharSequence)
     * @see #size(JSONArray)
     * @see #size(Collection)
     */
    public static <T> int size(/*@Nullable*/ ArraySet<T> as) {
        return as == null ? 0 : as.size();
    }

    /**
     * Get the size of the Bundle
     *
     * @param b The Bundle
     * @return 0 if the Bundle is null, actual size of the Bundle otherwise
     * @see #size(ArraySet)
     * @see #size(ViewGroup)
     * @see #size(Object[])
     * @see #size(String)
     * @see #size(CharSequence)
     * @see #size(JSONArray)
     * @see #size(Collection)
     */
    public static int size(/*@Nullable*/ Bundle b) {
        return b == null ? 0 : b.size();
    }

    /**
     * Is the TextView null or empty?
     *
     * @param tv The TextView
     * @return true if the TextView is null or empty
     * @see #isEmpty(Collection)
     * @see #isEmpty(Object[])
     * @see #isEmpty(String)
     * @see #isEmpty(Map)
     * @see #isEmpty(JSONArray)
     * @see #isEmpty(Editable)
     * @see #isEmpty(PagerAdapter)
     * @see #isEmpty(Bundle)
     */
    public static boolean isEmpty(/*@Nullable*/ TextView tv) {
        return tv == null || isEmpty(getString(tv));
    }

    /**
     * Get the size of the jsonArray
     *
     * @param ja The jsonArray
     * @return 0 if the jsonArray is null or empty, actual size of the jsonArray otherwise
     * @see #size(Collection)
     * @see #size(Object[])
     * @see #size(String)
     * @see #size(CharSequence)
     */
    public static int size(JSONArray ja) {
        return ja == null ? 0 : ja.length();
    }


    /**
     * Is the Editable null or empty?
     *
     * @param et The Editable
     * @return true if the Editable is null or empty
     * @see #isEmpty(Collection)
     * @see #isEmpty(Object[])
     * @see #isEmpty(String)
     * @see #isEmpty(Map)
     * @see #isEmpty(JSONArray)
     * @see #isEmpty(TextView)
     * @see #isEmpty(PagerAdapter)
     * @see #isEmpty(Bundle)
     */
    public static boolean isEmpty(/*@Nullable*/ Editable et) {
        return isEmpty(getString(et));
    }

    /**
     * Is the PagerAdapter null or empty?
     *
     * @param adapter The PagerAdapter
     * @return true if the PagerAdapter is null or empty
     * @see #isEmpty(Collection)
     * @see #isEmpty(Object[])
     * @see #isEmpty(String)
     * @see #isEmpty(Map)
     * @see #isEmpty(JSONArray)
     * @see #isEmpty(TextView)
     * @see #isEmpty(Editable)
     * @see #isEmpty(Bundle)
     */
    public static boolean isEmpty(/*@Nullable*/ PagerAdapter adapter) {
        return adapter == null || adapter.getCount() == 0;

    }

    /**
     * Is the Bundle null or empty?
     *
     * @param b The Bundle
     * @return true if the Bundle is null or empty
     * @see #isEmpty(Collection)
     * @see #isEmpty(Object[])
     * @see #isEmpty(String)
     * @see #isEmpty(Map)
     * @see #isEmpty(JSONArray)
     * @see #isEmpty(TextView)
     * @see #isEmpty(PagerAdapter)
     * @see #isEmpty(Editable)
     */
    public static <T> boolean isEmpty(/*@Nullable*/ Bundle b) {
        return b == null || b.isEmpty();
    }

    /**
     * Is the JSONArray null or empty?
     *
     * @param ja The JSONArray
     * @return true if the array is null or empty
     * @see #isEmpty(Collection)
     * @see #isEmpty(String)
     * @see #isEmpty(CharSequence)
     * @see #isEmpty(Map)
     * @see #isEmpty(Object[])
     */
    public static boolean isEmpty(JSONArray ja) {
        return ja == null || ja.length() < 1;

    }


    /**
     * Get String from TextView/EditText
     *
     * @param tv The TextView/EditText
     * @return the string from EditText, null in all other cases
     */
    /*@Nullable*/
    public static String getString(/*@Nullable*/ TextView tv) {
        if (tv == null) {
            return null;
        }
        if (tv.getText() == null) {
            return null;
        }
        return getString(tv.getText().toString());
    }


    /**
     * Get String from TextView/EditText
     *
     * @param tv The TextView/EditText
     * @return the string from EditText, empty String in all other cases
     */
    /*@NonNull*/
    public static String getStringSafe(/*@Nullable*/ TextView tv) {
        String s = getString(tv);
        return s == null ? JUtil.EMPTY_STRING : s;
    }

    /**
     * Is the string a valid email?
     *
     * @param s The string
     * @return true if the entered string is valid email, false otherwise
     */
    public static boolean isValidEmail(/*@Nullable*/ String s) {
        String string = getString(s);
        return !isEmpty(string) && Patterns.EMAIL_ADDRESS.matcher(string).matches();
    }


    /**
     * Is the entered phone number valid
     *
     * @param target The phone number to be tested
     * @return true if phone number is valid, false otherwise
     */
    public static boolean isValidPhone(String target) {
        return !TextUtils.isEmpty(target) && Patterns.PHONE.matcher(target).matches();
    }


    /**
     * Get the app version
     *
     * @param c Context
     * @return The app version
     */
    public static int getAppVersion(Context c) {
        try {
            PackageInfo packageInfo = c.getPackageManager()
                    .getPackageInfo(c.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Throwable e) {
            //throw new RuntimeException("Could not get package name: " + e);
            return Integer.MIN_VALUE;
        }
    }

    /**
     * Get the app versionName
     *
     * @param c Context
     * @return The app versionName
     */
    public static String getAppVersionName(Context c) {
        try {
            PackageInfo packageInfo = c.getPackageManager()
                    .getPackageInfo(c.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Throwable e) {
            //throw new RuntimeException("Could not get package name: " + e);
            return "";
        }
    }

    /**
     * Returns displayable styled text from the provided HTML string.
     **/
    public static Spanned getSpannedFromHtml(String s) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT);
        } else {
            //noinspection deprecation , os version checked
            return Html.fromHtml(s);
        }
    }

    /**
     * Is any object passed is null or empty.<br>
     * <p>
     * Usage: <code>isAnyEmpty(string1,string2,string3,string4)</code><br>
     * Usage: <code>isAnyEmpty(collection,map,string,charSequence,object)</code>
     * </p>
     *
     * @param objects objects to be checked
     * @return true, if any of the object passed is null or empty, false otherwise
     */
    public static boolean isAnyEmpty(Object... objects) {
        if (objects == null || size(objects) == 0) {
            return true;
        }

        for (Object o : objects) {
            if (o == null) {
                return true;
            } else {
                if (o instanceof Collection) {
                    if (isEmpty((Collection) o)) {
                        return true;
                    }
                } else if (o instanceof Map) {
                    if (isEmpty((Map) o)) {
                        return true;
                    }
                } else if (o instanceof String) {
                    if (isEmpty((String) o)) {
                        return true;
                    }
                } else if (o instanceof CharSequence) {
                    if (isEmpty((CharSequence) o)) {
                        return true;
                    }
                } else if (o instanceof JSONArray) {
                    if (isEmpty((JSONArray) o)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Are all the object passed is null or empty.<br>
     * <p>
     * Usage: <code>isAnyEmpty(string1,string2,string3,string4)</code><br>
     * Usage: <code>isAnyEmpty(collection,map,string,charSequence,object)</code>
     * </p>
     *
     * @param objects objects to be checked
     * @return true, if all of the object passed is null or empty, false otherwise
     */
    public static boolean isAllEmpty(Object... objects) {
        if (objects == null || size(objects) == 0) {
            return true;
        }
        for (Object o : objects) {
            if (o != null) {
                if (o instanceof Collection) {
                    if (!isEmpty((Collection) o)) {
                        return false;
                    }
                } else if (o instanceof Map) {
                    if (!isEmpty((Map) o)) {
                        return false;
                    }
                } else if (o instanceof String) {
                    if (!isEmpty((String) o)) {
                        return false;
                    }
                } else if (o instanceof CharSequence) {
                    if (!isEmpty((CharSequence) o)) {
                        return false;
                    }
                } else if (o instanceof JSONArray) {
                    if (!isEmpty((JSONArray) o)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }


}