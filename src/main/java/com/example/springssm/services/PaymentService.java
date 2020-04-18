package com.example.springssm.services;

import com.example.springssm.domain.Payment;
import com.example.springssm.domain.PaymentEvent;
import com.example.springssm.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

public interface PaymentService {
    Payment newPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);
}
