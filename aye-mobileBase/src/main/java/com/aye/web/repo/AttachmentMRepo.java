package com.aye.web.repo;

import com.aye.web.model.ar.AttachmentM;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AttachmentMRepo extends MongoRepository<AttachmentM, String> {
}
