/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.config;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author denisse_mejia
 */
public class Main {
    
    
    public static void main(String[] args) {
        BCryptPasswordEncoder vpass = new BCryptPasswordEncoder(12);
        vpass.encode("123");
        System.out.println(vpass.encode("123"));
    }
    
    
}
