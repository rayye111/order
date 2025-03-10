package com.qd.mapper;

import com.qd.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liruirui
 * @since 2024-10-18
 */
public interface CartMapper extends BaseMapper<Cart> {
    @Select("select cart.* ,product.id as pid,pname,price,old_price,img from cart,product where cart.product_id = product.id and desk_id= #{deskId}")
    List<Cart> getList(Integer deskId);
}
