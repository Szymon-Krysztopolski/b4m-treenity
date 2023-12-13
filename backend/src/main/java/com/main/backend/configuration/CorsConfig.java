package com.main.backend.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(addPorts(
                        getUrls("localhost", "127.0.0.1"),
                        "", ":3000")
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    private String[] addPorts(List<String> urls, String... ports) {
        return Arrays.stream(ports)
                .flatMap(port -> urls.stream().map(url -> url + port))
                .toArray(String[]::new);
    }

    private List<String> getUrls(String... ips) {
        return Arrays.stream(ips)
                .flatMap(ip -> Arrays.stream(new String[]{
                        "http://" + ip,
                        "https://" + ip
                }))
                .collect(Collectors.toList());
    }
}
