package com.joe.command.impl;

import com.joe.command.CommandContext;
import com.joe.command.Param;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * 简单命令上下文
 *
 * @author JoeKerouac
 * @version 2019年08月29日 16:01
 */
@AllArgsConstructor
public class SimpleCommandContext implements CommandContext {

    /**
     * 命令名
     */
    private final String      name;

    /**
     * 参数列表
     */
    private final List<Param> params;

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<Param> params() {
        return params;
    }
}
