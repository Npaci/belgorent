package com.pngabo.belgorent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ContextIdApplicationContextInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BelgorentApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BelgorentApplication.class, args);
		ctx.getBean(Menu.class).afficherVoitures();
	}

}
