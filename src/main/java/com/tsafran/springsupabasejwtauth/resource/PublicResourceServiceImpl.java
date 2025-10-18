package com.tsafran.springsupabasejwtauth.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PublicResourceServiceImpl implements PublicResourceService {
    private final PublicResourceRepository publicResourceRepository;

    @Override
    public List<PublicResource> getAll() {
        return publicResourceRepository.findAll();
    }
}
