package com.joe.command.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.joe.command.*;
import com.joe.command.exception.CommandException;
import com.joe.command.exception.CommandExistException;
import com.joe.utils.common.Assert;
import com.joe.utils.common.string.StringConst;
import com.joe.utils.common.string.StringFormater;
import com.joe.utils.common.string.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 命令执行引擎默认实现
 *
 * @author JoeKerouac
 * @version 2019年08月29日 11:40
 */
@Slf4j
public class CommandEngineImpl implements CommandEngine {

    /**
     * 命令不存在时的提示语
     */
    private static final String  CMD_NOT_EXIST_TEMP = "command not found: [{0}]";

    /**
     * 命令容器
     */
    private Map<String, Command> container          = new ConcurrentHashMap<>();

    /**
     * 变更锁
     */
    private final Object         modifyLock         = new Object();

    /**
     * 命令解析
     */
    private final CommandParser  parser;

    /**
     * 命令环境
     */
    private final Environment    environment;

    public CommandEngineImpl(CommandParser parser, Environment environment) {
        this.parser = parser;
        this.environment = environment;
    }

    @Override
    public String exec(String commandStr) {
        if (StringUtils.isEmpty(commandStr)) {
            return StringConst.EMPTY;
        }

        try {
            CommandContext context = parser.parse(commandStr);
            Command command = container.get(context.name());
            if (command == null) {
                return StringFormater.simpleFormat(CMD_NOT_EXIST_TEMP, context.name());
            }
            return command.exec(context, this, environment);
        } catch (CommandException e) {
            return e.getUserErrInfo();
        } catch (Throwable e) {
            log.error("异常未被捕获", e);
            return "服务器异常";
        }
    }

    @Override
    public String[] commandList() {
        return container.keySet().toArray(new String[0]);
    }

    @Override
    public void registerCommand(Command command) throws CommandExistException {
        Assert.notNull(command, "要注册的command不能为null");

        if (container.containsKey(command.name())) {
            throw new CommandExistException(command);
        }

        synchronized (modifyLock) {
            if (container.containsKey(command.name())) {
                throw new CommandExistException(command);
            }
            container.put(command.name(), command);
        }
    }

    @Override
    public Command unregister(String commandName) {
        Assert.notBlank(commandName, "commandName不能为空");
        synchronized (modifyLock) {
            return container.remove(commandName);
        }
    }

    @Override
    public Command searchCommand(String commandName) {
        return container.get(commandName);
    }
}
