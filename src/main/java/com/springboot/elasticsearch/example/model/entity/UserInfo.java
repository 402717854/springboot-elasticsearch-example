package com.springboot.elasticsearch.example.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @ClassName UserInfo
 * @Description: TODO
 * @Author wys
 * @Date 2020/8/10-21:07
 * @Version V1.0
 **/
@Setter
@Getter
public class UserInfo {
    private String name;
    private Integer age;
    private Float salary;
    private String address;
    private String remark;
    private Date createTime;
    private String birthDate;
}
