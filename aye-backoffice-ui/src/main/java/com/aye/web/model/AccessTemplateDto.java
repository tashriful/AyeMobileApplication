package com.aye.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AccessTemplateDto {


    private String id;
    private String tempDescription;
    private String tempNumber;


}
