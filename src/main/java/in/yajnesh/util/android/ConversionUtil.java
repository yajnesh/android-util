package in.yajnesh.util.android;

/*
This file is part of JavaUtils.

JavaUtils is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

Foobar is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with JavaUtils. If not, see <https://www.gnu.org/licenses/>.

(ɔ) Yajnesh T
*/
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import in.yajnesh.util.java.JUtil;

/**
 * Collection of common conversion methods
 *
 * @author Yajnesh T
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ConversionUtil {

    /**
     * Convert map to Json
     *
     * @param map The map to be converted
     * @return The json constructed from map
     */
    public static String mapToJson(Map<String, String> map) {
        JSONObject j = new JSONObject();

        for (Map.Entry<String, String> v : map.entrySet()) {
            try {
                j.put(v.getKey(), v.getValue());
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return j.toString();
    }

    /**
     * Convert json to map
     *
     * @param jString The json object to be converted
     * @return converted map
     */
    public static Map<String, String> jsonToMap(String jString) {

        if (JUtil.isEmpty(jString)) {
            return JUtil.emptyMapMutable();
        }
        try {
            JSONObject j = new JSONObject(jString);

            Iterator<String> iter = j.keys();
            if (iter == null || !iter.hasNext()) {
                return JUtil.emptyMapMutable();
            }

            Map<String, String> hm = new HashMap<>();
            do {
                String item = iter.next();
                hm.put(item, j.getString(item));

            } while (iter.hasNext());
            return hm;

        } catch (Throwable e) {
            e.printStackTrace();
            return JUtil.emptyMapMutable();
        }
    }

    /**
     * Convert json to map
     *
     * @param jObject The json object to be converted
     * @return converted map
     */
    public static Map<String, String> jsonToMap(JSONObject jObject) {
        if (jObject != null) {
            return jsonToMap(jObject.toString());
        }
        return JUtil.emptyMapMutable();
    }


    /**
     * Convert collection to json array
     *
     * @param collection collection
     * @return converted json
     */
    public static <T> String collectionToJson(Collection<T> collection) {
        if (collection == null) {
            return JUtil.EMPTY_STRING;
        }
        try {
            JSONArray j = new JSONArray();
            for (T v : collection) {
                String value = JUtil.EMPTY_STRING;
                if (v != null) {
                    value = v.toString();
                }
                j.put(value);
            }
            return j.toString();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Convert json array to set
     *
     * @param jString jsonArray
     * @return set
     */
    public static Set<String> jsonToSet(String jString) {

        if (JUtil.isEmpty(jString)) {
            return null;
        }
        try {

            JSONArray jsonArray = new JSONArray(jString);

            Set<String> set = new HashSet<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                String value = jsonArray.getString(i);
                if (!JUtil.isEmpty(value)) {
                    set.add(value);
                }
            }
            return set;

        } catch (Throwable e) {
            e.printStackTrace();
            return JUtil.emptySetMutable();
        }
    }

    /**
     * Convert json to set
     *
     * @param jArray The json object to be converted
     * @return converted set
     */
    public static Set<String> jsonToSet(JSONArray jArray) {
        if (jArray != null) {
            return jsonToSet(jArray.toString());
        }
        return JUtil.emptySetMutable();
    }
}
