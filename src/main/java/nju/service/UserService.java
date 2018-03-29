package nju.service;

import nju.entity.PayMessage;
import nju.entity.TicketRecord;
import nju.entity.User;
import nju.entity.UserInfo;
import nju.util.SystemDefault;
import nju.vo.UserVO;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.Map;

/**
 * Created by lienming on 2018/3/10.
 */
public interface UserService {


    int register(String email, String password);

    boolean activatAccount(int userID);

    int login(String email, String password);

    UserInfo getUserInfo(int userID);

    boolean removeBalance(int userID, double amount);

    boolean addCredit(TicketRecord tr);

    boolean drawback(int userID, double return_amount, TicketRecord tr);

    List<TicketRecord> getTicketRecordByPage(int userID, int page);

    List<TicketRecord> getValidTicketRecord(int userID);

    List<TicketRecord> getInvalidTicketRecord(int userID);

    List<PayMessage> getPayMessage(int userID) ;

    boolean checkEmailExist(String Email);

    UserVO getUserVO(int userID);

    int getUserIDByEmailAndPassword(String email, String password);

    void edit(int userID, String userName, int aID, String aPwd);

    int recharge(int userID,int amount) ;

    int convert(int userID,int credit) ;

    int froze(int userID,String pwd) ;


}
