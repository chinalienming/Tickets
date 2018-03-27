package nju.service.impl;

import nju.dao.ExternalAccountRepository;
import nju.dao.TicketRecordRepository;
import nju.dao.UserInfoRepository;
import nju.dao.UserRepository;
import nju.entity.ExternalAccount;
import nju.entity.TicketRecord;
import nju.entity.User;
import nju.entity.UserInfo;
import nju.service.UserService;
import nju.util.SystemDefault;
import nju.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lienming on 2018/3/10.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private TicketRecordRepository ticketRecordRepository;

    @Autowired
    private ExternalAccountRepository externalAccountRepository ;


    //String format = String.format("%05d", i);

    public int register(String email,String password) {
        //check email illegal or exist ?
        boolean email_legal = SystemDefault.checkEmailLegal(email) ;
        if(!email_legal) {
            return -1 ;
        }

        boolean email_exist = checkEmailExist(email) ;
        if(email_exist) {
            return -2 ;
        }

        //check password illegal ?

        boolean password_legal = SystemDefault.checkString(password) ;
        if(!password_legal) {
            return -3 ;
        }

        //register,updateToDatabase

        User user = new User(email,password) ;

        User user_getID = userRepository.save(user) ;

        UserInfo userInfo = new UserInfo();
        userInfo.setUserID(user_getID.getUserID());
        userInfoRepository.save(userInfo) ;

        return user_getID.getUserID();
    }

    //
    public boolean activatAccount(int userID){
        User user = userRepository.findById(userID).get();
        if(user==null)
            return false ;
        user.setActivated(true);
        user.setHasLoginQualification(true);
        userRepository.save(user) ;
        return true ;
    }

    public int login(String email,String password) {

        User user = userRepository.findByEmailAndPassword(email,password) ;

        if( null == user )  return -1 ;
        if( user.getActivated() == false )  return -2 ;
        if( user.getHasLoginQualification() == false )  return -3 ;

        return user.getUserID() ;
    }

    public UserInfo getUserInfo(int userID) {
        return userInfoRepository.findById(userID).get();
    }

    public boolean removeBalance(int userID, double amount){
        UserInfo userInfo = getUserInfo(userID) ;
        boolean remove = userInfo.removeBalance(amount);
        userInfoRepository.save(userInfo) ;
        return remove ;
    }

    public boolean addCredit(int userID, double amount,TicketRecord tr) {
        UserInfo userInfo = getUserInfo(userID) ;
        userInfo.addCredit(amount,tr);

        userInfoRepository.save(userInfo) ;
        ticketRecordRepository.save(tr) ;

        return true ;
    }



    public boolean drawback(int userID, double return_amount, TicketRecord tr){  // need credit punishment ?
        UserInfo userInfo = getUserInfo(userID) ;
        userInfo.drawback(return_amount,tr);

        userInfoRepository.save(userInfo) ;
        ticketRecordRepository.save(tr) ;
        return true ;
    }



    public boolean checkEmailExist(String email) {
        return userRepository.existsByEmail(email) ;
}

    public UserVO getUserVO(int userID) {
        UserVO vo = new UserVO() ;

        User user = userRepository.findById(userID).get() ;
        UserInfo userInfo = getUserInfo(userID) ;
        BeanUtils.copyProperties(user , vo);
        BeanUtils.copyProperties(userInfo,vo);

        ExternalAccount ea = externalAccountRepository.findByUserIDAndIsSite(userID,false);
        if(ea!=null) {
            vo.accountID = ea.getAccountID() ;
            vo.accountBalance = ea.getBalance() ;
        }
        return vo;
    }


    public List<TicketRecord> getTicketRecordByPage(int userID,int page) {

        List<TicketRecord> result = new ArrayList<>() ;
        Page<TicketRecord> pages ;
        if (page >= 0) {
            pages = ticketRecordRepository.findByUserID(userID,new PageRequest(page, SystemDefault.SIZE_PER_PAGE_OF_SITE));
            pages.forEach(result::add);
        } else {
            ticketRecordRepository.findByUserID(userID).forEach(result::add);
        }
        return result;
    }

    public List<TicketRecord> getTicketRecord(int userID) {

        List<TicketRecord> result = new ArrayList<>() ;

        ticketRecordRepository.findByUserID(userID).forEach(result::add);

        return result;
    }

    public int getUserIDByEmailAndPassword(String email,String password) {

        User user = userRepository.findByEmailAndPassword(email,password) ;
        if(user==null)
            return -1 ;
        return user.getUserID() ;
    }

}
