package com.example.demo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回topic信息
 */
@Data
public class SendToTopicDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "信息")
    private String message;
    @ApiModelProperty(value = "类型")
    private String type;
    @ApiModelProperty(value = "topic")
    private String topic;
}
