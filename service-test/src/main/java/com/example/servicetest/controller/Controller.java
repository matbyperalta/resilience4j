/**
 * Copyright 2021, Banco Scotiabank Colpatria S.A.
 * Digital Factory - Scotiabank Colpatria
 * http://www.scotiabankcolpatria.com
 * <p>
 * All rights reserved Date: 8/23/21
 */

package com.example.servicetest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 *
 *
 * @since //TODO: version when the type was created
 * @version //TODO: version when the type was edited
 * @author <a href="matbyperalta@scotiabankcolpatria.com.co">Firstname Lastname</a>
 */
@RestController
public class Controller {

    private static int count = 0;

    @GetMapping("/test")
    public String test(){
        return "success";
    }

    @GetMapping("/time-out")
    public String timeOut(){
        count++;
        //Will fail 50% of the time
        double random = Math.random();
        if (random < 0.5) {
            System.out.println(count +" "+new Date() +" "+"Success");
            return "Success...";
        } else {
            try {
                System.out.println(count +" "+new Date() +" "+"Delaying Execution");
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Exception";
    }

}
