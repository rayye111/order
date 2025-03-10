package com.qd.entity;

import lombok.Data;

@Data
public class OrdersDto extends Orders {
    private String timeMin;
    private String timeMax;
    private Integer page;  //第几页
    private Integer limit; //每页几条
}