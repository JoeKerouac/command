package com.joe.command;

import com.joe.command.exception.CommandExistException;

/**
 * 命令引擎
 *
 * @author JoeKerouac
 * @version 2019年08月29日 11:33
 */
public interface CommandEngine {

    /**
     * 执行命令
     * @param commandStr 完整命令行，包含参数
     * @return 命令执行结果，可以为空
     */
    String exec(String commandStr);

    /**
     * 命令列表
     * @return 命令列表
     */
    String[] commandList();

    /**
     * 注册命令
     * @param command 要注册的命令
     * @throws CommandExistException 如果要注册的命令已经存在那么将会抛出该异常
     */
    void registerCommand(Command command) throws CommandExistException;

    /**
     * 注销命令
     * @param commandName 要注销的命令名{@link Command#name()}
     * @return 如果命令存在那么删除并返回
     */
    Command unregister(String commandName);

    /**
     * 搜索指定命令
     * @param commandName 命令名
     * @return 指定命令，不存在时返回null
     */
    Command searchCommand(String commandName);
}
