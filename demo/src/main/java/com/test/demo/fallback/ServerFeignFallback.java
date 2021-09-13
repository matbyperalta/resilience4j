package com.test.demo.fallback;

import com.test.demo.feign.ServerFeign;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;

public class ServerFeignFallback implements ServerFeign {

    private Exception cause;

    public ServerFeignFallback(Exception cause){
        this.cause = cause;
    }

    @Override
    public String test() {
        return null;
    }

    @Override
    public String timeOut() {
        if (cause instanceof FeignException) {
            //TODO: guardar en BD el mensaje enviado si hay un FeignException
            System.out.println("FeignException");
            return "Feign Exception";
        } else if (cause instanceof CallNotPermittedException){
            //TODO: guardar en BD el mensaje enviado si el circuito esta abierto
            System.out.println("CallNotPermittedException");
            return "CallNotPermitted exception";
        }else{
            return "other exception";
        }
    }
}
