package com.aye.web.service;

import com.aye.web.model.AccessTemplateDto;

import java.util.List;

public interface AccessTemplateService {
    List<AccessTemplateDto> getAccessTemplates(String accessToken);
    AccessTemplateDto saveAccessTemplate(AccessTemplateDto accessTemplateDto, String accessToken);
    AccessTemplateDto getAccessTemplateById(String id, String accessToken);
}
