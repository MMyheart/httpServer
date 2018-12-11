package com.myheart.socket;

import java.net.Socket;

/**
 * @author yangxin
 * @date 2018/12/10
 */
public class Request {
    private Socket socket;

    public Request(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
