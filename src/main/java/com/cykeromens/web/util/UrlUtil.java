/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cykeromens.web.util;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author comenuko
 */
public class UrlUtil {
    
    public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest){
        String encode = httpServletRequest.getCharacterEncoding();
        if(encode == null){
            encode = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try{
            pathSegment = UriUtils.encodePathSegment(pathSegment, encode);
        }catch(UnsupportedEncodingException uee){
            System.out.println(uee.getMessage());
        }
        return pathSegment;
    }
}
