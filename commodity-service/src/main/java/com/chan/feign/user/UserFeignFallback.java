package com.chan.feign.user;

import org.springframework.stereotype.Component;

/**
 * @Auther: Chan
 * @Date: 2019/12/19 20:01
 * @Description:
 */
@Component
public class UserFeignFallback implements UserFeign {
    @Override
    public String login(String value) {
        return "触发熔断..";
    }
}
