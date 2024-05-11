package com.ck.mall.frontend.console;

/**
 * 转换时可能会出现的异常
 *
 * @author Qnxy
 */
public class ConsoleValueMappingException extends RuntimeException {

    public ConsoleValueMappingException(String message) {
        super(message);
    }

    public ConsoleValueMappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
