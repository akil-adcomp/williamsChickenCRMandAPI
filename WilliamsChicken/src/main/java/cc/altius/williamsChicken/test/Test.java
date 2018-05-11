/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cc.altius.williamsChicken.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;

/**
 *
 * @author mahesh
 */
public class Test {

    public static void main(String[] args) {

        org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig bEConfig = new EnvironmentStringPBEConfig();
        bEConfig.setAlgorithm("PBEWithMD5AndDES");
        bEConfig.setPasswordEnvName("PASSWORD_ENV_VARIABLE");

        org.jasypt.encryption.pbe.StandardPBEStringEncryptor bEStringEncryptor = new StandardPBEStringEncryptor();
        bEStringEncryptor.setConfig(bEConfig);
        bEStringEncryptor.setPassword("ALTIUS");

        String pass = bEStringEncryptor.encrypt("joliBaseAppPass");
        System.out.println("pass - " + pass);
        System.out.println("Decrypt - "+bEStringEncryptor.decrypt(pass));
        
    }
}
