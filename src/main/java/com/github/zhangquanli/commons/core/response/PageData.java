package com.github.zhangquanli.commons.core.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 分页数据
 *
 * @author zhangquanli
 */
@ApiModel("分页数据")
public class PageData<E> {
    @ApiModelProperty(value = "总计条数", position = 1, required = true, example = "1")
    private long total;
    @ApiModelProperty(value = "元素列表", position = 2, required = true)
    private List<E> rows;

    public PageData() {
    }

    public PageData(long total, List<E> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
