/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.location.jpaImpl;

import com.cykeromens.model.location.Area;
import com.cykeromens.repository.location.AreaRepository;
import com.cykeromens.service.location.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author cykeromens
 */
@Service("areaService")
@Repository
@Transactional
public class AreaServiceImpl implements AreaService{
    
    AreaRepository areaRepository;

    public AreaServiceImpl(){}
    @Autowired
    public AreaServiceImpl(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }

    @Override @Transactional(readOnly = true)
    public Page<Area> findAll(Pageable pageable) {
        return (Page<Area>)areaRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(areaRepository.findOne(id));
    }

    @Override
    public Object save(Object object) {
        Area area = (Area) object;
        return areaRepository.saveAndFlush(area);
    }

    @Override
    public void delete(Object object) {
        Area area = (Area) object;
        areaRepository.delete(area);
    }

    @Override
    public Long countAll() {
        return areaRepository.count();
    }
}
