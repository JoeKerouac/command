package com.joe.command.impl;


import com.joe.command.Environment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简单环境上下文
 * 
 * @author JoeKerouac
 * @version 2019年08月29日 11:08
 */
public class SimpleEnvironment implements Environment {

    private Map<String, Object> container = new ConcurrentHashMap<>();


    @Override
    public Map<String, Object> getAll() {
        return container;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getEnv(String key) {
        return (T)container.get(key);
    }

    @Override
    public void put(String key, Object value) {
        container.put(key, value);
    }
}
