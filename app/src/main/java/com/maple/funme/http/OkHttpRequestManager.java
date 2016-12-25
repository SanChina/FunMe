package com.maple.funme.http;

import android.os.Handler;

import com.maple.funme.interfaces.IRequestCallback;
import com.maple.funme.interfaces.IRequestManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by San on 2016/12/23.
 */

public class OkHttpRequestManager implements IRequestManager {

    private OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public static IRequestManager getInstance() {
        return OkHttpRequestManagerHolder.INSTANCE;
    }

    private static class OkHttpRequestManagerHolder {
        private static OkHttpRequestManager INSTANCE = new OkHttpRequestManager();
    }

    private OkHttpRequestManager() {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        mHandler = new Handler();
    }

    @Override
    public void get(String url, IRequestCallback requestCallback) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        addCallback(requestCallback, request);
    }

    private void addCallback(final IRequestCallback requestCallback, final Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                e.printStackTrace();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        requestCallback.onFailure(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String json = response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.onSuccess(json);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void post(String url, String requestBodyJson, IRequestCallback requestCallback) {

    }


}
