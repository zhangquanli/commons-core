package com.github.zhangquanli.commons.core.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.zhangquanli.commons.core.response.PageData;
import com.github.zhangquanli.commons.core.response.ResponseResult;
import com.github.zhangquanli.commons.core.response.ResponseResults;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

public abstract class BaseMapperController<E, ID extends Serializable, M extends Mapper<E>> {
    private M mapper;

    @Autowired
    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseResult<?> insert(E entity) {
        mapper.insert(entity);
        return ResponseResults.success();
    }

    @PutMapping
    @ApiOperation("根据主键修改")
    public ResponseResult<?> updateById(E entity) {
        mapper.updateByPrimaryKey(entity);
        return ResponseResults.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据主键删除")
    public ResponseResult<?> deleteById(@PathVariable ID id) {
        mapper.deleteByPrimaryKey(id);
        return ResponseResults.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据主键查询")
    public ResponseResult<E> findById(@PathVariable ID id) {
        E entity = mapper.selectByPrimaryKey(id);
        return ResponseResults.success(entity);
    }

    @GetMapping
    @ApiOperation("查询所有")
    public ResponseResult<List<E>> findAll() {
        List<E> entities = mapper.selectAll();
        return ResponseResults.success(entities);
    }

    @GetMapping("/{page}/{size}")
    @ApiOperation("分页查询所有")
    public ResponseResult<PageData<E>> pageFindAll(@PathVariable Integer page, @PathVariable Integer size) {
        PageHelper.startPage(page, size);
        Page<E> entityPage = (Page<E>) mapper.selectAll();
        return ResponseResults.success(entityPage);
    }
}
