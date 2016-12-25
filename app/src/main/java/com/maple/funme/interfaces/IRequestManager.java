package com.maple.funme.interfaces;

/**
 * Created by San on 2016/12/23.
 */

public interface IRequestManager {

    void get(String url, IRequestCallback requestCallback);

    void post(String url, String requestBodyJson, IRequestCallback requestCallback);

}
