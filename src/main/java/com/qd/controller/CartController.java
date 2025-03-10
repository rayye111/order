package com.qd.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qd.common.result.ResultUtils;
import com.qd.common.utils.EmptyUtils;
import com.qd.entity.Cart;
import com.qd.entity.Users;
import com.qd.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService service;

    /**
     *  获取指定桌号的购物车清单
     * @param deskId
     * @return
     */
    @GetMapping("/")
    public Object getList(Integer deskId){
        List<Cart> list = service.getList(deskId);
        return ResultUtils.returnDataSuccess(list);
    }


    @DeleteMapping("/")
    public Object del(Integer deskId){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("desk_id",deskId);
        if(service.remove(queryWrapper)){
            return ResultUtils.returnSuccess();
        }
        return ResultUtils.returnFail("清空失败");
    }



    /**
     * 添加
     * @param c
     * @return
     */
    @PostMapping("/")
    public Object add(@RequestBody Cart c){
        //查询，判断是否已存在：
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("desk_id",c.getDeskId());
        queryWrapper.eq("product_id",c.getProductId());
        Cart one = service.getOne(queryWrapper);

        //已存在：修改数量，num+1
        if(EmptyUtils.isNotEmpty(one)){
            one.setNum(one.getNum() + c.getNum());
            if(one.getNum()<=0){
                //执行删除
                if (service.removeById(one)) {
                    return ResultUtils.returnDataSuccess(c);
                }
                return ResultUtils.returnDataFail("删除失败");
            }else{
                //执行修改
                if (service.updateById(one)) {
                    return ResultUtils.returnDataSuccess(c);
                }
                return ResultUtils.returnDataFail("修改数量失败");
            }
        }else { //未存在：添加
            if (service.save(c)) {
                return ResultUtils.returnDataSuccess(c);
            }
            return ResultUtils.returnFail("添加失败");
        }
    }

}
