package com.personal.app;


import java.util.Properties;

import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
  
@Configuration()
@ComponentScan("com.personal.app")
public class AppConfiguracion {

	@Bean(name="flyway")
	public Flyway flayway(){

		Flyway flayway = new Flyway();
		flayway.setBaselineOnMigrate(false);
		flayway.setLocations("classpath:db/migration/estructura");
		flayway.setDataSource(dataSource());
		flayway.migrate();
		
		return flayway;
	}

	@Bean(name="dataSource")
	public SingleConnectionDataSource dataSource()
	{
		SingleConnectionDataSource  datasource = new SingleConnectionDataSource();
		datasource.setDriverClassName("org.sqlite.JDBC");
		datasource.setUrl("jdbc:sqlite:aplicacion.db");
		datasource.setSuppressClose(true);
		return datasource;
	}
	
	
	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect","com.personal.app.SQLiteDialect");
		properties.setProperty("hibernate.show_sql","true");
		properties.setProperty("hibernate.hbm2ddl.auto","none");
		properties.setProperty("hibernate.current_session_context_class","thread");
		sessionFactory.setPackagesToScan("com.personal.app.modelo");
	    sessionFactory.setHibernateProperties(properties);
		return sessionFactory;
		
	}
	/*
    <property name="show_sql">true</property>
    <property name="format_sql">true</property>
    <property name="dialect"></property>
    <property name="connection.driver_class">org.sqlite.JDBC</property>
    <property name="connection.url">jdbc:sqlite:mydb.db</property>
    <property name="connection.username"></property>
    <property name="connection.password"></property>
     
    <property name="hibernate.hbm2ddl.auto">update</property>
     
    <mapping class="com.srccodes.example.hibernate.Contact"/>
    
	  */
	@Bean(name="transactionManager")
	public HibernateTransactionManager transactionManager(){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		
		transactionManager.setSessionFactory((SessionFactory) sessionFactory().getObject());
		return transactionManager;
	}
 


	
	
}
