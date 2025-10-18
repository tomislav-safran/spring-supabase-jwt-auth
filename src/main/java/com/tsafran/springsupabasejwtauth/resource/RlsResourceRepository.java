package com.tsafran.springsupabasejwtauth.resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RlsResourceRepository extends JpaRepository<RlsResource, UUID> {
}