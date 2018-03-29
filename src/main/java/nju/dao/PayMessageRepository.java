package nju.dao;

import nju.entity.PayMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayMessageRepository extends JpaRepository<PayMessage,Integer> {
    List<PayMessage> findByUserID(int userID) ;
}
