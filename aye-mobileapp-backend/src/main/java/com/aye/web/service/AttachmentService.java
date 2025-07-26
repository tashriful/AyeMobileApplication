package com.aye.web.service;

import com.aye.web.model.ar.AttachmentM;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {

    List<String> addFileList(List<MultipartFile> upload) throws IOException;
    String addFile(MultipartFile upload) throws IOException;
    AttachmentM downloadFile(String id) throws IOException;
}
