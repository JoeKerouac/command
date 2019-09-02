package com.joe.command.provider;

import com.joe.command.*;
import com.joe.utils.collection.CollectionUtil;
import com.joe.utils.common.string.StringFormater;
import com.joe.utils.common.string.StringUtils;

import java.util.List;

/**
 * 帮助命令
 * 
 * @author JoeKerouac
 * @version 2019年08月29日 16:03
 */
public class HelpCommand implements Command {

    /**
     * 命令名
     */
    private static final String NAME = "help";

    @Override
    public String exec(CommandContext input, CommandEngine engine, Environment environment) {
        List<Param> list = input.params();
        if (CollectionUtil.safeIsEmpty(list)) {
            return help();
        }

        // 只取第一个，后边的不管
        Param param = list.get(0);
        if (StringUtils.isNotEmpty(param.prefix())) {
            return "命令错误。" + help();
        }

        // 找到指定命令，返回帮助文档
        Command command = engine.searchCommand(param.value());

        if (command == null) {
            return StringFormater.simpleFormat("命令[{0}]不存在", param.value());
        }

        return command.help();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String help() {
        return "帮助命令，输入help [command]查看指定命令的帮助文档";
    }
}
