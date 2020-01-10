package com.github.zhangquanli.commons.core.response;

import org.springframework.util.Assert;

import java.util.List;

import static com.github.zhangquanli.commons.core.response.ResponseResult.*;

/**
 * 响应结果工具类
 *
 * @author zhangquanli
 */
public final class ResponseResults {
    private ResponseResults() {
    }

    /**
     * 构建成功的响应结果，使用【默认】消息，使用【默认】数据
     *
     * @return 响应结果
     */
    public static <T> ResponseResult<T> success() {
        return buildResponseResult(SUCCESS_CODE, SUCCESS_MSG, null);
    }

    /**
     * 构建成功的响应结果，使用【用户】消息，使用【默认】数据
     *
     * @param msg 消息
     * @return 响应结果
     */
    public static <T> ResponseResult<T> success(String msg) {
        return buildResponseResult(SUCCESS_CODE, msg, null);
    }

    /**
     * 构建成功的响应结果，使用【默认】消息，使用【用户】数据
     *
     * @param data 数据
     * @param <T>  数据的类型
     * @return 响应结果
     */
    public static <T> ResponseResult<T> success(T data) {
        Assert.notNull(data, "data can not be null");
        return buildResponseResult(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    /**
     * 构建成功的响应结果，使用【默认】消息，使用【分页】数据
     *
     * @param page 分页数据
     * @param <E>  数据的类型
     * @return 响应结果
     * @see org.springframework.data.domain.Page
     */
    public static <E> ResponseResult<PageData<E>> success(org.springframework.data.domain.Page<E> page) {
        PageData<E> pageData = buildPageData(page.getTotalElements(), page.getContent());
        return buildResponseResult(SUCCESS_CODE, SUCCESS_MSG, pageData);
    }

    /**
     * 构建成功的响应结果，使用【默认】消息，使用【分页】数据
     *
     * @param page 分页数据
     * @param <E>  数据的类型
     * @return 响应结果
     * @see com.github.pagehelper.Page
     */
    public static <E> ResponseResult<PageData<E>> success(com.github.pagehelper.Page<E> page) {
        PageData<E> pageData = buildPageData(page.getTotal(), page.getResult());
        return buildResponseResult(SUCCESS_CODE, SUCCESS_MSG, pageData);
    }

    /**
     * 构建成功的响应结果，使用【用户】消息，使用【用户】数据
     *
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据的类型
     * @return 响应结果
     */
    public static <T> ResponseResult<T> success(String msg, T data) {
        Assert.notNull(msg, "msg can not be null");
        Assert.notNull(data, "data can not be null");
        return buildResponseResult(SUCCESS_CODE, msg, data);
    }

    /**
     * 构建成功的响应结果，使用【用户】消息，使用【分页】数据
     *
     * @param msg  消息
     * @param page 分页数据
     * @param <E>  数据的类型
     * @return 响应结果
     */
    public static <E> ResponseResult<PageData<E>> success(String msg, org.springframework.data.domain.Page<E> page) {
        Assert.notNull(msg, "msg can not be null");
        Assert.notNull(page, "page can not be null");
        PageData<E> pageData = buildPageData(page.getTotalElements(), page.getContent());
        return buildResponseResult(SUCCESS_CODE, msg, pageData);
    }

    /**
     * 构建成功的响应结果，使用【用户】消息，使用【分页】数据
     *
     * @param msg  消息
     * @param page 分页数据
     * @param <E>  数据的类型
     * @return 响应结果
     */
    public static <E> ResponseResult<PageData<E>> success(String msg, com.github.pagehelper.Page<E> page) {
        Assert.notNull(msg, "msg can not be null");
        Assert.notNull(page, "page can not be null");
        PageData<E> pageData = buildPageData(page.getTotal(), page.getResult());
        return buildResponseResult(SUCCESS_CODE, msg, pageData);
    }

    public static <T> ResponseResult<T> failure() {
        return buildResponseResult(FAILURE_CODE, FAILURE_MSG, null);
    }

    public static <T> ResponseResult<T> failure(String msg) {
        Assert.notNull(msg, "msg can not be null");
        return buildResponseResult(FAILURE_CODE, msg, null);
    }

    public static <T> ResponseResult<T> failure(T data) {
        Assert.notNull(data, "data can not be null");
        return buildResponseResult(FAILURE_CODE, FAILURE_MSG, data);
    }

    public static <T> ResponseResult<T> failure(String msg, T data) {
        Assert.notNull(msg, "msg can not be null");
        Assert.notNull(data, "data can not be null");
        return buildResponseResult(FAILURE_CODE, msg, data);
    }

    /**
     * 构建响应结果
     *
     * @param code 代码
     * @param msg  消息
     * @param data 数据
     * @param <T>  数据的类型
     * @return 响应结果
     */
    public static <T> ResponseResult<T> buildResponseResult(int code, String msg, T data) {
        return new ResponseResult<>(code, msg, data);
    }

    /**
     * 构建分页数据
     *
     * @param total 总计条数
     * @param rows  元素列表
     * @param <E>   元素的类型
     * @return 分页数据
     */
    public static <E> PageData<E> buildPageData(long total, List<E> rows) {
        return new PageData<>(total, rows);
    }
}
