package com.payment.admin.config;

import com.payment.admin.repository.base.BaseRepositoryFactoryBean;
import com.payment.admin.repository.base.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
        basePackages = { "com.payment.admin.repository" },
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class,
        repositoryBaseClass = BaseRepositoryImpl.class
)
@EnableTransactionManagement
public class DatabaseConfig {
}
