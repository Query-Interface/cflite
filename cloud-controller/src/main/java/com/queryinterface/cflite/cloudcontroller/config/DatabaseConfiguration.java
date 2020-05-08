package com.queryinterface.cflite.cloudcontroller.config;

import com.queryinterface.cflite.cloudcontroller.common.EnvironmentHelper;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class DatabaseConfiguration {
    @Bean
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url(EnvironmentHelper.getSystemProperty("DB_URI", "jdbc:postgresql://cflite:5432/cflitedb"));
        dataSourceBuilder.username(EnvironmentHelper.getSystemProperty("DB_USER", "query-interface"));
        dataSourceBuilder.password(EnvironmentHelper.getSystemProperty("DB_PASSWORD","Password1"));
        DataSource dataSource = dataSourceBuilder.build();
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true);
        adapter.setDatabase(Database.POSTGRESQL);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(adapter);
        factory.setPackagesToScan("com.queryinterface.cflite.cloudcontroller");
        factory.setDataSource(getDataSource());
        return factory;
    }
}
