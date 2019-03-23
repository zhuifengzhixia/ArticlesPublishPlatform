package com.example.capp.service.impl;

import com.example.capp.dto.Log;
import com.example.capp.mapper.LogMapper;
import com.example.capp.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void addLog(String action, String data, String ip, Long authorId) {
        Log log = new Log();
        log.setAuthorId(authorId);
        log.setIp(ip);
        log.setData(data);
        log.setAction(action);
        log.setCreationDate(new Date());
        logMapper.addLog(log);
    }

    @Override
    public PageInfo<Log> getLogs(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Log> logs = logMapper.getLogs();
        PageInfo<Log> pageInfo = new PageInfo<>(logs);
        return pageInfo;
    }
}
