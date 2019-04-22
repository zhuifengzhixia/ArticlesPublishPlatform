
package com.example.capp.service.site;

import com.example.capp.dto.StatisticsDto;
import com.example.capp.model.CommentDomain;
import com.example.capp.model.ContentDomain;

import java.util.List;

/**
 * 网站相关Service接口
 */
public interface SiteService {

    /**
     * 获取评论列表
     * @param limit
     * @return
     */
    List<CommentDomain> getComments(int limit);

    /**
     * 获取文章列表
     * @param limit
     * @return
     */
    List<ContentDomain> getNewArticles(int limit);

    /**
     * 获取后台统计数
     * @return
     */
    StatisticsDto getStatistics();

    /**
     * 根据用户id获取评论列表
     * @param uid
     * @param limit
     * @return
     */
    List<CommentDomain> getCommentsByAuthorId(Integer uid, int limit);
}
