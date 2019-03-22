package com.example.capp.utils;

public class APIResponse<T> {

    private static final String CODE_SUCCESS = "success";

    private static final String CODE_FAIL = "fail";


    private String code;
    private T data;
    private String message;

    public APIResponse(){

    }

    public APIResponse(String code){
        this.code = code;
    }

    public APIResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public APIResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public static APIResponse success() {
        return new APIResponse(CODE_SUCCESS);
    }

    public static APIResponse success(Object data) {
        return new APIResponse(CODE_SUCCESS, data);
    }

    public static APIResponse fail(String message) {
        return new APIResponse(CODE_FAIL, message);
    }

    public static APIResponse withCode(String code){
        return new APIResponse(code);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
