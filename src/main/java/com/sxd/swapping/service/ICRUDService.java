package com.sxd.swapping.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface ICRUDService<T> {

    public T insert(T entity) throws Exception;

    public T update(T entity) throws Exception;

    public boolean delete(T entity) throws Exception;

    public Page<T> pageFind(T entity) throws Exception;

    public Page<T> pageSortFind(T entity, Map<String,Sort.Direction> map) throws Exception;

    public List<T> find(T entity) throws Exception;

    public List<T> findAll() throws  Exception;
}

