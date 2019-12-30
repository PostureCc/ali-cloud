package com.chan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Auther: Chan
 * @Date: 2019/11/21 11:31
 * @Description:
 */
@Configuration
public class RestTemplateConfiguration {

    @Autowired
    private OkHttpConfig okHttpConfig;

    @Bean
    // TODO: 2019/12/30 使用该注解实现负载均衡的前提是 需要将调用的服务注册到注册中心中 然后名称调用 如果直接写IP地址 会找不到实例的
    //@LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        //RestTemplate 配置OkHttp连接池
        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory(okHttpConfig.okHttpClient()));
        return restTemplate;
    }

    /**
     * get根据url获取对象
     */
    public String get(String url) {
        return restTemplate().getForObject(url, String.class, new Object[]{});
    }

    /**
     * 发送post请求,请求数据为json格式
     *
     * @param <T>
     * @param url  请求URL地址
     * @param data json串
     */
    public <T> T post(String url, String data, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        // 设置请求头
        headers.add("Accept", "application/json");
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json;charset=UTF-8");
//        log.info("************postByJson************\n url:{} \ndata:{}", url, data);
        return restTemplate().postForObject(url, new HttpEntity<String>(data, headers), clazz);
    }

    /**
     * post提交对象
     */
    public String post(String url, String data) {
        return restTemplate().postForObject(url, null, String.class, data);
    }

    /**
     * put修改对象
     */
    public void put(String url, String data) {
        restTemplate().put(url, null, data);
    }

    /**
     * delete根据ID删除对象
     */
    public void delete(String url, String id) {
        restTemplate().delete(url, id);
    }

}
