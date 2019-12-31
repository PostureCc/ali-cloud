package com.chan.feign.user;

import chan.model.VO.TokenVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: Chan
 * @Date: 2019/12/19 20:00
 * @Description:
 */
@FeignClient(value = "user-service", fallback = UserFeignFallback.class)
public interface UserFeign {

    @GetMapping("/login")
    String login(@RequestParam("value") String value);

    /**
     * @SpringQueryMap :SpringCloud2.1.X 以上才有此注解
     * 如果不允许升级到 可以配合@RequestParams注解做单个传参调用接口
     */
    @GetMapping("/feignObj")
    TokenVO feignObj(@SpringQueryMap TokenVO tokenVO);

}
