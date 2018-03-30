package com.iqmsoft.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.iqmsoft.interfaces.MyRepository;
import com.iqmsoft.model.User;

import java.util.List;

@Repository
public class RepositoryMongo implements MyRepository {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void save(Object object) {
        mongoOperations.save(object);
    }

    @Override
    public Object encontrarPorId(long id, Class aClass) {
        return mongoOperations.findById(id, aClass);
    }

    @Override
    public List<User> encontrarPorNome(String name) {
        return mongoOperations.find(Query.query(Criteria.where("nome").is(name)), User.class);
    }

    public void setMongoOps(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
}
