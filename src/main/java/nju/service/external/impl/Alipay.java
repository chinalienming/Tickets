package nju.service.external.impl;

import nju.dao.ExternalAccountRepository;
import nju.entity.ExternalAccount;
import nju.service.external.PaymentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lienming on 2018/3/10.
 */
@Service
public class Alipay implements PaymentInterface {

    @Autowired
    private ExternalAccountRepository externalAccountRepository ;

//    public static final int timeOff_seconds = 900;

    //operation in Alipay
    public boolean transfer
        (ExternalAccount from_ea , ExternalAccount to_ea, double request_amount)  {

        //check balance of from_account_id in database_stub
        if(!checkBalance(from_ea,request_amount)){
            return false ;
        }

        from_ea.setBalance(from_ea.getBalance()-request_amount);
        to_ea.setBalance(to_ea.getBalance()+request_amount);
        externalAccountRepository.save(from_ea) ;
        externalAccountRepository.save(to_ea) ;

//        // begin counting time
//        TimeCounter tc = new TimeCounter() ;
//        try {
//            tc.count(timeOff_seconds);
//        } catch (InterruptedException e) {
//            return true ;
//        }
//        if time pass deadline return false
        return false ;
    }

    private boolean checkBalance(ExternalAccount ea , double request_amount) {
        if ( ea.getBalance() < request_amount )
            return false ;
        return true ;
    }



}
