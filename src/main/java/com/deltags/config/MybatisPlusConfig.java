package com.deltags.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.deltags.mapper")
public class MybatisPlusConfig {
}
