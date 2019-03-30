
package com.example.capp.model;

/**
 * 文章关联信息表
 */
public class RelationShipDomain {

    /**
     * 文章主键
     */
    private Integer cid;

    /**
     * 项目编号
     */
    private Integer mid;

    private Integer authorId;


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
