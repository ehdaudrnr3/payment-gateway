package com.ssg.homework.t2021hw.entity.generator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class StringPrefixSequenceIdGenerator extends SequenceStyleGenerator {

    public static final String CLASS_NAME = "com.ssg.homework.t2021hw.entity.generator.StringPrefixSequenceIdGenerator";

    public static final String PREFIX_PARAMETER = "prefix";
    private final String PREFIX_DEFAULT = "";
    private String prefix;

    public static final String NUMBER_FORMAT_PARAMETER = "numberFormat";
    private final String NUMBER_FORMAT_DEFAULT = "%d";
    private String numberFormat;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        prefix = ConfigurationHelper.getString(PREFIX_PARAMETER, params, PREFIX_DEFAULT);
        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, params, NUMBER_FORMAT_DEFAULT);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return prefix + String.format(numberFormat, super.generate(session, object));
    }

}
