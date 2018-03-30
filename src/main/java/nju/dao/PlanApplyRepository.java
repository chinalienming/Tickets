package nju.dao;

import nju.entity.PlanApply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lienming on 2018/3/30.
 */
public interface PlanApplyRepository extends JpaRepository<PlanApply,Integer> {

    List<PlanApply> findBySiteID(int siteID) ;
}
