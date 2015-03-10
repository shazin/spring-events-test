package springeventstest.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages={"springeventstest.repo"})
@EnableTransactionManagement
public class RepoConfig {
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource());
		localContainerEntityManagerFactoryBean.setPackagesToScan("springeventstest.domain");
		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties());
		localContainerEntityManagerFactoryBean.setPersistenceUnitName("springevents-PU");
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		localContainerEntityManagerFactoryBean.afterPropertiesSet();
		
		return localContainerEntityManagerFactoryBean.getObject();
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory());
	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	}
	
	@Bean
	public Properties jpaProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		prop.setProperty("hibernate.hbm2ddl.auto", "create");
		
		return prop;
	}
}
