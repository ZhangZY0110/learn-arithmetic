package com.zhangzeyuan.learnarithmetic.vo;

import lombok.Data;

import java.util.ArrayList;

/**
 * @Author zeyuan zhang
 * @Date 2025/3/23 下午3:37
 * @Version 1.0
 */
@Data
public class SmsLabelListPojoVo {
    private String id;
    private String parentId;
    private Integer status;
    private String title;
    private String description;
    private ArrayList<SmsLabelListPojoVo> children;
}
