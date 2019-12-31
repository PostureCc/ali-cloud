package com.chan.controller;

import chan.model.VO.TokenVO;
import com.chan.config.RestTemplateConfiguration;
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

    @Autowired
    private RestTemplateConfiguration restTemplateConfiguration;

    @GetMapping("/commodity")
    public String commodity(@RequestParam("value") String value) {
        return value;
    }

    @GetMapping("/feign")
    public String feign(@RequestParam("value") String value) {
        return userFeign.login(value);
    }

    @GetMapping("/feignObj")
    public TokenVO feignObj(TokenVO tokenVO) {
        return userFeign.feignObj(tokenVO);
    }

    @GetMapping("/rest")
    public String rest(@RequestParam("value") String value) {
        return restTemplateConfiguration.get(String.format("http://127.0.0.1:8085/login?value=%s", value));
//        return userFeign.login(value);
    }

}
