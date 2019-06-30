package com.xjsaber.learn.spring.springboot.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.xjsaber.learn.spring.springboot.pojo.MongoUser;
import com.xjsaber.learn.spring.springboot.pojo.User;
import com.xjsaber.learn.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xjsaber
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    public UserServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void printUser(User user) {
        if (user == null){
            throw new RuntimeException("检查用户参数是否为空......");
        }
        System.out.println("id = " + user.getId());
        System.out.println("\tusername = " + user.getUsername());
        System.out.println("\tpassword = " + user.getPassword());
    }

    @Override
    public List<User> findUsers(Object a, Object b) {
        return null;
    }

    /*********************************************************************************
     * MongoDB 信息
     *********************************************************************************/

    @Override
    public List<MongoUser> findUser(String userName, String note, int skip, int limit) {
        // 将用户名称和备注设置为模糊查询准则
        Criteria criteria = Criteria.where("userName").regex(userName).and("note").regex(note);
        Query query = Query.query(criteria).limit(limit).skip(skip);
        return mongoTemplate.find(query, MongoUser.class);
    }

    @Override
    public void saveUser(MongoUser user) {
        // 使用名称为user文档保存用户信息
        mongoTemplate.save(user, "user");
        // 如果文档采用类名首字符为小写，则可以这样保存
        // mongoTemplate.save(user);
    }

    @Override
    public MongoUser getUser(Long id) {
        return mongoTemplate.findById(id, MongoUser.class);
//        只返回第一个
//        Criteria criteriaId = Criteria.where("id").is(id);
//        Query queryId = Query.query(criteriaId);
//        return mongoTemplate.findOne(queryId, MongoUser.class);
    }

    @Override
    public UpdateResult updateUser(Long id, String userName, String note) {
        // 确定要更新的对象
        Criteria criteriaId = Criteria.where("id").is(id);
        Query query = Query.query(criteriaId);
        // 定义更新对象
        Update update = Update.update("userName", userName);
        // 更新第一个文档
        return mongoTemplate.updateFirst(query, update, User.class);
        // 更新多个对象
//        UpdateResult result = mongoTemplate.updateMulti(query, update, User.class);
    }

    @Override
    public DeleteResult deleteUser(Long id) {
        Criteria criteriaId = Criteria.where("id").is(id);
        Query query = Query.query(criteriaId);
        return mongoTemplate.remove(query);
    }

    /**
     * mongo工具类
     */
    private MongoTemplate mongoTemplate = null;

}
