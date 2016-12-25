package com.maple.funme.interfaces;

/**
 * Created by San on 2016/12/23.
 */

public interface IRequestCallback {

    void onSuccess(String response);

    void onFailure(Throwable throwable);

}
