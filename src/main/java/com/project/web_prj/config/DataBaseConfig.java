package com.project.web_prj.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

// DB 관련 설정 클래스
@Configuration
@ComponentScan(basePackages = "com.project.web_prj")
@PropertySource("classpath:db_info.properties")
@Getter
public class DataBaseConfig {

    @Value("${aws.rds_user_name}")
    private String userName;

    @Value("${aws.rds_password}")
    private String password;

    @Value("${aws.rds_url}")
    private String url;

    public DataBaseConfig() {
    }

    // DB접속 정보 설정 (DataSource설정)
    @Bean
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();
//        config.setUsername("spring4");
//        config.setPassword("1234");
//        config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
//        config.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        config.setUsername(userName);
        config.setPassword(password);
        config.setJdbcUrl(url);
        config.setDriverClassName("org.mariadb.jdbc.Driver");

        return new HikariDataSource(config);
    }
}
