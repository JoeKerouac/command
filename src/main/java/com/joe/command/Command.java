package com.joe.command;

import com.joe.command.exception.CommandException;

/**
 * 命令对象
 * 
 * @author JoeKerouac
 * @version 2019年08月29日 11:33
 */
public interface Command {

    /**
     * 命令处理
     * @param input 命令输入
     * @param engine 当前命令执行引擎
     * @param env 当前命令环境
     * @return 命令输出
     * @throws CommandException 执行异常的时候应该抛出该异常或者该异常的子类
     */
    String exec(CommandContext input, CommandEngine engine,
                Environment env) throws CommandException;

    /**
     * 命令名
     * @return 命令名
     */
    String name();

    /**
     * 命令帮助文档
     * @return
     */
    String help();
}
