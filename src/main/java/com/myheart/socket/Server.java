package com.myheart.socket;

import com.myheart.thread.ThreadPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

/**
 * @author yangxin
 * @date 2018/12/10
 */
public class Server{

    private ServerSocket serverSocket;

    private ThreadPool threadPool;


    public void start(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(new File("target/classes/property.properties")));
            serverSocket = new ServerSocket(Integer.parseInt(prop.getProperty("port")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                Socket socket = serverSocket.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
