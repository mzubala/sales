package pl.com.bottega.sales.infrastructure;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.logging.Logger;

@Component
@Aspect
public class JpaPerformanceAspect {

    private static final long THREASHOLD = 30;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Around("execution(* pl.com.bottega.sales.application..*.*(..))")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Statistics statistics = sessionFactory.getStatistics();
        statistics.setStatisticsEnabled(true);
        try {
            Object ret = joinPoint.proceed();
            checkQueries(statistics);
            statistics.setStatisticsEnabled(false);
            return ret;
        } catch (Throwable ex) {
            checkQueries(statistics);
            statistics.setStatisticsEnabled(false);
            throw ex;
        }
    }

    private void checkQueries(Statistics statistics) {
        if (statistics.getPrepareStatementCount() > THREASHOLD)
            Logger.getLogger(JpaPerformanceAspect.class.getName()).warning("!!!!!!!!!!!!!! TO MANY QUERIES !!!!!!!!!!!!!!");
    }

}
