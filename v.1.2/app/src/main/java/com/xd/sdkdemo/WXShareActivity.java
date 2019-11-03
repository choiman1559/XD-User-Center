package com.xd.sdkdemo;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xd.xdsdk.share.XDWXShare;
import com.xd.xdsdk.share.XDWXShareObject;

/**
 * Created by sunyi on 2017/8/9.
 */

public class WXShareActivity extends Activity implements View.OnClickListener{

    private static final String TAG = WXShareActivity.class.getSimpleName();

    private String path =  Environment.getExternalStorageDirectory().getAbsolutePath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity);

        Button text, image, music, video, web;

        text = (Button) findViewById(R.id.text);
        image = (Button) findViewById(R.id.image);
        music = (Button) findViewById(R.id.music);
        video = (Button) findViewById(R.id.video);
        web = (Button) findViewById(R.id.web);

        text.setOnClickListener(this);
        image.setOnClickListener(this);
        music.setOnClickListener(this);
        video.setOnClickListener(this);
        web.setOnClickListener(this);

        XDWXShare.setWXShareCallBack(new XDWXShare.XDWXShareCallback() {
            @Override
            public void onWXShareSucceed() {
                Log.e(TAG, "onWXShareSucceed");
            }

            @Override
            public void onWXShareFailed(String msg) {
                Log.e(TAG, "onWXShareFailed : " + msg);
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text:
                XDWXShareObject wxShareContent = new XDWXShareObject();
                wxShareContent.setTitle("title");
                wxShareContent.setText("text");
                wxShareContent.setDescription("description");
                wxShareContent.setScene(XDWXShareObject.SCENE_TIMELINE);
                wxShareContent.setType(XDWXShareObject.TYPE_TEXT);
                XDWXShare.share(wxShareContent);
                break;
            case R.id.image:
                wxShareContent = new XDWXShareObject();
                wxShareContent.setTitle("title");
                wxShareContent.setDescription("description");
                wxShareContent.setImage(path + "/1.jpeg");
//                wxShareContent.setThumbPath(path + "/2.png");
                wxShareContent.setImage(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                wxShareContent.setThumb(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
                wxShareContent.setScene(XDWXShareObject.SCENE_TIMELINE);
                wxShareContent.setType(XDWXShareObject.TYPE_IMAGE);
                XDWXShare.share(wxShareContent);
                break;
            case R.id.music:
                wxShareContent = new XDWXShareObject();
                wxShareContent.setTitle("title");
                wxShareContent.setDescription("description");
                wxShareContent.setMusicUrl("http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3");
                wxShareContent.setThumb(path + "/2.png");
                wxShareContent.setScene(XDWXShareObject.SCENE_TIMELINE);
                wxShareContent.setType(XDWXShareObject.TYPE_MUSIC);
                XDWXShare.share(wxShareContent);
                break;
            case R.id.video:
                wxShareContent = new XDWXShareObject();
                wxShareContent.setTitle("title");
                wxShareContent.setDescription("description");
                wxShareContent.setVideoUrl("www.qq.com");
                wxShareContent.setThumb(path + "/2.png");
                wxShareContent.setScene(XDWXShareObject.SCENE_TIMELINE);
                wxShareContent.setType(XDWXShareObject.TYPE_VIDEO);
                XDWXShare.share(wxShareContent);
                break;
            case R.id.web:
                wxShareContent = new XDWXShareObject();
                wxShareContent.setTitle("title");
                wxShareContent.setDescription("description");
                wxShareContent.setWebPageUrl("xd.com");
                wxShareContent.setThumb(path + "/2.png");
                wxShareContent.setScene(XDWXShareObject.SCENE_SESSION);
                wxShareContent.setType(XDWXShareObject.TYPE_WEB);
                XDWXShare.share(wxShareContent);
            default:
                break;
        }
    }
}
