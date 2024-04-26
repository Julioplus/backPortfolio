package com.jcm.portfolio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jcm.portfolio.configuration.MongoDBDummy;
import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PortfolioApplication {


  private static final MongoDBDummy mongoDB = new MongoDBDummy();


  public static void main(String[] args) throws JsonProcessingException {
    Locale.setDefault(new Locale("es", "ES"));
    SpringApplication.run(PortfolioApplication.class, args);
  }

}
