package com.aye.web.model.user;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class AuthResponseDto {

    private String accessToken;
    private String refreshToken;
    private UserAuth userInfo;
    private List<UserMenuAuth> userAccessList;
}
