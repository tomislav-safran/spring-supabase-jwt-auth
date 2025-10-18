package com.tsafran.springsupabasejwtauth.resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublicResourceRepository extends JpaRepository<PublicResource, UUID> {
}