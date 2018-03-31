package nju.dao;

import nju.entity.EditApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EditApplyRepository  extends JpaRepository<EditApply,Integer> {

    boolean existsBySiteIDAndState(int siteID,int state) ;

    List<EditApply> findByState(int state);

    Page<EditApply> findByState(int state,Pageable pageRequest) ;
}
