package com.aye.web.model.ar;

import com.aye.web.model.user.UserM;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Attachment_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AttachmentM {

    private String id;
    private String fileName;
    private String contentType;
    private long size;
    private String downloadUrl;
    private byte[] content;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
