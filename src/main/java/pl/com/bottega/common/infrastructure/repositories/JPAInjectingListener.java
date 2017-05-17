package pl.com.bottega.common.infrastructure.repositories;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.events.EventPublisher;
import pl.com.bottega.common.domain.events.EventPublisherAware;
import pl.com.bottega.common.domain.time.TimeService;
import pl.com.bottega.common.domain.time.TimeServiceAware;

import javax.persistence.PostLoad;

@Component
public class JPAInjectingListener implements ApplicationContextAware {

    private static ApplicationContext ctx;

    @PostLoad
    public void injectServices(BaseAggregateRoot aggregateRoot) {
        if(aggregateRoot instanceof TimeServiceAware)
            ((TimeServiceAware)aggregateRoot).setTimeService(timeService());
    }

    private TimeService timeService() {
        return ctx.getBean(TimeService.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
