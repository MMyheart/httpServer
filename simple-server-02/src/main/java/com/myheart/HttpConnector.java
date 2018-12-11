package com.myheart;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author yangxin
 * @date 2018/12/11
 */
@Slf4j
public class HttpConnector implements Connector {

    private int port = 8080;

    private int acceptCount = 10;

    private String threadName;

    private boolean initialize = false;

    private Thread thread;

    /**
     * 连接器的socket
     */
    private ServerSocket serverSocket;


    @Override
    public void initialize() {
        if(this.initialize){
            log.error("HttpConnector has initialized！");
            throw new RuntimeException("HttpConnector has initialized！");
        }

        this.initialize = true;
        this.threadName = "HttpConnector-"+port;

        try {
            serverSocket = new ServerSocket(port,acceptCount);
        } catch (IOException e) {
            log.error("Create Serverocket failure!");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start() {
        if(!this.initialize){
            initialize();
        }
        thread = new Thread(this,threadName);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {

    }
}
