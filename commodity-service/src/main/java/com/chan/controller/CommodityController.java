package com.chan.controller;

import com.chan.feign.user.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chan
 * @Date: 2019/12/19 19:59
 * @Description:
 */
@RestController
public class CommodityController {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/commodity")
    public String commodity(@RequestParam("value") String value) {
        return value;
    }

    @GetMapping("/feign")
    public String feign(@RequestParam("value") String value) {
        return userFeign.login(value);
    }

}
