package com.gpch.login.configuration;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

    /*@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public PasswordEncoder passwordMD5Encoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return getMd5(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return getMd5(charSequence.toString()).equals(s);
            }
        };
    }

    public static String getMd5(String input) {
        try {
            // Static getInstance method is called with hashing SHA
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method called
            // to calculate message digest of an input
            // and return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown"
                    + " for incorrect algorithm: " + e);
            return null;
        }
    }

    /*@Bean
    public PasswordEncoder passwordMD5Encoder()
    {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        passwordEncoder.setDefaultPasswordEncoderForMatches(new MessageDigestPasswordEncoder("MD5"));
        passwordEncoder.
        return new MD5Encoder();
    }*/

}