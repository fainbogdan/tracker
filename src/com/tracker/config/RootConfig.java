package com.tracker.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages={"com.tracker"},excludeFilters={@Filter(type=FilterType.ANNOTATION,value=EnableWebMvc.class)})
public class RootConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(DataSource dataSource,JpaVendorAdapter adapter)
	{
		LocalContainerEntityManagerFactoryBean bean=new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource);
		bean.setJpaVendorAdapter(adapter);
		bean.setPersistenceUnitName("tracker");
		bean.setPackagesToScan("com.tracker");
		return bean;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter()
	{
		HibernateJpaVendorAdapter adapter=new HibernateJpaVendorAdapter();
		return adapter;
	}
	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/tracker");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
}
