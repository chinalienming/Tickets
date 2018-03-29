package nju.dao;

import nju.entity.EditApply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditApplyRepository  extends JpaRepository<EditApply,Integer> {

    boolean existsBySiteIDAndState(int siteID,int state) ;

}
