package nju.dao;

import nju.entity.SitePlan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lienming on 2018/3/11.
 */
public interface SitePlanRepository extends JpaRepository<SitePlan,Integer>{

    List<SitePlan> findBySiteID(int siteID) ;

    Page<SitePlan> findBySiteID(int siteID, Pageable pageRequest) ;
}
