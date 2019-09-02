package com.joe.command.exception;

/**
 * 命令异常
 *
 * @author JoeKerouac
 * @version 2019年08月29日 11:35
 */
public abstract class CommandException extends RuntimeException {

    private static final long serialVersionUID = 7301459102429631396L;

    private String message;

    public CommandException(String message) {
        super(message);
        this.message = message;
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    protected CommandException(String message, Throwable cause, boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message;
    }

    /**
     * 获取对用户展示的异常提示
     * @return 对用户展示的异常提示
     */
    public String getUserErrInfo() {
        return message;
    }
}
