package com.tsafran.springsupabasejwtauth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "This is an admin endpoint, only authenticated users with role ADMIN can see it";
    }

    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "This is a protected endpoint, only authenticated users can see it";
    }
}
