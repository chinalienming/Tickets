package nju.dao;

import nju.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lienming on 2018/3/11.
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer> {


}
