package nju.service.external.impl;

import nju.entity.ExternalAccount;
import nju.service.external.PaymentInterface;
import org.springframework.stereotype.Service;

/**
 * Created by lienming on 2018/3/10.
 */
@Service
public class OnlineBank implements PaymentInterface {
    public boolean transfer(ExternalAccount from_ea , ExternalAccount to_ea, double request_amount){
        return false ;
    }

}
