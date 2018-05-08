package se.steam.trellov2.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import se.steam.trellov2.service.exceptions.mappers.DataNotFoundMapper;
import se.steam.trellov2.service.exceptions.mappers.WrongInputExceptionMapper;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.springframework.orm.jpa.vendor.Database.MYSQL;

@Configuration
@EnableTransactionManagement
public class AppConfig extends ResourceConfig {

    private final Environment environment;

    public AppConfig(Environment environment) {
        this.environment = environment;
        this.packages("se.steam.trellov2.resource");
        register(DataNotFoundMapper.class);
        register(WrongInputExceptionMapper.class);
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl(environment.getProperty("trellov2.jdbc-url"));
        config.setUsername(environment.getProperty("trellov2.username"));
        config.setPassword(environment.getProperty("trellov2.password"));
        config.addDataSourceProperty("useSSL", false);
        return new HikariDataSource(config);
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(MYSQL);
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("se.steam.trellov2.repository.model");
        factory.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        return factory;
    }

}