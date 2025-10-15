package com.example.batch.job;

import com.example.batch.model.source.SourcePayment;
import com.example.batch.model.target.TargetPayment;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor implements ItemProcessor<SourcePayment, TargetPayment> {
    @Override
    public TargetPayment process(SourcePayment source) {
        TargetPayment target = new TargetPayment();
        target.setPayDate(source.getPayDate());
        target.setPayType(source.getPayType());
        // Transform: Round amount to nearest 100
        target.setAmount(Math.round(source.getAmount() / 100.0) * 100.0);
        return target;
    }
}
