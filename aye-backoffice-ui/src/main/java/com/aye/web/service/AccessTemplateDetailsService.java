package com.aye.web.service;

import com.aye.web.model.AccessTemplateDetailsMDto;

import java.util.List;

public interface AccessTemplateDetailsService {
    List<AccessTemplateDetailsMDto> getAccessTemplatesDetails(String accessToken);

    List<AccessTemplateDetailsMDto> getAccessTemplatesDetailsByTemplate(String templateId, String accessToken);
    AccessTemplateDetailsMDto saveAccessTemplateDetails(AccessTemplateDetailsMDto accessTemplateDetailsMDto, String accessToken);
    AccessTemplateDetailsMDto getAccessTemplateDetailsById(String id, String accessToken);
}
