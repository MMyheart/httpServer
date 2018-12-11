package com.myheart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangxin
 * @date 2018/12/11
 */
public class Server {

    private static Boolean shutdown = false;
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    public static void main(String[] args) {
//            try {
//                ServerSocket ss = new ServerSocket(8888);
//                Socket socket = ss.accept();
//                Request request = new Request(socket);
//
//                Response response = new Response();
//                response.setRequest(request);
//                response.setHttpCode(200);
//                response.setHttpMsg("OK");
//                response.setProtocol(response.getRequest().getHttpRequestHead().getProtocol());
//                response.setContent("fgggfgffs");
//                response.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            Server server = new Server();
            server.await();
    }

    private static void await() {
        ServerSocket serverSocket = null;
        int port = 8888;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            System.exit(1);
        }
        //Loop waiting for a request
        while (!shutdown) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;

            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();
                //create Request object and parse
                Request request = new Request(socket);
//
//                //create Response object
                Response response = new Response();
                response.setRequest(request);
                response.setHttpCode(200);
                response.setHttpMsg("OK");
                response.setProtocol(response.getRequest().getHttpRequestHead().getProtocol());
                response.setContent("fgggfgffs");
                response.close();
                //close the socket
                socket.close();

//                String html = "http/1.1 200 ok\n"
//                        +"\n\n"
//                        +"1234服务端。。。。";
//                output.write(html.getBytes());
//                output.flush();
//                output.close();

                //check if the previous uri is a shutdown command
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                continue;
            }
        }
    }

}
