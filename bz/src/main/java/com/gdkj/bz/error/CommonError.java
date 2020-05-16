package com.gdkj.bz.error;

/**
 * Created by DELL on 2019/9/10.
 */
public interface CommonError {

    public int getErrCode();

    public String getErrMsg();

    public CommonError setErrMsg(String errMsg);
}
