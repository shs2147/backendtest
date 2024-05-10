package com.example.demo.external.service;

import com.example.demo.entity.DemoData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "DemoService")

public interface DemoService {

    @GetMapping("/demo/user/{id}")
    DemoData getData(@PathVariable int id);

    @PostMapping("demo/save")
    DemoData save (@RequestBody DemoData demoData);
}
