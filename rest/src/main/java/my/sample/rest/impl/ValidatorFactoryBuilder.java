package my.sample.rest.impl;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

import javax.el.ExpressionFactory;
import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.validation.ValidationProviderResolver;
import javax.validation.ValidatorFactory;
import javax.validation.spi.ValidationProvider;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author mohammad shamsi <m.h.shams@gmail.com>
 */
public class ValidatorFactoryBuilder {

    public ValidatorFactory build() {
        return Validation.byDefaultProvider()
                .providerResolver(new OsgiServiceDiscoverer())
                .configure()
                .messageInterpolator(new DummyMessageInterpolator())
                .buildValidatorFactory();
    }

    private static class OsgiServiceDiscoverer implements ValidationProviderResolver {

        @Override
        public List<ValidationProvider<?>> getValidationProviders() {
            return Arrays.asList(new HibernateValidator());
        }
    }

    private static class DummyMessageInterpolator implements MessageInterpolator {
        private MessageInterpolator delegatee = new ResourceBundleMessageInterpolator();

        @Override
        public String interpolate(String messageTemplate, Context context) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            try {
                Thread.currentThread().setContextClassLoader(ExpressionFactory.class.getClassLoader());
                return delegatee.interpolate(messageTemplate, context);
            } finally {
                Thread.currentThread().setContextClassLoader(contextClassLoader);
            }
        }

        @Override
        public String interpolate(String messageTemplate, Context context, Locale locale) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            try {
                Thread.currentThread().setContextClassLoader(ExpressionFactory.class.getClassLoader());
                return delegatee.interpolate(messageTemplate, context, locale);
            } finally {
                Thread.currentThread().setContextClassLoader(contextClassLoader);
            }
        }
    }
}