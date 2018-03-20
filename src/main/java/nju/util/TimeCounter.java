package nju.util;

/**
 * Created by lienming on 2018/3/10.
 */
public class TimeCounter {

    public boolean shouldTerminate = false ;

    public void count(int timeOff_seconds) throws InterruptedException{
        while(timeOff_seconds>0) {
            checkState();
            if(shouldTerminate) {
                throw new InterruptedException() ;
            }
            timeOff_seconds--;
            System.out.println(timeOff_seconds+" left");
            Thread.sleep(1000);
        }
    }

    private void checkState() {
        //query in database_stub
        boolean transferFinished = false ;

        if(transferFinished) {
            this.shouldTerminate = true;
        }
    }
}
