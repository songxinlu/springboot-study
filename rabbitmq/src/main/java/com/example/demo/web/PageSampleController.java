package com.example.demo.web;

import com.example.demo.config.MqttConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Description:发布/订阅通讯
 * @author: songxl
 * @time: 2022/6/1 11:45
 */
@ApiIgnore
@Controller
@RequestMapping("/pageSample")
public class PageSampleController {

    @Autowired
    private MqttConfig mqttConfig;

    @GetMapping("/sample")
    public String index(ModelMap model) {
        model.addAttribute("mqtt_url", mqttConfig.getFrontUrl());
        return "sample";
    }

}
