package nju.dao;

import nju.entity.TicketRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lienming on 2018/3/11.
 */
public interface TicketRecordRepository extends JpaRepository<TicketRecord,Integer> {


    List<TicketRecord> findByUserID(int userID);

    Page<TicketRecord> findByUserID(int userID, Pageable pageRequest);
}
