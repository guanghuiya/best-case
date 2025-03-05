package com.meiqiu.feign;

import com.meiqiu.base.BizResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description
 * @Author sgh
 * @Date 2025/3/2
 * @Time 21:02
 */
 @FeignClient(name = "meiqiu-service")
// @FeignClient(name = "meiqiu-service",url = "http://localhost:8080")
public interface MeiqiuServiceFeign {

    @ApiOperation(value = "异步生效测试")
    @RequestMapping(value = "/api/meiqiu/async/test", method = RequestMethod.POST)
    BizResult<Boolean> async();

}
