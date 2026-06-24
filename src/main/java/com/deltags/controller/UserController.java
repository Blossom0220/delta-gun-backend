package com.deltags.controller;

import com.deltags.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    @PostMapping("/api/login/wechat")
    public Result<Map<String, String>> mockWechatLogin(@RequestParam String code) {
        String token = "mock-token-" + UUID.randomUUID();
        return Result.ok(Map.of("token", token));
    }
}
