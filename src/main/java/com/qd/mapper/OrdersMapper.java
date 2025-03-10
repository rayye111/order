package com.qd.mapper;

import com.qd.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qd.entity.OrdersDto;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liruirui
 * @since 2024-10-18
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    List<Orders> getList(OrdersDto orders);
}
