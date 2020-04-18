package com.example.springssm.services;

import com.example.springssm.domain.Payment;
import com.example.springssm.domain.PaymentEvent;
import com.example.springssm.domain.PaymentState;
import com.example.springssm.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder().amount(new BigDecimal("12.99")).build();
    }

    @Transactional
    @Test
    void preAuth() {
        Payment savedPaymet = paymentService.newPayment(payment);

        System.out.println("State should be NEW");

        System.out.println(savedPaymet.getState());

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPaymet.getId());

        Payment preAuthPayment = paymentRepository.getOne(savedPaymet.getId());

        System.out.println("State should be PRE_AUTH");
        System.out.println(sm.getState().getId() );
        System.out.println(preAuthPayment);

    }
}
