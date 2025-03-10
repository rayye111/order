package com.qd.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.common.result.ResultUtils;
import com.qd.common.utils.EmptyUtils;
import com.qd.entity.Desk;
import com.qd.entity.Product;
import com.qd.service.IDeskService;
import com.qd.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService service;

    /**
     * 多条件分页查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/")
    public Object getList(@RequestParam Integer page, @RequestParam Integer limit,Product product){
        //封装分页对象
        IPage p = new Page(page,limit);

        //封装查询条件
        QueryWrapper queryWrapper = new QueryWrapper();
        if(EmptyUtils.isNotEmpty(product.getPname())) {
            queryWrapper.like("pname", product.getPname());
        }
        if(EmptyUtils.isNotEmpty(product.getTid())) {
            queryWrapper.eq("tid", product.getTid());
        }
        queryWrapper.orderByDesc("weight");


        //调用服务层的方法进行分页查询、条件查询
        IPage res = service.page(p,queryWrapper);
        //从查询结果中取中当前页的数据
        List list = res.getRecords();
        //从查询结果中取中总条数
        int total = (int)res.getTotal();
        //返回数据
        return ResultUtils.returnSuccessLayui(list,total);
    }


    /**
     *  前台首页获取上架商品，且按照权重排序
     * @return
     */
    @GetMapping("/getAllList")
    public Object getList(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("sale_on_off",1);
        queryWrapper.orderByDesc("weight");
        return ResultUtils.returnDataSuccess(service.list(queryWrapper));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/")
    public Object del(@RequestParam Integer id){
        if(service.removeById(id)){
            return ResultUtils.returnSuccess();
        }
        return ResultUtils.returnFail("删除失败");
    }


    /**
     * 添加
     * @param p
     * @return
     */
    @PostMapping("/")
    public Object add(@RequestBody Product p){
        if(service.save(p)){
            return ResultUtils.returnDataSuccess(p);
        }
        return ResultUtils.returnFail("添加失败");
    }

    /**
     * 修改
     * @param p
     * @return
     */
    @PutMapping("/")
    public Object update(@RequestBody Product p){
        if(service.updateById(p)){
            return ResultUtils.returnDataSuccess(p);
        }
        return ResultUtils.returnFail("修改失败");
    }




}
