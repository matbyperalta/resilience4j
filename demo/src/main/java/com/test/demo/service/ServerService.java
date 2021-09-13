/**
 * Copyright 2021, Banco Scotiabank Colpatria S.A.
 * Digital Factory - Scotiabank Colpatria
 * http://www.scotiabankcolpatria.com
 * <p>
 * All rights reserved Date: 8/31/21
 */

package com.test.demo.service;

import com.test.demo.exception.ServerException;
import com.test.demo.fallback.ServerFeignFallback;
import com.test.demo.feign.ServerFeign;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.feign.FeignDecorator;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

/**
 *
 *
 * @since //TODO: version when the type was created
 * @version //TODO: version when the type was edited
 * @author <a href="matbyperalta@scotiabankcolpatria.com.co">Firstname Lastname</a>
 */
@Service
public class ServerService {

    private final CircuitBreaker circuitBreaker;

    public ServerService(@Qualifier("circuitBrakerBean") CircuitBreaker circuitBreaker){
        this.circuitBreaker = circuitBreaker;
    }

    public static int count = 0;

    public void process() {

        try {
            count++;
            System.out.println(count +" "+circuitBreaker.getState());
            String string = getServerFeign().timeOut();

            if (Optional.of(string).isPresent()
                    && string.equals("Exception")) {
                //TODO: guarda en la BD si DP responde con error controlado de time-out
                System.out.println("ERROR CONTROLADO DE Data Power DE TIMEOUT");
            }
        }catch (CallNotPermittedException callNotPermittedException){
            System.out.println(circuitBreaker.getName()+" "+circuitBreaker.getState()+" //TODO");
        }
    }

    private ServerFeign getServerFeign(){
        FeignDecorators decorators = FeignDecorators.builder()
                .withCircuitBreaker(circuitBreaker)
                .withFallbackFactory(ServerFeignFallback::new)
                .build();
        return Resilience4jFeign.builder(decorators).target(ServerFeign.class,"http://localhost:8090/server-api");
    }


}
