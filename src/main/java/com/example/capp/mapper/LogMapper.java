package com.example.capp.mapper;

import com.example.capp.dto.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    /**
     * 添加日志
     * @param log
     * @return
     */
    int addLog(Log log);

    /**
     * 获取日志
     * @return
     */
    List<Log> getLogs();
}
