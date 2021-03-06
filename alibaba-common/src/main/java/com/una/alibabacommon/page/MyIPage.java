package com.una.alibabacommon.page;

import com.google.common.collect.Lists;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author maxiucheng
 * @className MyIPage
 * @description 分页接口显示yapi
 * @date 2019/11/28 5:44 下午
 **/
@Data
public class MyIPage<T> implements Serializable {

    /**
     * 总记录数
     */
    private long total;

    /**
     * 每页条数
     */
    private long size;

    /**
     * 当前页数
     */
    private long current;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 查询数据列表
     */
    private List<T> records = Lists.newArrayList();

}
