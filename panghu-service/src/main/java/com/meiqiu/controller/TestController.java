package com.meiqiu.controller;

import cn.hutool.json.JSONObject;
import com.meiqiu.feign.MeiqiuServiceFeign;
import com.meiqiu.返回封装.BizResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author sgh
 * @Date 2025/3/2
 * @Time 21:21
 */
@RestController
public class TestController {

    @Autowired
    private MeiqiuServiceFeign meiqiuServiceFeign;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        BizResult<Boolean> aa = meiqiuServiceFeign.async();
        System.out.println(new JSONObject(aa));
        return "success";
    }

}
