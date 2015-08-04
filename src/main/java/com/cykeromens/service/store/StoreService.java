/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.service.store;

import com.cykeromens.model.store.Store;
import com.cykeromens.service.Service;

/**
 *
 * @author cykeromens
 */
public interface StoreService extends Service {
    
    public Store findByStoreName(String storeName);
}
