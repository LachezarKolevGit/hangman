package bg.proxiad.demo.hangman.utils;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class JpaConfig {

  @Autowired private Environment environment;

  @Bean
  DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    System.out.println(
        "Driver is " + environment.getProperty("spring.datasource.driver-class-name"));
    dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(environment.getProperty("spring.datasource.url"));
    System.out.println("Url " + dataSource.getUrl());

    dataSource.setUsername(environment.getProperty("spring.datasource.username"));
    System.out.println("Url " + dataSource.getUsername());
    dataSource.setPassword(environment.getProperty("spring.datasource.password"));
    System.out.println("Passoword " + dataSource.getPassword());
    return dataSource;
  }

  @Bean
  LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan("bg.proxiad.demo.hangman.model");
    em.setPersistenceUnitName("name");
    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(this.additionalProperties());
    em.getEntityManagerInterface();
    return em;
  }

  @Bean
  PlatformTransactionManager transactionManager() {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

  @Bean
  PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  @Bean
  PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
    return new PersistenceAnnotationBeanPostProcessor();
  }

  final Properties additionalProperties() {
    final Properties hibernateProperties = new Properties();
    hibernateProperties.setProperty(
        "hibernate.ddl-auto", environment.getProperty("spring.jpa.hibernate.ddl-auto"));
    hibernateProperties.setProperty(
        "hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
    hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "false"); // error

    return hibernateProperties;
  }
}*/
