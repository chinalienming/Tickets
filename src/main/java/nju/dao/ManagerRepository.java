package nju.dao;

import nju.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lienming on 2018/3/11.
 */
public interface ManagerRepository extends JpaRepository<Manager,Integer> {
}
