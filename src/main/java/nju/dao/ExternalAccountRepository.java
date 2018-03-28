package nju.dao;

import nju.entity.ExternalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lienming on 2018/3/11.
 */
public interface ExternalAccountRepository extends JpaRepository<ExternalAccount,Integer> {
    ExternalAccount findByUserIDAndIsSite(int userID,boolean isSite) ;

    ExternalAccount findByAccountIDAndPassword(int accountID,String password) ;

}
