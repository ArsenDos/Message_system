package kz.com.ars.message_system.repository;

import kz.com.ars.message_system.module.MessageMongo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageMongoRepository extends ReactiveMongoRepository<MessageMongo,Long> {
}
