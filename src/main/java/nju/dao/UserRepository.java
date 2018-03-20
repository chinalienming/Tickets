package nju.dao;

import nju.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lienming on 2018/3/10.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email) ;

    User findByEmailAndPassword(String email,String password);

}
