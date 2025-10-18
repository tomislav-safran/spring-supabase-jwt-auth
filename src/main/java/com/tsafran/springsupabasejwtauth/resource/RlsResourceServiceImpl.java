package com.tsafran.springsupabasejwtauth.resource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RlsResourceServiceImpl implements RlsResourceService {
    private final RlsResourceRepository rlsResourceRepository;

    @Override
    public List<RlsResource> getAll() {
        return rlsResourceRepository.findAll();
    }
}
