package com.joe.command;

import com.joe.command.impl.CommandEngineImpl;
import com.joe.command.impl.CommandParserImpl;
import com.joe.command.impl.SimpleEnvironment;
import com.joe.command.provider.HelpCommand;

/**
 * 命令执行引擎工厂
 *
 * @author JoeKerouac
 * @version 2019年08月29日 11:35
 */
public class CommandEngineFactory {

    /**
     * 构建默认命令执行引擎
     * @return 默认命令执行引擎，使用默认命令解析器
     */
    public static CommandEngine build() {
        return build(null, null);
    }

    /**
     * 构建默认命令执行引擎
     * @param parser 命令执行引擎使用的命令解析器
     * @return 命令执行引擎
     */
    public static CommandEngine build(CommandParser parser) {
        return build(parser, null);
    }

    /**
     * 构建默认命令执行引擎
     * @param environment 命令环境
     * @return 命令执行引擎
     */
    public static CommandEngine build(Environment environment) {
        return build(null, environment);
    }

    /**
     * 构建默认命令执行引擎
     * @param parser 命令执行引擎使用的命令解析器
     * @param environment 命令环境
     * @return 命令执行引擎
     */
    public static CommandEngine build(CommandParser parser, Environment environment) {
        if (parser == null) {
            parser = new CommandParserImpl();
        }

        if (environment == null) {
            environment = new SimpleEnvironment();
        }

        CommandEngine engine = new CommandEngineImpl(parser, environment);
        // 注册默认命令
        engine.registerCommand(new HelpCommand());
        return engine;
    }
}
