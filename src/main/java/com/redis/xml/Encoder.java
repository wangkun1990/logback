package com.redis.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Encoder {

    @XmlAttribute(name = "class")
    private String encoderFullClassName;

    @XmlElement(name = "Pattern")
    private String pattern;

    public String getEncoderFullClassName() {
        return encoderFullClassName;
    }

    public void setEncoderFullClassName(String encoderFullClassName) {
        this.encoderFullClassName = encoderFullClassName;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
