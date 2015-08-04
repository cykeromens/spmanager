/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.service.location.jpaImpl;

import com.cykeromens.model.location.Zone;
import com.cykeromens.service.location.ZoneService;
import com.cykeromens.repository.location.ZoneRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cykeromens
 */
@Service
@Repository("zoneService")
@Transactional
public class ZoneServiceImpl implements ZoneService {

    ZoneRepository zoneRepository;

    public ZoneServiceImpl(){}

    @Autowired
    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override @Transactional(readOnly = true)
    public Page<Zone> findAll(Pageable pageable) {
        return zoneRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(zoneRepository.findOne(id));
    }

    @Override
    public Object save(Object object) {
        if(object instanceof Zone){
            return zoneRepository.save((Zone)object);
        }else{
            return null;
        }
    }

    @Override
    public void delete(Object object) {
        zoneRepository.delete((Zone)object);
    }

    @Override
    public Long countAll() {
        return zoneRepository.count();
    }
    
}
