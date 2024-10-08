package ru.innotech.education;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class ContextConfig {

    @Bean
    public Connection connectionPoll() throws SQLException {
        HikariConfig config = new HikariConfig();
        HikariDataSource ds;
        config.setJdbcUrl( "jdbc:postgresql://stusbp-01pgp001.innodev.local:5432/file_transfer" );
        config.setSchema("file_transfer");
        config.setUsername( "file_transfer_admin" );
        config.setPassword( "TUw.N2RxLAp3gHPg5UVJP3i2jOKqe1UW9Rv7xEuebIWxA2MDMRFc7ZzKXIxHsDPNGw.lyoIDQ9j7mHjz-ZtnyHp7iV25fdw3eg" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource(config);
        return ds.getConnection();
    }
}
