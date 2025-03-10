package com.qd.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qd.common.result.ResultUtils;
import com.qd.entity.OrderDetail;
import com.qd.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {


    @Autowired
    IOrderDetailService service;

    @GetMapping("/")
    public Object getList(Integer orderId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_id",orderId);  //第一个参数是数据库表中的字段名，
        List<OrderDetail> list = service.list(queryWrapper);
        return ResultUtils.returnSuccessLayui(list,list.size());
    }

}
