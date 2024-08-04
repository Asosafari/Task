package com.example.rayan_task.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * Author: ASOU SAFARI
 * Date:8/3/24
 * Time:4:46 PM
 */

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class User{
    @Id
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)",updatable = false,nullable = false)
    private UUID id;
    private String username;
    private String password;
    private String token;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime tokenCreatedTime;
    @Version
    private Integer version;

}
