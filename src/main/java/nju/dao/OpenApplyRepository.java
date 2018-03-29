package nju.dao;

import nju.entity.OpenApply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenApplyRepository extends JpaRepository<OpenApply,Integer> {

    boolean existsBySiteIDAndState(int siteID,int state) ;


}
