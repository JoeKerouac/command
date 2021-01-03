package com.joe.command.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.joe.command.CommandContext;
import com.joe.command.CommandParser;
import com.joe.command.Param;
import com.joe.utils.common.string.StringUtils;

/**
 * 默认命令解析器，会将命令参数按照空白符进行分割，同时如果命令前边有-符号将会被处理为前缀，例如对于命令：rm -rf home，会解析出来命令为 rm，参数为两个，一个是rf，一个是home，其中rf参数的前缀是-
 *
 * @author JoeKerouac
 * @version 2019年08月29日 13:56
 */
public class CommandParserImpl implements CommandParser {

    /**
     * 参数起始符号
     */
    private static final String START_SYMBOL = "-";

    @Override
    public CommandContext parse(String commandStr) {
        String[] commandLine = commandStr.trim().split("\\s");
        String commandName = commandLine[0].trim();

        List<Param> list;

        if (commandLine.length > 1) {
            list = new ArrayList<>(commandLine.length - 1);
            for (int i = 1; i < commandLine.length; i++) {
                final String param = commandLine[i];
                String value = param;
                int count = 0;
                if (value.startsWith(START_SYMBOL)) {
                    count += 1;
                    value = value.substring(1);
                }
                list.add(new SimpleParam(value, count == 0 ? null : StringUtils.copy(START_SYMBOL, count), param));
            }
        } else {
            list = Collections.emptyList();
        }
        return new SimpleCommandContext(commandName, list);
    }

}
