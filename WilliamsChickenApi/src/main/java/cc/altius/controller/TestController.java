/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author altius
 */
public class TestController {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("..."+encoder.encode("pass"));

    }
}
