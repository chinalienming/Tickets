package nju.service.external.impl;

import nju.dao.ExternalAccountRepository;
import nju.entity.ExternalAccount;
import nju.service.PlanService;
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

//    @Autowired
//    private PlanService planService ;

    public double transfer
        (ExternalAccount from_ea ,int from_userID ,int planID, double request_amount)  {



        //check balance of from_account_id in database_stub
        if(!checkBalance(from_ea,request_amount)){
            return -4 ;
        }

        from_ea.setBalance(from_ea.getBalance()-request_amount);

//        if(planService==null) {
//            System.out.println("planService is null!");
//            return -5;
//        }

       // externalAccountRepository.save(from_ea) ;

        return request_amount ;
    }

    private boolean checkBalance(ExternalAccount ea , double request_amount) {
        if ( ea.getBalance() < request_amount )
            return false ;
        return true ;
    }



}
