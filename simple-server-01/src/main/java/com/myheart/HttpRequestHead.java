package com.myheart;

import lombok.Data;

/**
 * @author yangxin
 * @date 2018/12/11
 */
@Data
public class HttpRequestHead {

    private String method;

    private String protocol;

    private String uri;

    private String host;

    private String port;

    private String connection;

    private String cacheControl;

}
