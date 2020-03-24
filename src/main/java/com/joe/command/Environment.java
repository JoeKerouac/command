package com.joe.command;

import java.util.Map;

/**
 * 命令环境（全局）
 * 
 * @author JoeKerouac
 * @version 2019年08月29日 16:05
 */
public interface Environment {

    /**
     * 获取所有环境配置
     * @return 所有环境配置
     */
    Map<String, Object> getAll();

    /**
     * 获取指定key对应的环境配置
     * @param key key
     * @param <T> 环境配置类型
     * @return 环境配置
     */
    <T> T getEnv(String key);

    /**
     * 往当前环境放置数据
     * @param key key
     * @param value value
     */
    void put(String key, Object value);

    /**
     * 如果不存在则放入
     * @param key 要放入的key
     * @param value 要放入的value
     * @return 旧值
     */
    Object putIfAbsent(String key, Object value);
}
