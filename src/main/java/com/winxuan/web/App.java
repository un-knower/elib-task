package com.winxuan.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangmingsen on 2016/7/5.
 */
@RestController
public class App {
    @RequestMapping("/")
    String home() {
        return "hello world";
    }

}
