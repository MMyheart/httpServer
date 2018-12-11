package com.myheart;

import java.io.*;
import java.net.Socket;

/**
 * @author yangxin
 * @date 2018/12/11
 */
public class Request {

    private String uri;

    private InputStream inputStream;

    private OutputStream outputStream;

    private String content;

    private Socket socket;

    private HttpRequestHead httpRequestHead;

    public Request(Socket socket) {
        this.socket = socket;
        init();
    }

    private void init(){
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            httpRequestHead = new HttpRequestHead();

            String content = null;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            content = bufferedReader.readLine();

            String[] temp = content.split(" ");
            httpRequestHead.setMethod(temp[0]);
            if(temp.length == 3) {
                httpRequestHead.setUri(temp[1]);
                httpRequestHead.setProtocol(temp[2]);
            }else {
                httpRequestHead.setProtocol(temp[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        uri = httpRequestHead.getUri();
    }

    public String getContent() {
        return content;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public HttpRequestHead getHttpRequestHead() {
        return httpRequestHead;
    }

    public void setHttpRequestHead(HttpRequestHead httpRequestHead) {
        this.httpRequestHead = httpRequestHead;
    }
}
