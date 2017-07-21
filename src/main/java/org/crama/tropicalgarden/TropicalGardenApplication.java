package org.crama.tropicalgarden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(
	basePackageClasses = { TropicalGardenApplication.class, Jsr310JpaConverters.class }
)
@SpringBootApplication
@ComponentScan("org.crama.tropicalgarden")
//@EntityScan("org.crama.tropicalgarden")
@EnableJpaRepositories("org.crama.tropicalgarden")
@EnableAsync
@EnableScheduling
public class TropicalGardenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TropicalGardenApplication.class, args);
	}
	
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
	  ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
	  messageBundle.setBasename("classpath:messages/messages");
	  messageBundle.setDefaultEncoding("UTF-8");
	  return messageBundle;
	}
	
	
}
