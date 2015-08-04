/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cykeromens.service.location.jpaImpl;

import com.cykeromens.model.location.Country;
import com.cykeromens.service.location.CountryService;
import com.cykeromens.repository.location.CountryRepository;

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
@Service("countryService")
@Repository
@Transactional
public class CountryServiceImpl implements CountryService {

    CountryRepository countryRepository;

    public CountryServiceImpl(){}

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override @Transactional(readOnly = true)
    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }

    @Override @Transactional(readOnly = true)
    public Optional<Object> findById(Long id) {
        return Optional.ofNullable(countryRepository.findOne(id));
    }

    @Override
    public Object save(Object object) {
        if(object instanceof Country){
            return countryRepository.save((Country)object);
        }
        else{
            return null;
        }
    }

    @Override
    public void delete(Object object) {
        if(object instanceof Country){
            countryRepository.delete((Country)object);
        }
    }

    @Override @Transactional(readOnly = true)
    public Long countAll() {
        return countryRepository.count();
    }
    
}
