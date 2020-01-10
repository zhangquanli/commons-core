package com.github.zhangquanli.commons.core.controller;

import com.github.zhangquanli.commons.core.response.PageData;
import com.github.zhangquanli.commons.core.response.ResponseResult;
import com.github.zhangquanli.commons.core.response.ResponseResults;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseJpaRepositoryController<T, ID extends Serializable, R extends JpaRepository<T, ID>> {
    private R jpaRepository;

    @Autowired
    public void setJpaRepository(R jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @PostMapping
    @ApiOperation("新增")
    public ResponseResult<?> insert(T entity) {
        jpaRepository.save(entity);
        return ResponseResults.success();
    }

    @PutMapping
    @ApiOperation("根据主键修改")
    public ResponseResult<?> updateById(T entity) {
        jpaRepository.save(entity);
        return ResponseResults.success();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("根据主键删除")
    public ResponseResult<?> deleteById(@PathVariable ID id) {
        jpaRepository.deleteById(id);
        return ResponseResults.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据主键查询")
    public ResponseResult<T> findById(@PathVariable ID id) {
        Optional<T> entityOptional = jpaRepository.findById(id);
        return entityOptional.map(ResponseResults::success)
                .orElseGet(ResponseResults::success);
    }

    @GetMapping
    @ApiOperation("查询所有")
    public ResponseResult<List<T>> findAll() {
        List<T> entities = jpaRepository.findAll();
        return ResponseResults.success(entities);
    }

    @GetMapping("/{page}/{size}")
    @ApiOperation("分页查询所有")
    public ResponseResult<PageData<T>> pageFindAll(@PathVariable Integer page, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<T> entityPage = jpaRepository.findAll(pageable);
        return ResponseResults.success(entityPage);
    }
}
