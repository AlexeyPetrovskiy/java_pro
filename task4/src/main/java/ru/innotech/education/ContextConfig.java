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
        config.setJdbcUrl( "jdbc:postgresql://host:5432/test" );
        config.setSchema("test");
        config.setUsername( "test_admin" );
        config.setPassword( "password" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource(config);
        return ds.getConnection();
    }
}
