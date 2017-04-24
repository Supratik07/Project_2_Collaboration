package com.niit.blogbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.blogbackend.dao.UserDAO;
import com.niit.blogbackend.dao.UserDAOImpl;
import com.niit.blogbackend.model.BaseDomain;
import com.niit.blogbackend.model.Blog;
import com.niit.blogbackend.model.Friend;
import com.niit.blogbackend.model.Job;
import com.niit.blogbackend.model.JobApplication;
import com.niit.blogbackend.model.User;

@Configuration
@ComponentScan("com.niit.blogbackend")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("COLB_DB");
		dataSource.setPassword("root");
		return dataSource;
	}

	public Properties getHibernateProperties() {
		Properties connectionProperties = new Properties();
		connectionProperties.put("hibernate.hbm2ddl.auto", "update");
		connectionProperties.put("hibernate.show_sql", "true");
		connectionProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		return connectionProperties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Job.class);
		sessionBuilder.addAnnotatedClass(Friend.class);
		sessionBuilder.addAnnotatedClass(JobApplication.class);
		sessionBuilder.addAnnotatedClass(BaseDomain.class);
		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}
	/*
	 * @Autowired
	 * 
	 * @Bean(name="userDAO") public UserDAO getUserDetailsDAO(SessionFactory
	 * sessionFactory) { return new UserDAOImpl(sessionFactory); }
	 */
}
