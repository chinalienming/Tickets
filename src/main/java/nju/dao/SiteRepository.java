package nju.dao;

import nju.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lienming on 2018/3/11.
 */
public interface SiteRepository extends JpaRepository<Site,String> {

    boolean existsBySiteName (String siteName) ;

}
