package com.example.demo.web;

import cn.hutool.core.collection.ListUtil;
import com.example.demo.api.CommonResult;
import com.example.demo.dto.SendToTopicDTO;
import com.example.demo.gateway.MqttGateway;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author: songxl
 * @time: 2022/6/1 11:48
 */
@RestController
@Api(value = "即时通讯后端示例", tags = { "即时通讯后端示例" })
@RequestMapping("/backSample")
public class BackSampleController {

    @Autowired
    private MqttGateway mqttGateway;

    @PostMapping("/sendToDefaultTopic")
    @ApiOperation("向默认主题发送消息")
    public CommonResult sendToDefaultTopic(String payload) {
        mqttGateway.sendToMqtt(payload);
        return CommonResult.success(null);
    }

    @PostMapping("/sendToTopic")
    @ApiOperation("向指定主题发送消息")
    public CommonResult sendToTopic(@RequestBody List<SendToTopicDTO> sendToTopics) {
        if (sendToTopics != null && sendToTopics.size() > 0) {
            sendToTopics.stream().forEach(sendToTopicDTO -> {
                mqttGateway.sendToMqtt(sendToTopicDTO.getMessage(), sendToTopicDTO.getTopic());
            });
        }
        return CommonResult.success(null);
    }

}
