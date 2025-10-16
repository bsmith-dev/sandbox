package com.example.batch.config;

import com.example.batch.job.PaymentProcessor;
import com.example.batch.model.source.SourcePayment;
import com.example.batch.model.target.TargetPayment;
import com.example.batch.repository.source.SourcePaymentRepository;
import com.example.batch.repository.target.TargetPaymentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Collections;

@Configuration
public class BatchConfig {

    @Autowired
    private SourcePaymentRepository sourcePaymentRepo;

    @Autowired
    private TargetPaymentRepository targetPaymentRepo;

    @Autowired
    private PaymentProcessor paymentProcessor;

    @Bean
    public RepositoryItemReader<SourcePayment> reader() {
        RepositoryItemReader<SourcePayment> reader = new RepositoryItemReader<>();
        reader.setRepository(sourcePaymentRepo);
        reader.setMethodName("findAll");
        reader.setArguments(Collections.emptyList());
        reader.setSort(Collections.singletonMap("id", Sort.Direction.ASC));
        return reader;
    }


    @Bean
    public ItemWriter<TargetPayment> writer() {
        return items -> targetPaymentRepo.saveAll(items);
    }

    @Bean
    public Step etlStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("ETL-Payment-Step", jobRepository)
                .<SourcePayment, TargetPayment>chunk(100, transactionManager)
                .reader(reader())
                .processor(paymentProcessor)
                .writer(writer())
                .build();
    }

    @Bean
    public Job etlPaymentJob(JobRepository jobRepository, Step etlStep) {
        return new JobBuilder("ETL-Payment-Job", jobRepository)
                .start(etlStep)
                .build();
    }
}

