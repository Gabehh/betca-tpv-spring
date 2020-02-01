package es.upm.miw.betca_tpv_spring;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Profile("dev")
@Component
@Aspect
public class ApiLogs {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void allResources() {
        // don't need code
    }

    @Before("allResources()")
    public void apiRequestLog(JoinPoint jp) {
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName())
                .info("------------------------------- o -------------------------------");
        StringBuilder log = new StringBuilder(jp.getSignature().getName() + " >>>");
        for (Object arg : jp.getArgs()) {
            log.append("; ARG: ").append(arg);
        }
        LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).info(log);
    }

    @AfterReturning(pointcut = "allResources()", returning = "returnValue")
    public void apiResponseLog(JoinPoint jp, Object returnValue) {
        Consumer<Object> consumer = result -> {
            String log = "<<< Return << " + jp.getSignature().getName() + ": " + result.toString();
            if (log.length() > 1000) {
                log = log.substring(0, 1000) + ".... (+" + log.length() + " characters)";
            }
            LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).debug(log);
        };
        Consumer<Throwable> error = throwable -> {
            String log = "<<< Return Exception << " + jp.getSignature().getName() + ": " + throwable.toString();
            LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).debug(log);
        };
        String result = returnValue.getClass().getSimpleName().substring(0, 4);
        if ("Flux".equals(result)) {
            ((Flux<Object>) returnValue).subscribe(consumer, error);
        } else if ("Mono".equals(result)) {
            ((Mono<Object>) returnValue).subscribe(consumer, error);
        } else {
            LogManager.getLogger(jp.getSignature().getDeclaringTypeName()).debug("???: " + returnValue.toString());
        }
    }

}
