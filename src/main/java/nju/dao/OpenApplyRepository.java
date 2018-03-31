package nju.dao;

import nju.entity.EditApply;
import nju.entity.OpenApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenApplyRepository extends JpaRepository<OpenApply,Integer> {

    boolean existsBySiteIDAndState(int siteID,int state) ;


    List<OpenApply> findByState(int state);

    Page<OpenApply> findByState(int state, Pageable pageRequest) ;


}
