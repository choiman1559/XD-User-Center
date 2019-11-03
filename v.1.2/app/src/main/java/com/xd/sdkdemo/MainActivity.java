package com.xd.sdkdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.net.*;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.xd.xdsdk.XDCallback;
import com.xd.xdsdk.XDSDK;


@SuppressLint("NewApi")
public class MainActivity extends Activity {

    public static final String TAG = MainActivity.class.getSimpleName();

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null) {
            NetworkInfo ni = cm.getActiveNetworkInfo();
            return ni != null && ni.isConnected();
        }
        return false;
    }

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final TextView json = (TextView)findViewById(R.id.json);
        context = this;

        json.setText("UID = UNKNOWN");
        if (!isOnline()) {
            json.setText("Check Internet and Try Again");
            Toast.makeText(this, "Check Internet and Try Again", Toast.LENGTH_LONG).show();

        } else {
            XDSDK.setCallback(new XDCallback() {
                @Override
                public void onInitSucceed() {
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
                    Toast.makeText(context, "Initialization Succeed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onInitFailed(String msg) {
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + msg);
                    Toast.makeText(context, "Initialization Failed", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onLoginSucceed(String token) {
                    json.setText("UID = " + XDSDK.getAccessToken());
                    Toast.makeText(context, XDSDK.getAccessToken(), Toast.LENGTH_LONG).show();
                    XDSDK.openUserCenter();
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + token);
                    Toast.makeText(context, "Login Succeed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLoginFailed(String msg) {
                    json.setText("Login Failed");
                    finishAffinity();
                    System.runFinalization();
                    System.exit(0);
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + msg);
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLoginCanceled() {
                    json.setText("Login Canceled");
                    finishAffinity();
                    System.runFinalization();
                    System.exit(0);
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
                    Toast.makeText(context, "Login Canceled", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onGuestBindSucceed(String token) {
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + token);
                    Toast.makeText(context, "onGuestBindSucceed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLogoutSucceed() {
                    finishAffinity();
                    System.runFinalization();
                    System.exit(0);
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
                    Toast.makeText(context, "Logout Succeed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPayCompleted() {
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
                    Toast.makeText(context, "onPayCompleted", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPayFailed(String msg) {
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName() + ":" + msg);
                    Toast.makeText(context, "onPayFailed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPayCanceled() {
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
                    Toast.makeText(context, "onPayCanceled", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRealNameSucceed() {
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
                    Toast.makeText(context, "onRealNameSucced", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onRealNameFailed(String msg) {
                    Log.e(TAG, Thread.currentThread().getStackTrace()[2].getMethodName());
                    Toast.makeText(context, "onRealNameFailed", Toast.LENGTH_SHORT).show();
                }
            });


            XDSDK.initSDK(this, "a4d6xky5gt4c80s", 1, "AndroidChannel", "AndroidVersion", true);
            XDSDK.login();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        XDSDK.onResume(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        XDSDK.onStop(this);
    }
}