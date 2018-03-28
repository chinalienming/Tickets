package nju.service.external;

import nju.entity.ExternalAccount;
import nju.service.external.impl.Alipay;
import nju.service.external.impl.OnlineBank;

/**
 * Created by lienming on 2018/3/10.
 */
public interface PaymentInterface {

    static PaymentInterface getInstance(String interface_name){

        if(interface_name.equals("Alipay")) {
            return new Alipay();
        }

//        if(interface_name.equals("OnlineBank")) {
//            return new OnlineBank();
//        }

        return null ;
    }

    double transfer(ExternalAccount from_ea ,int from_userID, int planID , double request_amount);


}
