package com.joe.command;

import java.util.List;
import java.util.Map;

/**
 * 命令上下文
 * 
 * @author JoeKerouac
 * @version 2019年08月29日 11:49
 */
public interface CommandContext {

    /**
     * 命令名
     * 
     * @return 命令名
     */
    String name();

    /**
     * 参数列表
     * 
     * @return 参数列表，可以为空
     */
    List<Param> params();
}
