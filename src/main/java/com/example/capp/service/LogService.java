package com.example.capp.service;

import com.example.capp.dto.Log;
import com.github.pagehelper.PageInfo;

public interface LogService {
    /**
     * 添加日志
     * @param action    触发动作
     * @param data      产生数据
     * @param ip        产生IP
     * @param authorId  产生人
     */
    void addLog(String action, String data, String ip, Long authorId);

    /**
     * 获取日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Log> getLogs(int pageNum, int pageSize);
}
