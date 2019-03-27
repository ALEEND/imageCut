package com.mko.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: testdemo
 * @description:
 * @author: yuxz
 * @create: 2019-03-25 14:44
 **/
    @Getter
    @Setter
    @Component
    @ConfigurationProperties(prefix="message")
    @PropertySource("classpath:file-message.properties")
    public class MessageProperties {

        private long fileSize;  //压缩大小

        private double scaleRatio; //压缩比例

        private String upPath; //保存路径

        private String imageType; //图片类型


    }
