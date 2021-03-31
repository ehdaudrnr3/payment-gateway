package com.ssg.homework.t2021hw.payment.server;

import com.ssg.homework.t2021hw.entity.PaymentMaster;
import com.ssg.homework.t2021hw.repository.PaymentMasterRepository;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentServerInitializer implements InitializingBean {

    private final PaymentMasterRepository repository;

    private List<PaymentServer> serverList;

    public PaymentServerInitializer(PaymentMasterRepository repository) {
        this.repository = repository;
    }

    public List<PaymentServer> getApplyServers() {
        return serverList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<PaymentMaster> all = repository.findAll();
        serverList = all.stream()
                .map(o -> new PaymentServer(o.getPmtCode(), o.getPmtType(), o.getPmtName(), o.getPartCnclYn()))
                .collect(Collectors.toList());
    }
}
