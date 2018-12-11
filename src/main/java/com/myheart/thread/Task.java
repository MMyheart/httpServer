package com.myheart.thread;

import com.myheart.socket.Request;
import com.myheart.socket.Response;

/**
 * @author yangxin
 * @date 2018/12/10
 */
public class Task implements Runnable{

    private Request request;

    private Response response;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public void run() {

    }
}
