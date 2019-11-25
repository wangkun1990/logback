package com.redis.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class RollingPolicy {

    @XmlAttribute(name = "class")
    private String rollingPolicyFullClassName;

    @XmlElement(name = "FileNamePattern")
    private String fileNamePattern;
    @XmlElement
    private String maxFileSize;
    @XmlElement
    private String maxHistory;
    @XmlElement
    private String totalSizeCap;

    public String getRollingPolicyFullClassName() {
        return rollingPolicyFullClassName;
    }

    public void setRollingPolicyFullClassName(String rollingPolicyFullClassName) {
        this.rollingPolicyFullClassName = rollingPolicyFullClassName;
    }

    public String getFileNamePattern() {
        return fileNamePattern;
    }

    public void setFileNamePattern(String fileNamePattern) {
        this.fileNamePattern = fileNamePattern;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public String getMaxHistory() {
        return maxHistory;
    }

    public void setMaxHistory(String maxHistory) {
        this.maxHistory = maxHistory;
    }

    public String getTotalSizeCap() {
        return totalSizeCap;
    }

    public void setTotalSizeCap(String totalSizeCap) {
        this.totalSizeCap = totalSizeCap;
    }
}
