package com.chan.feign.user;

import org.springframework.cloud.openfeign.FeignClient;
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

}
