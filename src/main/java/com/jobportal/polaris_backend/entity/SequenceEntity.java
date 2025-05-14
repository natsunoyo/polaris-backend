package com.jobportal.polaris_backend.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
@Data
public class SequenceEntity {
    @Id
    private String id;
    private Long seq;
}
