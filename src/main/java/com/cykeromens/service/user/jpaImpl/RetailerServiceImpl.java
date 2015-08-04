/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.user.jpaImpl;



import com.cykeromens.model.user.retailer.Retailer;
import com.cykeromens.repository.user.UserRepository;
import com.cykeromens.service.user.RetailerService;
import com.cykeromens.web.form.user.RetailerEditForm;
import com.cykeromens.repository.user.retailer.RetailerRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cykeromens
 */
//@Service("retailerService")
//@Repository
//@Transactional
public class RetailerServiceImpl extends UserServiceImpl implements RetailerService {

    private static final int statusCode = 6;
    RetailerRepository retailerRepository;

    @Autowired
    public RetailerServiceImpl(UserRepository userRepository, RetailerRepository retailerRepository) {
        super(userRepository);
        this.retailerRepository = retailerRepository;
    }

    @Scheduled(cron="font_awesome font_awesome font_awesome * * *")
    //@Scheduled(fixedDelay = 30000)
    @Override
    public void scheduleStatusTask(){
        Collection<Retailer> retailers = retailerRepository.findInactiveRetailers(statusCode);
        LocalDateTime currentDate = LocalDateTime.now();
        for (Retailer retailer: retailers) {
            if(retailer.getCreatedDate() ==null){
                retailer.setCreatedDate(currentDate);
            }
            long lCreatedDate = retailer.getCreatedDate().getLong(ChronoField.DAY_OF_YEAR);
            long lCurrentDate = currentDate.getLong(ChronoField.DAY_OF_YEAR);
            long days = lCurrentDate - lCreatedDate;
            if(days<15 && !retailer.isApproved() && !retailer.isOnBoarding() && !retailer.isActive()){
                retailer.setStatus(1); //new
                retailer.setOnBoarding(false);
                retailer.setActive(false);
                retailer.setApproved(false);
            }else if(days>=15){
                retailer.setStatus(2);//dew for on board
            }
            else if(days>=30){
                retailer.setStatus(3);//over dew for on board
            }
            if(retailer.isOnBoarding()==true){
                retailer.setStatus(4); //on boarding
            }else if(retailer.isActive()==true){
                retailer.setStatus(5); // is active
            }else if(retailer.isApproved()==true && retailer.isActive()==true){
                retailer.setStatus(6); //approved and activated
            }
            retailerRepository.save(retailer);

        }
    }


    @Override
    public Optional<Retailer> findByBusinessName(String businessName) {
        return retailerRepository.findByBusinessName(businessName);
    };

    @Override
    public Page<Retailer> findAllByPage(Pageable pageable) {
        return retailerRepository.findAll(pageable);
    }

    @Override
    public Optional<Retailer> create(RetailerEditForm retailerEditForm) {
        return null;
    }

}