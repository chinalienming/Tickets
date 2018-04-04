package nju.service;

import nju.dao.TicketRecordRepository;
import nju.entity.SitePlan;
import nju.entity.TicketRecord;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TimeOutTask {

    private final static int timeOut = 120 ;  //seconds

    @Autowired
    private TicketService ticketService ;
    @Autowired
    private PlanService planService ;
    @Autowired
    private TicketRecordRepository ticketRecordRepository ;

    private List<TicketRecord> checkList = new ArrayList<>() ;

    private List<SitePlan> planList = new ArrayList<>() ;

    @Scheduled(cron = "0/15 * * * * ?")  //5s
//    @Scheduled(cron = "0 0/2 * * * ?")  2min
//    @Scheduled(cron = "0 0/15 * * * ?")
    public void aTask(){

        checkList = ticketService.getUnpayRecord() ;
//        System.out.println("unpay size:" +checkList.size()) ;

        long mills_now = System.currentTimeMillis() ;

        for(TicketRecord tr:checkList) {
            Timestamp createTime = tr.getCreateTime() ;
            long distance = mills_now - createTime.getTime();
            long seconds = distance/1000 ;
//            System.out.println(tr.getRecordID()+":"+seconds);
            if( seconds > timeOut ) {
                ticketService.setTimeOut(tr.getRecordID()) ;
            } else {
                continue;
            }
        }

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(DateTime.now().toDate()));
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void matchTask() {
        planList = planService.getAllSitePlan() ;
        for(SitePlan sitePlan:planList) {
            Date date = sitePlan.getBeginTime() ;
            long mills_now = System.currentTimeMillis();
            long distance = mills_now - date.getTime();
            long days = distance / (1000*60*60*24);
            if(days<14) {
                int planId = sitePlan.getPlanID() ;
                List<TicketRecord> l =
                        ticketRecordRepository.findByPlanIDAndSeatType(planId,"D");
                for(TicketRecord tr : l ) {
                    tr.setSeatType(tr.getSeatNumber().charAt(0)+"");
                    ticketRecordRepository.save(tr) ;
                }
            }
        }
    }


}
