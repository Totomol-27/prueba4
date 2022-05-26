package com.isita.ccapper;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.isita.ccapper.app.service.ArchivoService;

@SpringBootApplication
//@EnableScheduling
public class CcapperIsitaApplication  implements CommandLineRunner  {
	@Autowired
	private BCryptPasswordEncoder  encrypPassword;
	@Resource
	private ArchivoService ArchivoService;
	
	public static void main(String[] args) {
		SpringApplication.run(CcapperIsitaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String v = "12345";
		String xx = encrypPassword.encode(v);
		System.out.println("------------------password "+xx);
	}
	
}
