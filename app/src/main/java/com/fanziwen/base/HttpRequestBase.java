package com.fanziwen.base;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.lang.reflect.Member;

public class HttpRequestBase implements Serializable {

    @Expose
    public int respCode;

    @Expose
    public String respMsg;

    @Expose
    public Member user;

}
