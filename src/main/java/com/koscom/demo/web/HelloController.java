package com.koscom.demo.web;

import com.koscom.demo.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // (1)
public class HelloController {
    @GetMapping("/hello") // (2)
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount){
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        System.out.println("dto ="+ dto);
        return dto;

    }

}