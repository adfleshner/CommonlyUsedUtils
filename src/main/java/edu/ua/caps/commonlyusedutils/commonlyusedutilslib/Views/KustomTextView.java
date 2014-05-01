package edu.ua.caps.commonlyusedutils.commonlyusedutilslib.Views;

/**
 * Created by afleshner on 3/10/14.
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import edu.ua.caps.commonlyusedutils.commonlyusedutilslib.R;

/**
 * Subclass of {@link TextView} that supports the <code>fonts</code> attribute from XML.
 *
 *
 * usage in xml
 *{@literal
 *  first write: xmlns:cuu="http://schemas.android.com/apk/res-auto"
 *  then in the KustomTextView Tag
 *  cuu:font="path_to_the_font"
 * }
 *
 *
 * @author afleshner
 */
public class KustomTextView extends TextView {

    /*
     * Caches typefaces based on their file path and name, so that they don't have to be created
     * every time when they are referenced.
     */
    private static Map<String, Typeface> mTypefaces;

    public KustomTextView(final Context context) {
        this(context, null);
    }

    public KustomTextView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KustomTextView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
        if (mTypefaces == null) {
            mTypefaces = new HashMap<String, Typeface>();
        }
        final TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.KustomTextView);
        if (array != null) {
            final String typefaceAssetPath = array.getString(
                    R.styleable.KustomTextView_font);

            if (typefaceAssetPath != null) {
                Typeface typeface = null;

                if (mTypefaces.containsKey(typefaceAssetPath)) {
                    typeface = mTypefaces.get(typefaceAssetPath);
                } else {
                    AssetManager assets = context.getAssets();
                    typeface = Typeface.createFromAsset(assets, typefaceAssetPath);
                    mTypefaces.put(typefaceAssetPath, typeface);
                }
                setTypeface(typeface);
            }
            array.recycle();
        }
    }

}
