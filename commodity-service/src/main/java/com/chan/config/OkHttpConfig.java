package com.chan.config;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: Chan
 * @Date: 2019/12/25 11:14
 * @Description: 配置 oKHttp 与连接池
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class OkHttpConfig {

    // 默认老外留给你彩蛋中文乱码，加上它就 OK
//    @Bean
//    public Encoder encoder() {
//        return (Encoder) new FormEncoder();
//    }

    @Bean
    @PostConstruct
    public OkHttpClient okHttpClient() {
        Dispatcher dispatcher = new Dispatcher();
        //最大并发请求数为64
        dispatcher.setMaxRequests(64);
        //每个主机最大的请求数 默认值为5
        dispatcher.setMaxRequestsPerHost(8);

        return new OkHttpClient.Builder()
                //设置连接超时
                .connectTimeout(180, TimeUnit.SECONDS)
                //设置读超时 五分钟
                .readTimeout(300, TimeUnit.SECONDS)
                //设置写超时
                .writeTimeout(300, TimeUnit.SECONDS)
                //是否自动重连
                .retryOnConnectionFailure(true)
                .dispatcher(dispatcher)
                /*
                 * maxIdleConnections:最多保存的空闲连接 默认值5个
                 * keepAliveDuration:连接的默认保活时间为 5分钟
                 * */
                .connectionPool(new ConnectionPool(10, 3, TimeUnit.MINUTES))
                //设置过滤器
                //.addInterceptor()
                .build();
    }

}
