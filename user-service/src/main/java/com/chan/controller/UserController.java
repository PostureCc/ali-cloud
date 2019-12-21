package com.chan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chan
 * @Date: 2019/12/19 18:31
 * @Description:
 */
@RestController
public class UserController {

    @GetMapping("/login")
    public String login(@RequestParam("value") String value) {
        return value;
    }

}
