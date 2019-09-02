package com.joe.command.exception;

import com.joe.command.Command;
import com.joe.utils.common.string.StringFormater;

/**
 * 命令已存在异常
 * 
 * @author JoeKerouac
 * @version 2019年08月29日 11:36
 */
public class CommandExistException extends CommandException {

    private static final long serialVersionUID = -5636974616338836019L;

    public CommandExistException(Command command) {
        super(StringFormater.simpleFormat("命令[{0}]已存在", command.name()));
    }
}
