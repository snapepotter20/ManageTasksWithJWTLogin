package com.jwtlogin;

import org.springframework.boot.SpringApplication;
import java.security.Key;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@SpringBootApplication
public class JwtLoginAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtLoginAppApplication.class, args);
		  System.out.println(new BCryptPasswordEncoder().encode("1234"));
		   Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	        String base64Key = Encoders.BASE64.encode(key.getEncoded());
	        System.out.println("Base64 Secret Key: " + base64Key);
	}

}
