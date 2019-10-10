package com.v5b7c6.android.crosswalk.sample;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.ValueCallback;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkView;

public class MainActivity extends XWalkActivity {

    private XWalkView xwalkView;

    @Override
    protected void onXWalkReady() {
        xwalkView.setResourceClient(new XWalkResourceClient(xwalkView){
            @Override
            public void onReceivedSslError(XWalkView view, ValueCallback<Boolean> callback, SslError error) {
                callback.onReceiveValue(true);//系统WebView中的是handler.process()
            }
        });
        xwalkView.loadUrl("https://crosswalk-project.org");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xwalkView = findViewById(R.id.xwalkView);
        // turn on debugging
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
    }
}
