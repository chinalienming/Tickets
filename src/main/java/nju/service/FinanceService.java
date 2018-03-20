package nju.service;

import nju.entity.ExternalAccount;
import nju.entity.TicketRecord;

/**
 * Created by lienming on 2018/3/10.
 */
public interface FinanceService {

    double transfer2plan(int userID, int planID, int[] ticketNum);

    boolean requestInternalInterface(int userID, int planID, double total_price) ;

    boolean requestExternalInterface(int userID, int planID, double total_price);

    boolean cancelFromSite(TicketRecord tr, double return_amount);

    boolean transfer2Site(int planID , int siteID);

    ExternalAccount findExternalAccount(int userID,boolean isSite);


}