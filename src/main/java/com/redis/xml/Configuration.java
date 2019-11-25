package com.redis.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {

    @XmlElement(name = "appender")
    private List<Appender> appenders;

    private Root root;

    @XmlElement(name = "logger")
    private List<Logger> loggers;

    public List<Appender> getAppenders() {
        return appenders;
    }

    public void setAppenders(List<Appender> appenders) {
        this.appenders = appenders;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public List<Logger> getLoggers() {
        return loggers;
    }

    public void setLoggers(List<Logger> loggers) {
        this.loggers = loggers;
    }
}
