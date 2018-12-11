package com.myheart.thread;

import com.myheart.socket.Server;

/**
 * @author yangxin
 * @date 2018/12/10
 */
public class Bootstrap {

    private Boolean RUNNING = false;

    private Server server;

    public void start(){
        server = new Server();

    }
}
