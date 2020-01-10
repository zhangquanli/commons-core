package com.github.zhangquanli.commons.core.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应结果
 *
 * @author zhangquanli
 */
@ApiModel("响应结果")
public class ResponseResult<T> {
    static final int SUCCESS_CODE = 0;
    static final int FAILURE_CODE = 1;
    static final String SUCCESS_MSG = "成功";
    static final String FAILURE_MSG = "失败";

    @ApiModelProperty(value = "代码", position = 1, required = true, example = SUCCESS_CODE + "")
    private int code;
    @ApiModelProperty(value = "信息", position = 2, required = true, example = SUCCESS_MSG)
    private String msg;
    @ApiModelProperty(value = "数据", position = 3)
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
