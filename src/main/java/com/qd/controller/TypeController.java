package com.qd.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.common.result.ResultUtils;
import com.qd.entity.Desk;
import com.qd.entity.Type;
import com.qd.entity.Users;
import com.qd.service.ITypeService;
import com.sun.deploy.ref.AppModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    ITypeService service;

    /**
     * 全查
     * @return
     */
    @GetMapping("/")
    public Object getList(){
        List<Type> list = service.list();
        return ResultUtils.returnSuccessLayui(list,list.size());
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
     * @param t
     * @return
     */
    @PostMapping("/")
    public Object add(@RequestBody Type t){
        if(service.save(t)){
            return ResultUtils.returnDataSuccess(t);
        }
        return ResultUtils.returnFail("添加失败");
    }

    /**
     * 修改
     * @param t
     * @return
     */
    @PutMapping("/")
    public Object update(@RequestBody Type t){
        if(service.updateById(t)){
            return ResultUtils.returnDataSuccess(t);
        }
        return ResultUtils.returnFail("修改失败");
    }

}
