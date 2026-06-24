package com.deltags.controller;

import com.deltags.common.Result;
import com.deltags.dto.SchemeSaveRequest;
import com.deltags.entity.UserScheme;
import com.deltags.service.SchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchemeController {

    private final SchemeService schemeService;

    @GetMapping("/api/schemes")
    public Result<List<UserScheme>> listMySchemes(@RequestParam Long userId) {
        return Result.ok(schemeService.listMySchemes(userId));
    }

    @PostMapping("/api/schemes")
    public Result<Long> saveScheme(@RequestParam Long userId,
                                   @RequestBody SchemeSaveRequest request) {
        return Result.ok(schemeService.saveScheme(userId, request));
    }
}
