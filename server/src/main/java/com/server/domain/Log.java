package com.server.domain;

import java.util.Date;

public class Log {
    private String id;

    private String excName;

    private String excMessage;

    private String operUserId;

    private String operUserName;

    private String operMethod;

    private String operUri;

    private String operIp;

    private String operVer;

    private Date createdAt;

    private String operModul;

    private String operType;

    private String operDesc;

    private String requiredMethod;

    private String requiredParam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExcName() {
        return excName;
    }

    public void setExcName(String excName) {
        this.excName = excName;
    }

    public String getExcMessage() {
        return excMessage;
    }

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage;
    }

    public String getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(String operUserId) {
        this.operUserId = operUserId;
    }

    public String getOperUserName() {
        return operUserName;
    }

    public void setOperUserName(String operUserName) {
        this.operUserName = operUserName;
    }

    public String getOperMethod() {
        return operMethod;
    }

    public void setOperMethod(String operMethod) {
        this.operMethod = operMethod;
    }

    public String getOperUri() {
        return operUri;
    }

    public void setOperUri(String operUri) {
        this.operUri = operUri;
    }

    public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp;
    }

    public String getOperVer() {
        return operVer;
    }

    public void setOperVer(String operVer) {
        this.operVer = operVer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getOperModul() {
        return operModul;
    }

    public void setOperModul(String operModul) {
        this.operModul = operModul;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
    }

    public String getOperDesc() {
        return operDesc;
    }

    public void setOperDesc(String operDesc) {
        this.operDesc = operDesc;
    }

    public String getRequiredMethod() {
        return requiredMethod;
    }

    public void setRequiredMethod(String requiredMethod) {
        this.requiredMethod = requiredMethod;
    }

    public String getRequiredParam() {
        return requiredParam;
    }

    public void setRequiredParam(String requiredParam) {
        this.requiredParam = requiredParam;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", excName=").append(excName);
        sb.append(", excMessage=").append(excMessage);
        sb.append(", operUserId=").append(operUserId);
        sb.append(", operUserName=").append(operUserName);
        sb.append(", operMethod=").append(operMethod);
        sb.append(", operUri=").append(operUri);
        sb.append(", operIp=").append(operIp);
        sb.append(", operVer=").append(operVer);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", operModul=").append(operModul);
        sb.append(", operType=").append(operType);
        sb.append(", operDesc=").append(operDesc);
        sb.append(", requiredMethod=").append(requiredMethod);
        sb.append(", requiredParam=").append(requiredParam);
        sb.append("]");
        return sb.toString();
    }
}