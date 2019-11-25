package com.redis.entity;

import java.io.Serializable;
import java.util.StringJoiner;

public class Logger implements Serializable {

    /**
     * 配置的全类名
     */
    private String fullClassName;

    /**
     * 全类名的日志级别
     */
    private String level;


    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Logger.class.getSimpleName() + "[", "]")
                .add("fullClassName='" + fullClassName + "'")
                .add("level='" + level + "'")
                .toString();
    }
}
