package com.flesh.commonlyusedutilslib;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Handler;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.os.Handler;
import android.content.Intent;

/**
 * Created by afleshner on 3/5/14.
 */
public class CommonlyUsedUtilsBeta {

    private Context mContext;

    public CommonlyUsedUtilsBeta(Context cxt) {
        this.mContext = cxt;
    }

    /**
     * place in an onTouchListener and you are able to toggle the password visibility on touch like in win 8
     * <p/>
     * Method still in beta because it will crash the system if spammed. (there is a leak somewhere in the code.
     * It has something to do with the keyboard being open and not closing the connection correctly.
     */
    public void revealPassWord(final EditText et, final MotionEvent event) {
        final InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            imm.hideSoftInputFromWindow(((Activity) mContext).getWindow().getCurrentFocus().getWindowToken(), 0);
            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                public void run() {
                    if (imm.isActive(et)) {
                        et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        et.setSelection(et.getText().toString().length());
                    }
                }
            }, 50);

        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            // Handler h = new Handler();
            // h.postDelayed(new Runnable() {
            // public void run() {
            // if (event.getAction() != MotionEvent.ACTION_DOWN) {
            if (imm.isActive(et)) {
                et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                et.setSelection(et.getText().toString().length());
                imm.showSoftInput(et, 0);
            }
            // }
            // }
            // }, 250);
        }
    }

    public static Bitmap createACircularImage(Bitmap bm) {
        Bitmap circleBitmap = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader (bm,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        Canvas c = new Canvas(circleBitmap);
        c.drawCircle(bm.getWidth()/2, bm.getHeight()/2, bm.getWidth()/2, paint);
        return bm;
    }
    
   public static void SplashTimer(final Activity act, int inMilliSecs, final Class whereTo){
        new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    Intent i = new Intent(act, whereTo);
                    act.startActivity(i);
                    act.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    act.finish();
                }
            },inMilliSecs);
    }


    
}
