package com.aye.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserAccessMDto {

    private String id;

    private String accessNumber;

    private UserDto user;
    private AccessTemplateDto userAccessTemplateM;
    //Access type - Android/Web APP
    private String userAccessType;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

}
