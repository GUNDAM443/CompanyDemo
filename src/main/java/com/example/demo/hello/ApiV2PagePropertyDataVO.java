package com.example.demo.hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description: 分页数据
 * @author: aeiSaf
 * @create: 2020-08-04 17:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiV2PagePropertyDataVO implements Serializable {

    /**
     * 数据
     */
    private Object content;

    /**
     * 总条数
     */
    private Long totalElements;

    /**
     * 总页数
     */
    private Integer totalPages;

    /**
     * 当前页
     */
    private Integer presentPage;


}
