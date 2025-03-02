package com.meiqiu.feign;

import com.meiqiu.返回封装.BizResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description
 * @Author sgh
 * @Date 2025/3/2
 * @Time 21:02
 */
@FeignClient(value = "meiqiu-service",path = "/api/meiqiu")
public interface MeiqiuServiceFeign {

    @ApiOperation(value = "异步生效测试")
    @RequestMapping(value = "/async/test", method = RequestMethod.POST)
    BizResult<Boolean> async();

}
