package com.cykeromens.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Created by omens on 8/1/15.
 */
public interface Service {
    public Page<?> findAll(Pageable pageable);
    public Optional<Object> findById(Long id);
    public Object save(Object object);
    public void delete(Object object);
    public Long countAll();
}
