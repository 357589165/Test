package com.example.demo.web;

import com.example.demo.utils.LongPollingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author 王超 by 2019-03-07
 */
@Controller
public class LongPollingController {

    @Autowired
    LongPollingUtils longPollingUtils;

    @GetMapping("/longPolling")
    @ResponseBody
    public DeferredResult<String> longPolling() {
        DeferredResult<String> result = new DeferredResult<>(3000L );
        result.onTimeout(() -> result.setResult( "请求过期。"));
        longPollingUtils.addDeferredResult(result);
        return result;
    }

}
