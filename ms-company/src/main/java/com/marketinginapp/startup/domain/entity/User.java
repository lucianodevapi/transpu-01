package com.marketinginapp.startup.domain.entity;

import com.marketinginapp.startup.utils.enums.EStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String profile;

    private LocalDateTime created;
    private LocalDateTime updated;
    private EStatus status;

    @DBRef
    private List<Role> roles = new ArrayList<>();
}
