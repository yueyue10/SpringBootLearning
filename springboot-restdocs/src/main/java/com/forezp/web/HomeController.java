package com.forezp.web;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> greeting() {
        return Collections.singletonMap("message", "Hello World");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam(value = "string") String string) {
        return "hello " + string;
    }
}