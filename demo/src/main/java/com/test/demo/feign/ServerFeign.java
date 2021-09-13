package com.test.demo.feign;
import feign.RequestLine;

public interface ServerFeign {

    @RequestLine("GET /test")
    public String test();

    @RequestLine("GET /time-out")
    public String timeOut();

}
