/**
 * Copyright (C) 2017-2018 pinganfang, Inc. All Rights Reserved.
 */
package com.springboot.scaffold.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 异常VO
 *
 */
@ApiModel(description = "异常数据")
public class ExceptionVO {

    @ApiModelProperty(value = "异常Code,可选", example = "null")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String errorCode;

    @ApiModelProperty(value = "异常消息,可选", example = "参数不符合要求")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}

