package com.missg.mydrysister;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by missg on 2018-06-05.
 */

public class LoaderImage {
    private final int CHANGE_IMG = 0;

    private ImageView iv;
    private byte[] data;

    public LoaderImage(ImageView iv) {
        this.iv = iv;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CHANGE_IMG:
                    if (data != null) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        iv.setImageBitmap(bitmap);
                    }
            }
        }
    };

    public void loadImg(final String imgUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(imgUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(10000);
                    if (conn.getResponseCode() == 200) {
                        try (InputStream in = conn.getInputStream();
                             ByteArrayOutputStream out = new ByteArrayOutputStream();
                        ) {
                            byte[] bytes = new byte[1024];
                            int len = 0;
                            while (-1 != (len = in.read(bytes))) {
                                out.write(bytes, 0, len);
                            }
                            data = out.toByteArray();
                            handler.sendEmptyMessage(CHANGE_IMG);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
