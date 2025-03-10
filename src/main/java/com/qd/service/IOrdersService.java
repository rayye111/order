package com.qd.service;

import com.qd.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qd.entity.OrdersDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IOrdersService extends IService<Orders> {

    List<Orders> getList(OrdersDto orders);

    boolean addOrderAndDetail(Orders o);
}
