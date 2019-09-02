package com.joe.command.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.joe.command.CommandContext;
import com.joe.command.CommandParser;
import com.joe.command.Param;
import com.joe.utils.common.string.StringUtils;

/**
 * 默认命令解析器
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
                list.add(new SimpleParam(value,
                    count == 0 ? null : StringUtils.copy(START_SYMBOL, count), param));
            }
        } else {
            list = Collections.emptyList();
        }
        return new SimpleCommandContext(commandName, list);
    }

}
