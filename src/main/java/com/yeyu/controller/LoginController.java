package com.yeyu.controller;

import com.yeyu.pojo.Admin;
import com.yeyu.pojo.AdminLoginParam;
import com.yeyu.pojo.RespBean;
import com.yeyu.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author 13474
 * @Package com.yeyu.controller
 * @date 2021/11/1419:09
 */
@Api(tags = "LoginController")
public class LoginController {
    @Autowired
    private IAdminService adminService;
    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request);
    }
    @ApiOperation(value = "获取当前用户信息")
    @PostMapping("/admin/info")
    public Admin getAdmininfo(Principal principal){
        if (null == principal){
            return null;
        }
        String username = principal.getName();
        Admin admin =  adminService.getAdminusername(username);
        admin.setPassword(null);
        return admin;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("退出成功");
    }
}
