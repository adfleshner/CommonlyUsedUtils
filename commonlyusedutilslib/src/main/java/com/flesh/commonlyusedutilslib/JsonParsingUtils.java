package com.flesh.commonlyusedutilslib;

import android.content.Context;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by afleshner on 11/20/2014.
 */
public class JsonParsingUtils {

    /**
     * Takes a JsonFile and creates an object from it.
     * <p/>
     * Acceptable String file name.
     * fileName can = "file.json" or just "file"
     *
     * @param cxt
     * @param fileName
     * @param clazz
     * @return
     */
    public static Object parseJsonObjectFromFile(Context cxt, String fileName, Class clazz) {
        String json = getJsonFileFromAssets(cxt, fileName, clazz);
        return new Gson().fromJson(json, clazz);
    }

    /**
     * Takes a JsonFile and creates an ArrayList from it.
     * <p/>
     * Acceptable String file name.
     * fileName can = "file.json" or just "file"
     *
     * @param cxt
     * @param fileName
     * @param clazz
     * @return
     */
    public static ArrayList parseJsonArrayListFromFile(Context cxt, String fileName, Class clazz) {
        String json = getJsonFileFromAssets(cxt, fileName, clazz);
        Type listType = new ListParameterizedType(clazz);
        return new Gson().fromJson(json, listType);
    }

    /**
     * Takes a Json string and creates an object from it.
     * <p/>
     * Acceptable String file name.
     * fileName can = "file.json" or just "file"
     *
     * @param cxt
     * @param json
     * @param clazz
     * @return
     */
    public static Object parseJsonObject(Context cxt, String json, Class clazz) {
        return new Gson().fromJson(json, clazz);
    }

    /**
     * Takes a Json String and creates an ArrayList from it.
     *
     * @param cxt
     * @param json
     * @param clazz
     * @return
     */
    public static ArrayList parseJsonArrayList(Context cxt, String json, Class clazz) {
        Type listType = new ListParameterizedType(clazz);
        return new Gson().fromJson(json, listType);
    }

    /**
     * The following Method Takes in a fileName from the assets and adds ".json" to the end of the final
     *
     * @param cxt
     * @param fileName
     * @param clazz
     * @return
     */
    private static String getJsonFileFromAssets(Context cxt, String fileName, Class clazz) {
        String json = null;
        int length = fileName.length();
        //if string length is greater than or equal to five check if .json is at the end.
        if (length >= 5) {
            //Add json connection at the end of the filename if .json is not there.
            if (!fileName.substring(length - 5, length).equals(".json")) {
                fileName = fileName.concat(".json");
            }
        } else {
            //if length not 5 just add ".json" to the end
            fileName = fileName.concat(".json");
        }
        try {
            InputStream is = cxt.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * Takes in a class and then creates a type and returns it to the Gson type so that a list can be creates from the json.
     */
    private static class ListParameterizedType implements ParameterizedType {

        private Type type;

        private ListParameterizedType(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }

        // implement equals method too! (as per javadoc)
    }

}

