package com.aye.web.model.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AttachmentMDto {

    private String id;
    private String fileName;
    private String contentType;
    private long size;
    private String downloadUrl;
    private byte[] content;

}
