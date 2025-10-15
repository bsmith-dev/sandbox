package com.example.batch.repository.source;


import com.example.batch.model.source.SourcePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourcePaymentRepository extends JpaRepository<SourcePayment, Long> {
}
