package com.infoaa.sharing.main;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.core.env.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class CostSharingApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CostSharingApiApplication.class);


    @Autowired
    private Environment env;

	public static void main(String[] args) {
		LOGGER.info("Launching App: Inside the CostSharingApiApplication:main()");
		SpringApplication.run(CostSharingApiApplication.class, args);
	}


	@Bean
	  public TomcatServletWebServerFactory tomcatFactory() {
	    return new TomcatServletWebServerFactory() {
	      @Override
	      protected void postProcessContext(Context context) {
	        ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
	      }
	    };
	  }

}
