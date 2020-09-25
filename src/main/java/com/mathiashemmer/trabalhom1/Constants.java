/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mathiashemmer.trabalhom1;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mathias
 */
public class Constants {
    public static Map<String, String> usuarios (){
        Map<String, String> us = new HashMap<String,String>();
        
        us.put("Mathias", "Hemmer");
        us.put("Admin", "Admin");
        us.put("Professor", "Xavier");
        
        return us;
    }
}
