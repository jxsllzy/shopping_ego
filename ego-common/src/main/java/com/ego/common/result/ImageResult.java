package com.ego.common.result;
import java.io.Serializable;

public class ImageResult implements Serializable {
    /**
     * code :
     * msg :
     * data : {"src":"}
     */

    private int code;
    private String msg;
    private SrcResult data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SrcResult getData() {
        return data;
    }

    public void setData(SrcResult data) {
        this.data = data;
    }
}
