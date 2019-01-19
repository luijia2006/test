/*
package com.we.controller;

import com.we.sso.client.annotation.NotSSo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/test")
@Slf4j
@NotSSo
public class TestController {

    private DeferredResult<String> deferredResult = new DeferredResult<>();

    */
/**
     * 返回DeferredResult对象
     *
     * @return
     *//*

    @RequestMapping("/testDeferredResult")
    public DeferredResult<String> testDeferredResult() {
        return deferredResult;
    }

    */
/**
     * 对DeferredResult的结果进行设置
     *
     * @return
     *//*

    @ResponseBody
    @RequestMapping("/setDeferredResult")
    public Object setDeferredResult() {
        deferredResult.setResult("Test result!");
        return "succeed";
    }
}*/
