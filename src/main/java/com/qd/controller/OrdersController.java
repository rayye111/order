package com.qd.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.qd.common.result.ResultUtils;
import com.qd.entity.OrderDetail;
import com.qd.entity.Orders;
import com.qd.entity.OrdersDto;
import com.qd.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    IOrdersService service;


    /**
     * 多条件、分页、多表连接，查询
     * @param orders
     * @return
     */
    @GetMapping("/")
    public Object getList(OrdersDto orders){
        List<Orders> list =  service.getList(orders); //当前页的数据
        orders.setPage(null);
        orders.setLimit(null);
        int total =  service.getList(orders).size(); //总条数
        return ResultUtils.returnSuccessLayui(list,total);
    }


    /**
     * 修改
     * @param o
     * @return
     */
    @PutMapping("/")
    public Object update(@RequestBody Orders o){
        if(service.updateById(o)){
            return ResultUtils.returnDataSuccess(o);
        }
        return ResultUtils.returnFail("修改失败");
    }


    /**
     * 添加
     * @param o
     * @return
     */
    @PostMapping("/")
    public Object add(@RequestBody Orders o){
        if(service.addOrderAndDetail(o)){
            return ResultUtils.returnSuccess();
        }
        return ResultUtils.returnFail("添加失败");
    }



}
