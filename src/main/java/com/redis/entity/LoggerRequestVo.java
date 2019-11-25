package com.redis.entity;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

public class LoggerRequestVo implements Serializable {

    private List<Logger> loggers;

    public List<Logger> getLoggers() {
        return loggers;
    }

    public void setLoggers(List<Logger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LoggerRequestVo.class.getSimpleName() + "[", "]")
                .add("loggers=" + loggers)
                .toString();
    }
}
