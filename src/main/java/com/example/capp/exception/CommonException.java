package com.example.capp.exception;

import com.example.capp.utils.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * 统一异常类
 * @author WM
 * @date 2019/3/22
 */
public class CommonException extends RuntimeException{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonException.class);

    private String errorCode;

    private String[] errorMessageArgument;

    private APIResponse apiResponse;


    protected CommonException() {
        this("");
    }

    public CommonException(String message) {
        super(message);
        this.errorCode = "fail";
        this.errorMessageArgument = new String[0];
    }

    public CommonException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "fail";
        this.errorMessageArgument = new String[0];
    }

    public static CommonException withErrorCode(String errorCode) {
        CommonException commonException = new CommonException();
        commonException.errorCode = errorCode;
        return commonException;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String[] getErrorMessageArgument() {
        return this.errorMessageArgument;
    }

    public CommonException withErrorMessageArguments(String... errorMessageArgument) {
        if(errorMessageArgument != null) {
            this.errorMessageArgument = errorMessageArgument;
        }
        return this;
    }

    public APIResponse response() {
        if(this.apiResponse != null) {
            return this.apiResponse;
        } else {
            this.apiResponse = APIResponse.withCode(this.getErrorCode());
            if (this.getErrorMessageArgument() != null && this.getErrorMessageArgument().length > 0) {
                try {
                    this.apiResponse.setMessage(MessageFormat.format(this.apiResponse.getMessage(), this.getErrorMessageArgument()));
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            return this.apiResponse;
        }
    }
}
