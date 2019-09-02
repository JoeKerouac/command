package com.joe.command.impl;

import com.joe.command.Param;

import lombok.AllArgsConstructor;

/**
 * 简单参数
 *
 * @author JoeKerouac
 * @version 2019年08月29日 15:54
 */
@AllArgsConstructor
public class SimpleParam implements Param {

    /**
     * 参数值
     */
    private final String value;

    /**
     * 参数前缀
     */
    private final String prefix;

    /**
     * 参数原值
     */
    private final String paramLine;

    @Override
    public String value() {
        return value;
    }

    @Override
    public String prefix() {
        return prefix;
    }

    @Override
    public String paramLine() {
        return paramLine;
    }
}
