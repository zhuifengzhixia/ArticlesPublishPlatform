
package com.example.capp.service.attach;

import com.example.capp.dto.AttAchDto;
import com.example.capp.model.AttAchDomain;
import com.github.pagehelper.PageInfo;

/**
 * 文件相关接口
 */
public interface AttAchService {

    /**
     * 添加单个附件信息
     * @param attAchDomain
     */
    void addAttAch(AttAchDomain attAchDomain);

    /**
     * 获取所有的附件信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<AttAchDto> getAtts(int pageNum, int pageSize);

    /**
     * 通过ID获取附件信息
     * @param id
     * @return
     */
    AttAchDto getAttAchById(Integer id);

    /**
     * 通过ID删除附件信息
     * @param id
     */
    void deleteAttAch(Integer id);
}
