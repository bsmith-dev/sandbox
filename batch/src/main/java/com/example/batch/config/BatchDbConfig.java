package com.example.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchDbConfig {

    @Bean(name = "batchProperties")
    @ConfigurationProperties(prefix = "batch.datasource")
    @Primary
    public DataSourceProperties batchDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "dataSource")
    @Primary
    public DataSource batchDatasource(@Qualifier("batchProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager batchTransactionManager(@Qualifier("dataSource") DataSource batchDatasource) {
        return new DataSourceTransactionManager(batchDatasource);
    }

    @Bean(name = "batchJobRepository")
    @Primary
    public JobRepository jobRepository(
            @Qualifier("dataSource") DataSource batchDatasource,
            @Qualifier("transactionManager") PlatformTransactionManager batchTransactionManager) throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(batchDatasource);
        factory.setTransactionManager(batchTransactionManager);
        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
