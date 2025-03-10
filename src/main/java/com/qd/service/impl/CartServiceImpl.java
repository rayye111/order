package com.qd.service.impl;

import com.qd.entity.Cart;
import com.qd.mapper.CartMapper;
import com.qd.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lrr
 * @since 2024-10-16
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    @Autowired(required = false)
    CartMapper mapper;


    @Override
    public List<Cart> getList(Integer deskId) {
        return mapper.getList(deskId);
    }
}
