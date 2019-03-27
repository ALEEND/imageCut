package com.mko.test.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
/**
 * @program: testdemo
 * @description:
 * @author: yuxz
 * @create: 2019-03-25 15:08
 **/
public class ResponseResult {
    private String code;
    private String message;
    private Map<String,Object> data1;

}
