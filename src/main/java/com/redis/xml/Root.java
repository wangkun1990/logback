package com.redis.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class Root {

    @XmlAttribute
    private String level;

    @XmlElement(name = "appender-ref")
    private AppenderRef appenderRef;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public AppenderRef getAppenderRef() {
        return appenderRef;
    }

    public void setAppenderRef(AppenderRef appenderRef) {
        this.appenderRef = appenderRef;
    }
}
