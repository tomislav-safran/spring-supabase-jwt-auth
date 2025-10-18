package com.tsafran.springsupabasejwtauth.resource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "rls_resources")
public class RlsResource {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "name", length = Integer.MAX_VALUE)
    private String name;

}