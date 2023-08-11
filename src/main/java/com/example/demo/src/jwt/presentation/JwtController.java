package com.example.demo.src.jwt.presentation;

import com.example.demo.src.jwt.application.SecuritiyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/security")
public class JwtController {
    @Autowired
    private SecuritiyService securitiyService;

    @GetMapping("/create/token")
    public Map<String, Object> createToken(@RequestParam(value = "subject") String subject) {
        String token = securitiyService.createToken(subject, 2 * 1000 * 60);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", token);
        return map;
    }

    @GetMapping("/get/subject")
    public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
        String subject = securitiyService.getSubject(token);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", subject);
        return map;
    }
}
