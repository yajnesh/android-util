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

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;


/**
 * Network utils
 * <p>
 *
 * @author Yajnesh T
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public class NetworkUtil {

    private static String CHARSET;

    static {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            CHARSET = StandardCharsets.UTF_8.name();
        } else {
            CHARSET = "UTF-8";
        }
    }


    /**
     * Get the network info
     * <p>
     * Be sure to add this permission in manifest <br>{@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}
     *
     * @param context The context
     * @return The network info
     */
    @SuppressLint("MissingPermission")
    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null ? cm.getActiveNetworkInfo() : null;
    }

    /**
     * Check if there is any connectivity
     *
     * @param c The context
     * @return true if network is connected, false otherwise
     */
    public static boolean isConnected(Context c) {
        return isConnected(c, false);
    }

    /**
     * Check if there is any connectivity
     *
     * @param context   The context
     * @param showToast Show Toast in case of no data
     * @return true if network is connected, false otherwise
     */
    public static boolean isConnected(Context context, boolean showToast) {
        if (context == null) {
            return false;
        }
        NetworkInfo info = getNetworkInfo(context);
        boolean isConnected = (info != null && info.isConnected());

        if (!isConnected && showToast) {
            ALog.toast(context, "Please check your data connection");
        }
        return isConnected;
    }

    /**
     * Check if there is any connectivity to a Wifi network
     *
     * @param context The context
     * @return true if connected to wifi, false otherwise
     */
    public static boolean isConnectedWifi(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Check if there is any connectivity to a mobile network
     *
     * @param context The context
     * @return true if connected to mobile internet, false otherwise
     */
    public static boolean isConnectedMobile(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }


    private static void genericAddKeyValuePair(String key, String value, StringBuilder builder) {
        if (GenericUtil.isAnyEmpty(key, value)) {
            return;
        }

        key = GenericUtil.safeUrlEncode(key, CHARSET);
        value = GenericUtil.safeUrlEncode(value, CHARSET);
        if (builder.length() > 0)
            builder.append("&");
        builder.append(key).append("=").append(value);

    }

    /**
     * Converts map into url params and appends to the url <br> <br>
     * <p>
     * Note that all keys and values will be urlencoded using GenericUtil.safeUrlEncode(string, CHARSET);
     *
     * @param url    The url, <code>www.example.com</code>
     * @param params a map containing key value pairs,  {@code key1=value1 , key2=value2 }
     * @return The constructed url, {@code www.example.com?key1=value1&key2=value2 }
     */
    public static String getUrlWithParams(String url, Map<String, String> params) {
        return getUrlWithParamsGeneric(url, null, params);
    }

    /**
     * Converts Collection into url params and appends to the url <br> <br>
     * <p>
     * Note that all keys and values will be urlencoded using GenericUtil.safeUrlEncode(string, CHARSET);
     *
     * @param url    The url, <code>www.example.com</code>
     * @param key    The common key, <code>key1</code>
     * @param params a collection containing values, {@code value1,value2}
     * @return The constructed url, {@code www.example.com?key1=value1&key1=value2 }
     */

    public static String getUrlWithParams(String url, String key, Collection<Object> params) {
        return getUrlWithParamsGeneric(url, key, params);
    }

    /**
     * Construct get url with parameters
     *
     * @param url    The bare url
     * @param params The params to be added to url
     * @return The parameter appended url
     */
    private static String getUrlWithParamsGeneric(String url, String sameKey, Object params) {

        if (GenericUtil.isAnyEmpty(url, params)) {
            return null;
        }

        StringBuilder builder = getGenericUrlWithParams(sameKey, params);

//        return url + "?" + builder.toString();
        return builder.insert(0, '?').insert(0, url).toString();
    }

    @SuppressWarnings("unchecked")
    private static StringBuilder getGenericUrlWithParams(String sameKey, Object params) {
        StringBuilder builder = new StringBuilder();

        if (params instanceof Collection) {
            if (GenericUtil.isEmpty(sameKey)) {
                return builder;
            }
            Collection<Object> cParams = (Collection<Object>) params;
            for (Object value : cParams) {
                if (value != null) {
                    genericAddKeyValuePair(sameKey, value.toString(), builder);
                }
            }
        } else if (params instanceof Map) {
            Map<String, String> mParams = (Map<String, String>) params;
            for (String key : mParams.keySet()) {
                genericAddKeyValuePair(key, mParams.get(key), builder);
            }
        }

        return builder;
    }


}
