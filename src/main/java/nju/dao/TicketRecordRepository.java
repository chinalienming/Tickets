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

    List<TicketRecord> findBySiteID(int siteID) ;

    Page<TicketRecord> findByUserID(int userID, Pageable pageRequest);

    List<TicketRecord> findByUserIDAndIsValid(int userID,int isValid);

    List<TicketRecord> findBySiteIDAndPayType(int siteID,int payType);

    List<TicketRecord> findBySiteIDAndIsValid(int siteID,int isValid);

    List<TicketRecord> findByPlanIDAndPayType(int planID,int payType) ;

    List<TicketRecord> findByUserIDAndPayType(int userID,int payType) ;

    List<TicketRecord> findByPayType(int payType) ;

    List<TicketRecord> findByIsValid(int isValid) ;
}
