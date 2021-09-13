/**
 * Copyright 2021, Banco Scotiabank Colpatria S.A.
 * Digital Factory - Scotiabank Colpatria
 * http://www.scotiabankcolpatria.com
 * <p>
 * All rights reserved Date: 8/23/21
 */

package com.test.demo.controller;

import com.test.demo.exception.ServerException;
import com.test.demo.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private ServerService serverService;

    @GetMapping("/test")
    public String test() {

        serverService.process();

        return "success";
    }



}
