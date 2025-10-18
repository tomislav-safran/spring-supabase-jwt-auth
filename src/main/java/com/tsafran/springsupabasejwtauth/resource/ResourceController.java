package com.tsafran.springsupabasejwtauth.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ResourceController {

    private final PublicResourceService publicResourceService;
    private final RlsResourceService rlsResourceService;

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint, no authentication required";
    }

    @GetMapping("/resource/public")
    public List<PublicResource> getPublicResources() {
        return publicResourceService.getAll();
    }

    @GetMapping("/resource/rls")
    public List<RlsResource> getRlsResources() {
        return rlsResourceService.getAll();
    }
}
