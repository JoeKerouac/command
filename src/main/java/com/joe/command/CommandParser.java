package com.joe.command;

import com.joe.command.exception.CommandException;

/**
 * 命令解析
 *
 * @author JoeKerouac
 * @version 2019年08月29日 11:48
 */
public interface CommandParser {

    /**
     * 解析命令行
     * @param commandStr 完整命令行，包含命令和命令参数
     * @return 命令行解析结果
     * @throws CommandException 解析异常的时候抛出该异常
     */
    CommandContext parse(String commandStr) throws CommandException;
}
