package com.ssg.homework.t2021hw.config;

import com.ssg.homework.t2021hw.payment.agent.PaymentAgent;
import com.ssg.homework.t2021hw.payment.server.PaymentServer;
import com.ssg.homework.t2021hw.payment.server.PaymentServerContainer;
import com.ssg.homework.t2021hw.payment.server.RoutingPaymentServerContainer;
import com.ssg.homework.t2021hw.payment.server.PaymentServerInitializer;
import com.ssg.homework.t2021hw.payment.agent.VirtualPaymentAgent;
import com.ssg.homework.t2021hw.repository.PaymentMasterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class ServerConfig {

    @Bean
    public PaymentServerInitializer paymentServerInitializer(PaymentMasterRepository repository) {
        PaymentServerInitializer initializer = new PaymentServerInitializer(repository);
        return initializer;
    }

    @Bean
    public PaymentServerContainer routingServerContainer(PaymentServerInitializer initializer) {
        List<PaymentServer> applyServers = initializer.getApplyServers();
        Map<String, List<PaymentServer>> applyTargetServers = applyServers.stream().collect(Collectors.groupingBy(PaymentServer::getPaymentCode));

        RoutingPaymentServerContainer container = new RoutingPaymentServerContainer();
        container.setActiveRandom(true);
        container.setApplyTargetServers(applyTargetServers);
        return container;
    }

    @Bean
    public PaymentAgent paymentAgent(PaymentServerContainer container) {
        return new VirtualPaymentAgent(container);
    }

}
