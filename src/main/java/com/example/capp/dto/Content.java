package com.example.capp.dto;

/**
 * tl_content 文章表
 */
public class Content {

    /**
     * 文章的主键id
     */
    private Long id;

    /**
     * 文章的标题
     */
    private String title;

    /**
     * 标题图片
     */
    private String titlePic;

    /**
     * 内容缩略名
     */
    private String slug;

    /**
     * 内容文字
     */
    private String content;

    /**
     * 内容所属用户id
     */
    private Long authorId;

    /**
     * 内容类别
     */
    private String type;

    /**
     * 内容状态
     */
    private String status;

    /**
     * 标签列表
     */
    private String tags;

    /**
     * 分类列表
     */
    private String categories;

    /**
     * 点击次数
     */
    private Long hits;

    /**
     * 内容所属评论数
     */
    private Long commentsNum;

    /**
     * 是否允许评论
     */
    private Integer allowComment;

    /**
     * 是否允许ping
     */
    private Integer allowPing;

    /**
     * 允许出现在聚合中
     */
    private Integer allowFeed;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlePic() {
        return titlePic;
    }

    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Long getHits() {
        return hits;
    }

    public void setHits(Long hits) {
        this.hits = hits;
    }

    public Long getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Long commentsNum) {
        this.commentsNum = commentsNum;
    }

    public Integer getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Integer allowComment) {
        this.allowComment = allowComment;
    }

    public Integer getAllowPing() {
        return allowPing;
    }

    public void setAllowPing(Integer allowPing) {
        this.allowPing = allowPing;
    }

    public Integer getAllowFeed() {
        return allowFeed;
    }

    public void setAllowFeed(Integer allowFeed) {
        this.allowFeed = allowFeed;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", titlePic='" + titlePic + '\'' +
                ", slug='" + slug + '\'' +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", tags='" + tags + '\'' +
                ", categories='" + categories + '\'' +
                ", hits=" + hits +
                ", commentsNum=" + commentsNum +
                ", allowComment=" + allowComment +
                ", allowPing=" + allowPing +
                ", allowFeed=" + allowFeed +
                '}';
    }
}
