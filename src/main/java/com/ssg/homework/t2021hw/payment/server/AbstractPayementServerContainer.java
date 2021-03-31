package com.ssg.homework.t2021hw.payment.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractPayementServerContainer implements PaymentServerContainer {

    private Map<String, List<PaymentServer>> resolvedPaymentServers;

    public void setApplyTargetServers(Map<String, List<PaymentServer>> applyTargetServers) {
        resolvedPaymentServers = applyTargetServers;
    }

    public void addServer(PaymentServer... servers) {
        for (PaymentServer server : servers) {
            List<PaymentServer> list = resolvedPaymentServers.getOrDefault(server.getPaymentCode(), new ArrayList<>());
            list.add(server);
            resolvedPaymentServers.put(server.getPaymentCode(), list);
        }
    }

    @Override
    public PaymentServer getActiveServer(String paymentCode, String paymentType) {
        return determineTargetServer(paymentCode, paymentType);
    }

    protected abstract PaymentServer determineTargetServer(String paymentCode, String paymentType);

    protected List<PaymentServer> getResolvedServers(String key) {
        return resolvedPaymentServers.get(key);
    }
}
