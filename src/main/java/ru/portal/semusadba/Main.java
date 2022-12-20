package ru.portal.semusadba;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
//@EnableJpaRepositories("ru.portal.semusadba.model")
//@EntityScan("ru.portal.semusadba.model.entity")
@SpringBootApplication
public class Main extends SpringBootServletInitializer {


	public static void main(String[] args) {
//		System.setProperty("spring.devtools.restart.enabled", "false"); // Disabling java reload (bug with enums etc)
		SpringApplication.run(Main.class, args);
//		try {
//			File pidfile = new File(System.getenv().getOrDefault(
//					"PIDFILE", getPidFileName()));
//			pidfile.deleteOnExit();
//			(new ApplicationPid()).write(pidfile);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

//	private static String getPidFileName() {
//		return System.getenv().getOrDefault(
//				"PIDFILE", (new Function() {
//					@Override
//					public String apply(Object o) {
//						return System.getProperty(
//								"scheduling.enabled", "false"
//						).equals("true") ?
//								"logs/portfolio-scheduler.pid" :
//								"logs/portfolio.pid";
//					}
//				}).apply(null));
//	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(Main.class);
//	}

}


