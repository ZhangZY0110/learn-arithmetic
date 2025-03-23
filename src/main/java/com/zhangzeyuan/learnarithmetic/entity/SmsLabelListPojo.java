package com.zhangzeyuan.learnarithmetic.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
@Data
@TableName("sms_label_list")
public class SmsLabelListPojo {

  private String  id;
  private String parentId;
  @TableField(typeHandler = JacksonTypeHandler.class)
  private JsonArray parentList;
  private Integer status;
  private String title;
  private String description;



}
