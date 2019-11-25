package com.redis.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.StringJoiner;

@XmlAccessorType(XmlAccessType.FIELD)
public class Logger {

    @XmlAttribute
    private String level;

    @XmlAttribute
    private String name;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Logger.class.getSimpleName() + "[", "]")
                .add("level='" + level + "'")
                .add("name='" + name + "'")
                .toString();
    }
}
