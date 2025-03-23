package com.zhangzeyuan.learnarithmetic;

import com.zhangzeyuan.learnarithmetic.controller.SmsLableController;
import com.zhangzeyuan.learnarithmetic.vo.SmsLabelListVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zeyuan zhang
 * @Date 2025/3/23 下午4:43
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class SmsLableControllerTest {
    @Autowired
    SmsLableController smsLableController;
    @Test
    void tree() {
        SmsLabelListVo tree = smsLableController.tree("1000000000000000001");
        //得到了当前父id的素有子和孙节点
        System.out.println(tree.toString());
    }
}