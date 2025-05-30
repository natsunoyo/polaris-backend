package com.jobportal.polaris_backend.utility;

import com.jobportal.polaris_backend.entity.SequenceEntity;
import com.jobportal.polaris_backend.exception.JobPortalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class Utilities {

    public static MongoOperations mongoOperation;

    @Autowired
    public void setMongoOperation(MongoOperations mongoOperation) throws JobPortalException {
        Utilities.mongoOperation = mongoOperation;
    }

    public static Long getNextSequence(String key) {
        Query query = new Query(Criteria.where("_id").is(key));
        Update update = new Update();
        update.inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);
        SequenceEntity seq = mongoOperation.findAndModify(query, update, options, SequenceEntity.class);
        if(seq==null) try {
            throw new JobPortalException("Unable to get sequence id for key : " + key);
        } catch (JobPortalException e) {
            throw new RuntimeException(e);
        }
        return seq.getSeq();
    }
    public static String generateOTP(){
        StringBuilder otp=new StringBuilder();
        SecureRandom random = new SecureRandom();
        for(int i =0; i<6; i++)otp.append(random.nextInt(10));
        return otp.toString();
    }
}
