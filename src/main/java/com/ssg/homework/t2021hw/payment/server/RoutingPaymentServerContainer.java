package com.ssg.homework.t2021hw.payment.server;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class RoutingPaymentServerContainer extends AbstractPayementServerContainer {

    private boolean activeRandom;

    public RoutingPaymentServerContainer() {
        activeRandom = false;
    }

    public void setActiveRandom(boolean activeRandom) {
        this.activeRandom = activeRandom;
    }

    @Override
    public void setApplyTargetServers(Map<String, List<PaymentServer>> applyTargetServers) {
        super.setApplyTargetServers(applyTargetServers);
    }

    @Override
    protected PaymentServer determineTargetServer(String paymentCode, String paymentType) {
        List<PaymentServer> resolvedServers = getResolvedServers(paymentCode);
        if(resolvedServers.isEmpty()) {
            throw new IllegalArgumentException("invalid paymentCode");
        }

        Optional<PaymentServer> lookupServer = null;
        if(StringUtils.hasText(paymentType)) {
            lookupServer = resolvedServers.stream().filter(o->o.getPaymentType().equalsIgnoreCase(paymentType)).findAny();
        } else {
            if(activeRandom) {
                Random rand = new Random();
                int select = rand.nextInt(resolvedServers.size());
                lookupServer = Optional.of(resolvedServers.get(select));
            } else {
                lookupServer = resolvedServers.stream().findFirst();
            }
        }

        return lookupServer.orElseThrow(() -> new IllegalArgumentException("invalid paymentType"));
    }
}
