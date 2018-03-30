package nju.dao;

import nju.entity.SiteTR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteTRRepository extends JpaRepository<SiteTR,Integer> {

    List<SiteTR> findBySiteID(int siteID) ;

}
