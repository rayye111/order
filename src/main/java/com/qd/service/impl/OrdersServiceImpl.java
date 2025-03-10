package com.qd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qd.entity.*;
import com.qd.mapper.CartMapper;
import com.qd.mapper.OrderDetailMapper;
import com.qd.mapper.OrdersMapper;
import com.qd.mapper.ProductMapper;
import com.qd.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Queue;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {


    @Autowired(required = false)
    OrdersMapper mapper;
    @Autowired(required = false)
    OrderDetailMapper orderDetailMapper;
    @Autowired(required = false)
    CartMapper cartMapper;
    @Autowired(required = false)
    ProductMapper productMapper;


    @Override
    public List<Orders> getList(OrdersDto orders) {
        return mapper.getList(orders);
    }


    @Override
    public boolean addOrderAndDetail(Orders o) {
        //1.插入1条数据到订单表中
        int insert = mapper.insert(o);
        //2.插入多条数据到详情表中
        //2.1 先查询出来准备要插入的数据（来自购物车表）
        List<Cart> list = cartMapper.getList(o.getDeskId());
        //2.2 将查询的数据，遍历，插入
        OrderDetail od = new OrderDetail();
        for (Cart c : list) {
            od.setOrderId(o.getId());
            od.setProName(c.getPname());
            od.setNum(c.getNum());
            od.setProImg(c.getImg());
            od.setProPrice(c.getPrice());
            orderDetailMapper.insert(od);

            Product product = productMapper.selectById(c.getPid());
            product.setSaleMonth(product.getSaleMonth() + c.getNum());
            productMapper.updateById(product);

        }
        //3.删除购物车表中部分数据（某桌）
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("desk_id", o.getDeskId());
        int delete = cartMapper.delete(queryWrapper);

        if (insert > 0 && delete > 0)
            return true;

        return false;

    }
}
