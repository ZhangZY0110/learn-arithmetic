package com.zhangzeyuan.learnarithmetic.controller;

import com.zhangzeyuan.learnarithmetic.entity.SmsLabelListPojo;
import com.zhangzeyuan.learnarithmetic.mapper.SmsLabelListPojoMapper;
import com.zhangzeyuan.learnarithmetic.vo.SmsLabelListPojoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author zeyuan zhang
 * @Date 2025/3/23 下午3:33
 * @Version 1.0
 */
@RestController("/sms")
public class SmsLableController {
    @Autowired
    private SmsLabelListPojoMapper smsLabelListMapper;
    @RequestMapping(method = RequestMethod.GET,value = "/tree/{parentId}")
    public SmsLabelListPojoVo tree(String parentId){
        List<SmsLabelListPojo> tree = smsLabelListMapper.tree(parentId);
        //1. 封装第一层，
        SmsLabelListPojoVo vo = new SmsLabelListPojoVo();
        Iterator<SmsLabelListPojo> iterator = tree.iterator();

        while (iterator.hasNext()) {
            SmsLabelListPojo next = iterator.next();
            //传入的父id等等于id 设置本值
            if (StringUtils.hasText(parentId) && parentId.trim().equals(next.getId())){
                //执行封装
                BeanUtils.copyProperties(next, vo);
                iterator.remove();
                //直接退出当前循环
                // break;
            }
        }
        //做递归
        //返回以后需要继续循环，需要携带tree这个大集合，还需要 当前对象的 id值 ,还有vo对象。
        setChildLabel(tree, parentId, vo);


        return vo;
    }

    /**
     * 这个是一个递归方法
     * @param tree 这个tree永远是一个， 但是在方法内部的实现使用了数组拷贝 ，不然会抛 ConcurrentModificationException
     * @param parentId 这个是本次要比对的父id
     * @param vo 这个vo对象，第一次是最大的那个vo，setChild 的时候是第一层，孙子对象一定要传入的是父对象，而不是爷爷对象
     */
    private void setChildLabel(List<SmsLabelListPojo> tree,String parentId,SmsLabelListPojoVo vo){
        ArrayList<SmsLabelListPojo> list = new ArrayList();
        list.addAll(tree);
        System.out.println("传入的 pid："+parentId);
        //2.继续封装下一层
        //创建第一层子list ------- SON ---------->>、
        ArrayList<SmsLabelListPojoVo> sons = new ArrayList<>();
        for (Iterator<SmsLabelListPojo> it = list.iterator(); it.hasNext();){
            SmsLabelListPojo next = it.next();
            //判断第一层子 id 等于传进来的 parentId\
            // System.out.printf("next.getParentId():%s%n",next.getParentId());
            // System.out.printf("parentId:%s%n",parentId);
            if ((next.getParentId()).equals(parentId)){
                //执行第一层子插入 --------------- init obj
                SmsLabelListPojoVo sonVo = new SmsLabelListPojoVo();
                //------------------copy
                BeanUtils.copyProperties(next,sonVo);
                // 执行插入 -------------addList
                sons.add(sonVo);
                //对当前数据执行删除，减少迭代次数。 -------------remove
                it.remove();
                //孙类递归
                String sonId = next.getId();
                //执行递归， 与之前不同的是, 这次传入的id是当前对象的 id 让其继续遍历，判断其是否还有子集合，如有继续setChildren
                //这里的vo对象应该是当前对象的vo ，而不是父传来的vo，这个是子列表的vo，
                setChildLabel(list, sonId, sonVo);
            }
        }
        // 完成插入    <<------- SON ---------- 一定要在遍历的外面插入 初始化集合也是在外面的
        vo.setChildren(sons);
    }
}
