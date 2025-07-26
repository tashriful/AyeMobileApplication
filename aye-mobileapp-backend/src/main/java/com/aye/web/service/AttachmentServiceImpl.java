package com.aye.web.service;

import com.aye.web.model.ar.AttachmentM;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    private GridFsTemplate template;

    @Autowired
    private GridFsOperations operations;

    @Override
    public List<String> addFileList(List<MultipartFile> uploadList) throws IOException {
        List<String> fileIds = new ArrayList<>();

        for (MultipartFile upload : uploadList) {
            DBObject metadata = new BasicDBObject();
            metadata.put("fileSize", upload.getSize());

            Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);

            if (!fileID.equals("")) {
                fileIds.add(fileID.toString());
            } else {
                throw new IOException("File not save or file id not found");
            }
        }
        return fileIds;
    }

    @Override
    public String addFile(MultipartFile upload) throws IOException {
            DBObject metadata = new BasicDBObject();
            metadata.put("fileSize", upload.getSize());

            Object fileID = template.store(upload.getInputStream(), upload.getOriginalFilename(), upload.getContentType(), metadata);

            if (!fileID.equals("")) {
                return fileID.toString();
            } else {
                throw new IOException("File not save or file id not found");
            }
        }

    @Override
    public AttachmentM downloadFile(String id) throws IOException {

        GridFSFile gridFSFile = template.findOne( new Query(Criteria.where("_id").is(id)) );

        AttachmentM loadFile = new AttachmentM();

        if (gridFSFile != null && gridFSFile.getMetadata() != null) {
            loadFile.setId(gridFSFile.getObjectId().toString());
            loadFile.setFileName( gridFSFile.getFilename() );

            loadFile.setContentType( gridFSFile.getMetadata().get("_contentType").toString() );

            loadFile.setSize((Long) gridFSFile.getMetadata().get("fileSize"));

            loadFile.setContent( IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()));
        }

        return loadFile;
    }
}
