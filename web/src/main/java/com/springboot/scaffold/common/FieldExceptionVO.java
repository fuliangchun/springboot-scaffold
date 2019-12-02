/**
 * Copyright (C) 2017-2018 pinganfang, Inc. All Rights Reserved.
 */
package com.springboot.scaffold.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * 字段异常VO
 *
 */
public class FieldExceptionVO {

    @ApiModelProperty("字段名")
    private String fieldName;

    @ApiModelProperty("该字段出错信息")
    private String msg;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public FieldExceptionVO(String fieldName, String msg) {
        this.fieldName = fieldName;
        this.msg = msg;
    }

    public FieldExceptionVO() {
    }
}