package com.qd.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qd.common.result.ResultUtils;
import com.qd.common.utils.EmptyUtils;
import com.qd.entity.Users;
import com.qd.service.IUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/users")
public class UsersController {


    @Autowired
    IUsersService service;


    /**
     * 全查
     * @return
     */
    @GetMapping("/")
    public Object getList(){
        List<Users> list = service.list();
        return ResultUtils.returnSuccessLayui(list,list.size());
    }

    /**
     * 单查
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Object getOne(@PathVariable("id") Integer id){
        Users u = service.getById(id);
        return ResultUtils.returnDataSuccess(u);
    }

    /**
     * 添加
     * @param u
     * @return
     */
    @PostMapping("/")
    public Object add(@RequestBody Users u){
        if(service.save(u)){
            return ResultUtils.returnDataSuccess(u);
        }
        return ResultUtils.returnFail("添加失败");
    }


    /**
     * 修改
     * @param u
     * @return
     */
    @PutMapping("/")
    public Object update(@RequestBody Users u){
        if(service.updateById(u)){
            return ResultUtils.returnDataSuccess(u);
        }
        return ResultUtils.returnFail("修改失败");
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


    @PostMapping("/login")
    public Object login(@RequestParam String uname, @RequestParam String password, HttpSession session){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uname",uname);
        queryWrapper.eq("password",password);
        Users one = service.getOne(queryWrapper);
        if(EmptyUtils.isNotEmpty(one)){
            //登录成功后将用户名存储在会话中
            session.setAttribute("userName",uname);
            return ResultUtils.returnDataSuccess(one);
        }else{
            return ResultUtils.returnFail("用户名或密码错误");
        }
    }


    @GetMapping("/getCurrUser")
    public Object getCurrUser(HttpSession session){
        Object userName = session.getAttribute("userName");
        return ResultUtils.returnDataSuccess(userName);
    }

    @GetMapping("/signOut")
    public Object signOut(HttpSession session){
        session.removeAttribute("userName");
        return ResultUtils.returnSuccess();
    }


}
