package com.gohb.dto;

public class ResultUtils {

    private ResultUtils() {
    }

    public static Result<Boolean> getSuccessResult() {
        Result<Boolean> result = new Result<>();
        result.setData(true);
        result.setSuccess(true);
        return result;
    }

    public static  <T> Result<T> getSuccessResult(T t) {
        Result<T> result = new Result<>();
        result.setData(t);
        result.setSuccess(true);
        return result;
    }


    public static Result getFailedResult(Integer code, String msg) {
        Result<Boolean> result = new Result<>();
        result.setData(null);
        result.setMessage(msg);
        result.setStatusCode(code);
        return result;
    }

    public static Result getFailedResult(Integer code, String msg, Object data) {
        Result result = new Result<>();
        result.setErrorData(data);
        result.setMessage(msg);
        result.setStatusCode(code);
        return result;
    }


}
