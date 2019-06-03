package com.xjsaber.learn.spring.springboot.dao;

import com.xjsaber.learn.spring.springboot.pojo.MongoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author xjsaber
 */
@Repository
public class MongoUserRepositoryImpl {

    private final MongoTemplate mongoTemplate;

    public MongoUserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoUser findUserByIdOrUserName(Long id, String userName){
        Criteria criteriaId = Criteria.where("id").is(id);
        Criteria criteriaUserName = Criteria.where("userName").is(userName);
        Criteria criteria = new Criteria();
        criteria.orOperator(criteriaId, criteriaUserName);
        Query query = Query.query(criteria);
        return mongoTemplate.findOne(query, MongoUser.class);
    }
}
