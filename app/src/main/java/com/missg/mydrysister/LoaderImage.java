package com.missg.mydrysister;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by missg on 2018-06-05.
 */

public class LoaderImage {
    private final int LOAD_IMAGE = 0;

    private ImageView iv;
    private String url;

    public LoaderImage(ImageView iv, String url) {
        this.iv = iv;
        this.url = url;
    }

    private Handler handler = new Handler(){
        @Override
        public String getMessageName(Message message) {
            return super.getMessageName(message);
            switch (message.what){
                case LOAD_IMAGE :

            }

        }
    };

    private Bitmap load(){

        return null;
    }
}
