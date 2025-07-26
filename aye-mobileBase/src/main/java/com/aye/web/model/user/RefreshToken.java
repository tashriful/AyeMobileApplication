package com.aye.web.model.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document(collection ="R_TOKEN_M" )
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class RefreshToken {

    @Id
    private String refreshToken;
    private Instant expiry;
    private UserM userM;

}
