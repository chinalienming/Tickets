package nju.util;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
/**
 * Created by lienming on 2018/3/10.
 */
public class EmailUtility {

    public static final String SMTPSERVER = "smtp.qq.com";
    public static final String SMTPPORT = "465"; //邮箱服务器默认端口
    private static final String FROM = "728385437@qq.com";
    private static final String PWD = "okqbuktdkltmbahf" ;

    //Tips : Use this Method in Class Servlet.
    public static void sendAccountActivateEmail(String email,String user_id) {

        Properties props = new Properties();

        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", SMTPSERVER);
        props.setProperty("mail.smtp.port", SMTPPORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
        props.setProperty("mail.smtp.ssl.enable", "true");// 开启ssl

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }
        });


        // 开启debug模式，可以看到更多详细的输入日志
        session.setDebug(true);
        //创建邮件


        try {
            MimeMessage message = new MimeMessage(session);

            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM,"J2EE Lem","utf-8"));
            message.setRecipient(RecipientType.TO, new InternetAddress(email,"J2EE email authentication","utf-8"));
            message.setSubject("Mail From Li En Ming","utf-8");
            message.setContent(user_id, "text/html;charset=utf-8");
            message.setSentDate(new Date());
            message.saveChanges();

            Transport transport = session.getTransport();
            transport.connect(SMTPSERVER,FROM,PWD);
            Transport.send(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        EmailUtility.sendAccountActivateEmail("javalem@163.com","usb");
//    }

}
