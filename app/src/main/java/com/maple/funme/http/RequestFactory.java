package com.maple.funme.http;

import com.maple.funme.interfaces.IRequestManager;

/**
 * Created by San on 2016/12/23.
 */

public class RequestFactory {

    public static IRequestManager getRequestManager(){
        return OkHttpRequestManager.getInstance();
        //return OkHttpRequestManager.getInstance();
    }
}
