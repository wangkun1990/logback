package com.redis.mq;

import java.io.Serializable;

/**
 * 通过订阅实现
 * 由于是使用的windows版本的redis，需要修改配置文件
 * redis.windows-service.conf
 * notify-keyspace-events
 */
public class RedisMessageListener {

    private volatile Object object;

    public void handleMessage(Serializable message) {
        this.object = message;
    }

    public Object getObject() {
        return this.object;
    }
}
