package com.example.capp.dto;

import java.util.Date;

/***
 * tl_log 日志表
 */
public class Log {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 产生的动作
     */
    private String action;

    /**
     * 产生的数据
     */
    private String data;

    /**
     * 发生人id
     */
    private Long authorId;

    /**
     * 日志产生的ip
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date creationDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", data='" + data + '\'' +
                ", authorId=" + authorId +
                ", ip='" + ip + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}

