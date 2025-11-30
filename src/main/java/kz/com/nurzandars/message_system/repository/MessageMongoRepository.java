package kz.com.nurzandars.message_system.repository;

import kz.com.nurzandars.message_system.module.MessageMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMongoRepository extends MongoRepository<MessageMongo,Long> {
}
