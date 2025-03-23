package com.zhangzeyuan.learnarithmetic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhangzeyuan.learnarithmetic.entity.SmsLabelListPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author zeyuan zhang
 * @Date 2025/3/23 下午3:32
 * @Version 1.0
 */

public interface SmsLabelListPojoMapper extends BaseMapper<SmsLabelListPojo> {
     List<SmsLabelListPojo> tree(@Param("parentId") String parentId);
}
