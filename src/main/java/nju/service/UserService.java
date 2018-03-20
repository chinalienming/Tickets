package nju.service;

import nju.entity.TicketRecord;
import nju.entity.User;
import nju.entity.UserInfo;
import nju.vo.UserVO;

import java.util.List;

/**
 * Created by lienming on 2018/3/10.
 */
public interface UserService {


    int register(String email, String password ) ;

    boolean activatAccount(User user);

    int login(String email,String password) ;

    UserInfo getUserInfo(int userID);

    boolean removeBalance(int userID, double amount) ;

    boolean addCredit(int userID, double amount,TicketRecord tr) ;

    boolean drawback(int userID, double return_amount, TicketRecord tr) ;

    List<TicketRecord> getTicketRecordByPage(int userID,int page) ;

    boolean checkEmailExist(String Email);

    UserVO getUserVO(int userID) ;


}
