package com.smag.chatmessage;

import com.smag.chatmessage.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/*
 *  Classe ChatmessageApplication
 * */
@SpringBootApplication
public class ChatmessageApplication implements CommandLineRunner {

	@Resource
	private StorageService storageService;

	/*
	 * Boostrapp spring application
	 * */
	public static void main(String[] args) {
		SpringApplication.run(ChatmessageApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		storageService.init();
	}
}
