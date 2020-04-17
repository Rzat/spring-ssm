package com.example.springssm.config;

import com.example.springssm.domain.PaymentEvent;
import com.example.springssm.domain.PaymentState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;

import java.util.UUID;

@SpringBootTest
class StateMachineConfigurationTest {

    @Autowired
    StateMachineFactory<PaymentState, PaymentEvent> factory;

    @Test
    void testNEwStateMachine() {
        StateMachine<PaymentState, PaymentEvent> stateMachine = factory.getStateMachine(UUID.randomUUID());

               stateMachine.start();
        System.out.println(stateMachine.getState().toString());

        stateMachine.sendEvent(PaymentEvent.PRE_AUTHORIZE);
        System.out.println(stateMachine.getState().toString());

        stateMachine.sendEvent(PaymentEvent.PRE_AUTH_APPROVED);
        System.out.println(stateMachine.getState().toString());

        stateMachine.sendEvent(PaymentEvent.PRE_AUTH_DECLINED);
        System.out.println(stateMachine.getState().toString());

    }
}