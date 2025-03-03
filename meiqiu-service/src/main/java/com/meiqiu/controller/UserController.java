package com.meiqiu.controller;

import com.meiqiu.dto.LoginDTO;
import com.meiqiu.dto.RegisterUserDTO;
import com.meiqiu.service.UserService;
import com.meiqiu.vo.LoginVo;
import com.meiqiu.base.BizResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Description 用户接口
 * @Author sgh
 * @Date 2025/1/17
 * @Time 14:16
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "注册")
    @ResponseBody
    public BizResult<LoginVo> register(@RequestBody @Valid RegisterUserDTO request) {
        return BizResult.success(userService.register(request));
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    @ResponseBody
    public BizResult<LoginVo> login(@RequestBody @Valid LoginDTO loginDTO) {
        return BizResult.success(userService.login(loginDTO));
    }

}
