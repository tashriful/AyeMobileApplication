package com.aye.web.model.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;


@Document(collection = "USER_ACCESS_TEMPLATE_M")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserAccessTemplateM {

    @Id
    private String id;
    private String tempDescription;
    private String tempNumber;
    @DBRef
    @JsonManagedReference
    private List<UserAccessTemplateDetailsM> userAccessTemplateDetailsMList;
    @DBRef
    private UserM createdBy;
    @CreatedDate
    private Date cretedAt;
    @DBRef
    private UserM updatedBy;
    @LastModifiedDate
    private Date updatedAt;
}
