package nju.service;

import nju.entity.ExternalAccount;
import nju.entity.PayMessage;
import nju.entity.TicketRecord;

/**
 * Created by lienming on 2018/3/10.
 */
public interface FinanceService {

    double payByBalance(int recordID) ;

    double payByExternalAccount(int recordID,int account,String pwd) ;

    double transfer2plan(int userID, int planID, int[] ticketNum);

//    boolean requestInternalInterface(int userID, int planID, double total_price) ;
//
//    double requestExternalInterface(int userID, int planID, double total_price);

    boolean cancelFromSite(TicketRecord tr, double return_amount);

    boolean transfer2Site(int planID ,int accountID);

//    ExternalAccount findExternalAccount(int userID,boolean isSite);

    PayMessage generatePayMessage(int userID, String msg, double change);
}
