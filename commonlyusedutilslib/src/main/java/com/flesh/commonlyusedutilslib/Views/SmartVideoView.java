package com.flesh.commonlyusedutilslib.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by afleshner on 5/16/2014.
 */
public class SmartVideoView extends VideoView {

    private PlayPauseListener mListener;

    public SmartVideoView(Context context) {
        super(context);
    }

    public SmartVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setPlayPauseListener(PlayPauseListener listener) {
        mListener = listener;
    }

    @Override
    public void pause() {
        super.pause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    public void start() {
        super.start();
        if (mListener != null) {
            mListener.onPlay();
        }
    }

    interface PlayPauseListener {
        void onPlay();
        void onPause();
    }

}
