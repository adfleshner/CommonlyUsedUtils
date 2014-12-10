package com.flesh.commonlyusedutilslib;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by afleshner on 3/19/14.
 */
public class ActionBarUtils {

    /**
     * This is how you get it if you are using Support Lib v7 (aka ActionBarActivity)
     */
    public static AutoCompleteTextView GetSupportV7ActionBarSeachEditText(MenuItem item) {
        AutoCompleteTextView editText;
        SearchView sv = (SearchView) MenuItemCompat.getActionView(item);
        editText = (AutoCompleteTextView) sv.findViewById(R.id.search_src_text);
        return editText;
    }
    /**
     * This is how you get it if you are using Support Lib v4 (aka FragmentActivity)
     */
    @TargetApi(11)
    public static EditText GetActionBarSeachEditText(MenuItem item, int SearchViewId) {
        AutoCompleteTextView editText;
        android.widget.SearchView sv = (android.widget.SearchView) item.getActionView().findViewById(SearchViewId);
        int id = sv.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        editText = (AutoCompleteTextView) sv.findViewById(id);
        return editText;
    }
    
    
//    public static TextView getActionBarTitleTextView(Context cxt){
//        int titleId = cxt.getResources().getIdentifier("action_bar_title", "id","android");
//        TextView tv = new TextView(cxt);
//        return (TextView) cxt.findViewById(titleId);
//    }
//     public static TextView getActionBarSubTitleTextView(Context cxt){
//        int titleId = cxt.getResources().getIdentifier("action_bar_subtitle", "id","android");
//        return (TextView) findViewById(titleId);
//    }
    
}
