package com.toyota.bms.config; import org.springframework.context.annotation.*; import org.springframework.web.servlet.config.annotation.*;
@Configuration public class CorsConfig implements WebMvcConfigurer
{@Override public void addCorsMappings(CorsRegistry r)
{r.addMapping("/api/**")
        .allowedOriginPatterns("http://localhost:4200",
                "http://127.0.0.1:4200",
                "${FRONTEND_URL:http://localhost:4200}")
        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS").allowedHeaders("*");}}
