package com.example.demo;

import lombok.Data;

/**
 * @author : pp
 * @date : Created in 2020/6/30 18:02
 */
@Data
public class Person {

    private Long id;
    private String name;
    private String sn;
    private String departmentName;
    private Long departmentId;
    private String jobName;
    private Long jobId;
    private int status;
}

