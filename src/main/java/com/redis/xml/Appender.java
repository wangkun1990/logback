package com.redis.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 如果不添加@XmlAccessorType(XmlAccessType.FIELD)
 * 需要把@XmlAttribute放到方法上面，否则报类的两个属性具有相同名称 "name"错误
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Appender {

    @XmlElement(name = "File")
    private String file;

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "class")
    private String fullClassName;

    @XmlElement
    private Encoder encoder;

    @XmlElement(name = "rollingPolicy")
    private RollingPolicy rollingPolicy;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullClassName() {
        return fullClassName;
    }

    public void setFullClassName(String fullClassName) {
        this.fullClassName = fullClassName;
    }

    public Encoder getEncoder() {
        return encoder;
    }

    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public RollingPolicy getRollingPolicy() {
        return rollingPolicy;
    }

    public void setRollingPolicy(RollingPolicy rollingPolicy) {
        this.rollingPolicy = rollingPolicy;
    }
}
