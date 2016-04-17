package de.jehn.sarah.configuration;


import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sarahjehn on 28.02.16.
 */

@Configuration
public class PostgreConfiguration {


        @Bean
        public BasicDataSource dataSource() {

            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            String username = System.getenv("JDBC_DATABASE_USERNAME");
            String password = System.getenv("JDBC_DATABASE_PASSWORD");

            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl(dbUrl);
            basicDataSource.setUsername(username);
            basicDataSource.setPassword(password);

            return basicDataSource;
        }
    }