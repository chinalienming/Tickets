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
import java.util.Collections;
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

    public boolean addCredit(TicketRecord tr) {
        int userID = tr.getUserID() ;

        UserInfo userInfo = getUserInfo(userID) ;
        userInfo.addCredit(tr);

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
        } else {
            vo.accountID = -1 ;
            vo.accountBalance = 0 ;
        }
        return vo;
    }


    public List<TicketRecord> getTicketRecordByPage(int userID,int page) {

        List<TicketRecord> result = new ArrayList<>() ;
        Page<TicketRecord> pages ;
        if (page >= 0) {
            pages = ticketRecordRepository.findByUserID
                    (userID,new PageRequest(page, SystemDefault.SIZE_PER_PAGE_OF_SITE));

            pages.forEach(x->result.add(x));      //倒序插入
        } else {
            ticketRecordRepository.findByUserID(userID).forEach(x->result.add(x));
//            Collections.reverse(result);
        }
        return result;
    }

    public List<TicketRecord> getValidTicketRecord(int userID) {

        List<TicketRecord> result = new ArrayList<>() ;

        ticketRecordRepository.findByUserIDAndIsValid(userID,SystemDefault.RECORD_STATE_PAYED).forEach(result::add);
        ticketRecordRepository.findByUserIDAndIsValid(userID,SystemDefault.RECORD_STATE_WAITPAY).forEach(result::add);
        Collections.reverse(result);

        return result;
    }

    public List<TicketRecord> getInvalidTicketRecord(int userID) {
        List<TicketRecord> result = new ArrayList<>() ;
        ticketRecordRepository.findByUserIDAndIsValid(userID,SystemDefault.RECORD_STATE_CANCEL).forEach(result::add);
        ticketRecordRepository.findByUserIDAndIsValid(userID,SystemDefault.RECORD_STATE_TIMEOUT).forEach(result::add);
        Collections.reverse(result);
        return result ;
    }

    public int getUserIDByEmailAndPassword(String email,String password) {

        User user = userRepository.findByEmailAndPassword(email,password) ;
        if(user==null)
            return -1 ;
        return user.getUserID() ;
    }

    public void edit(int userID, String userName, int aID, String aPwd) {
        UserInfo userInfo = userInfoRepository.findById(userID).get() ;
        userInfo.setUserName(userName);
        userInfoRepository.save(userInfo) ;

        ExternalAccount ea = externalAccountRepository.findByUserIDAndIsSite(userID,false);
        if(ea.getAccountID()!=aID) {
            //更换支付宝账号
            externalAccountRepository.delete(ea);
            ExternalAccount nea = new ExternalAccount(aID,aPwd,userID) ;
            //如果aID重复? error !


            nea = externalAccountRepository.save(nea) ;
            System.out.println("new External Account ID :" + nea.getAccountID() ) ;
        } else {
            ea.setPassword(aPwd);
            externalAccountRepository.save(ea) ;
        }

    }

    public int recharge(int userID,int amount) {
        ExternalAccount ea = externalAccountRepository.findByUserIDAndIsSite(userID,false) ;
        double ori = ea.getBalance();
        if(ori<amount) {
            return -1 ;
        }
        ea.setBalance(ori-amount);
        externalAccountRepository.save(ea) ;

        UserInfo userInfo = userInfoRepository.findById(userID).get() ;
        userInfo.setBalance(userInfo.getBalance()+amount);
        userInfoRepository.save(userInfo) ;

        return 0 ;
    }

    public int convert(int userID,int credit) {

        UserInfo userInfo = userInfoRepository.findById(userID).get() ;
        userInfo.setCredit(userInfo.getCredit()-credit);
        userInfo.setBenefit(userInfo.getBenefit()+credit);
        userInfoRepository.save(userInfo) ;
        return 0 ;
    }

    public int froze(int userID,String pwd) {
        User user = userRepository.findById(userID).get() ;
        if(!user.getPassword().equals(pwd)) {
            System.out.println(pwd + " " + user.getPassword());
            return -1;
        }
        user.setHasLoginQualification(false);
        userRepository.save(user) ;
        return 0 ;
    }

}
