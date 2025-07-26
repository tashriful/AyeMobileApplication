package com.aye.web.model.user;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder

public class UserAuth {
    private String id;
    private String name;
    private String username;
    private String designation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private boolean isActive = true;

}
