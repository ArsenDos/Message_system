package kz.com.nurzandars.message_system.repository;

import kz.com.nurzandars.message_system.module.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
