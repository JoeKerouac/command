package com.joe.command;

/**
 * 命令行参数
 *
 * @author JoeKerouac
 * @version 2019年08月29日 15:51
 */
public interface Param {

    /**
     * 参数值
     * @return 参数值
     */
    String value();

    /**
     * 参数前缀，例如-、--
     * @return 参数前缀，可以为空
     */
    String prefix();

    /**
     * 参数原值
     * @return
     */
    String paramLine();
}
