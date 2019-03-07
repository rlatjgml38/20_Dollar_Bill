package com.dollarbill.springboot.chapter02;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ImageController {
	private static final String API_BASE_PATH = "/api";
	private Logger log = LogManager.getLogger(ImageController.class);
	
	// HTTP GET
	@GetMapping(API_BASE_PATH + "/images")
	Flux<Image> images() {
		return Flux.just(
			new Image("1", "learning-spring-boot-cover.jpg"),
			new Image("2", "learning-spring-boot-2nd-edition-cover.jpg"),
			new Image("3", "bazinga.png")
		);
	}
	
	// HTTP POST
	@PostMapping(API_BASE_PATH + "/images")
	Mono<Void> create(@RequestBody Flux<Image> images) {
		return images.map(
				image -> {log.info("We will save " + image + " to a Reactive database soon!");
				return image;
		}).then();
	}
}

// http --json -v POST localhost:8080/api/images id=10 name=foo