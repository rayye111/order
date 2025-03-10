package com.qd.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.common.result.ResultUtils;
import com.qd.entity.Desk;
import com.qd.entity.Users;
import com.qd.service.IDeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/desk")
public class DeskController {

    @Autowired
    IDeskService service;

    /**
     * 分页查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/")
    public Object getList(@RequestParam Integer page,@RequestParam Integer limit){
        //封装分页对象
        IPage p = new Page(page,limit);
        //调用服务层的方法进行分页查询
        IPage res = service.page(p);
        //从查询结果中取中当前页的数据
        List list = res.getRecords();
        //从查询结果中取中总条数
        int total = (int)res.getTotal();
        //返回数据
        return ResultUtils.returnSuccessLayui(list,total);
    }


    /**
     * 全查
     * @return
     */
    @GetMapping("/getAllList")
    public Object getList(){
        return ResultUtils.returnDataSuccess(service.list());
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
     * @param desk
     * @return
     */
    @PostMapping("/")
    public Object add(@RequestBody Desk desk){
        if(service.save(desk)){
            return ResultUtils.returnDataSuccess(desk);
        }
        return ResultUtils.returnFail("添加失败");
    }

    /**
     * 修改
     * @param desk
     * @return
     */
    @PutMapping("/")
    public Object update(@RequestBody Desk desk){
        if(service.updateById(desk)){
            return ResultUtils.returnDataSuccess(desk);
        }
        return ResultUtils.returnFail("修改失败");
    }



}
