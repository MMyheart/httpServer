package com.myheart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author yangxin
 * @date 2018/12/11
 */
public class Response {

    /**
     * CR.
     */
//    private static final byte CR = (byte) '\r';
    private static final String CR = "\r";

    /**
     * LF.
     */
//    private static final byte LF = (byte) '\n';
    private static final String LF = "\n";

    /**
     * SP.
     */
//    private static final byte SP = (byte) ' ';
    private static final String SP = " ";


    private Request request;

    private String protocol;

    private int httpCode;

    private String httpMsg;

    private String content;

    private InputStream inputStream;

    private OutputStream outputStream;

    public void close(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(protocol).append(SP).append(httpCode).append(SP).append(httpMsg).append(CR).append(LF)
                .append(CR).append(LF).append(content);
        String string = stringBuilder.toString();
        try {
            outputStream.write(string.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
        this.inputStream = request.getInputStream();
        this.outputStream = request.getOutputStream();
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpMsg() {
        return httpMsg;
    }

    public void setHttpMsg(String httpMsg) {
        this.httpMsg = httpMsg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
