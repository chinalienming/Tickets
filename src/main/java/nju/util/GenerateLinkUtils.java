package nju.util;


import nju.entity.User;

import javax.servlet.ServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by lienming on 2018/3/10.
 * 未使用
 */
public class GenerateLinkUtils {
    private static final String CHECK_CODE = "checkCode";

    public static String generateActivateLink(User user) {

        //NOTE :  Server address has not been modified

        return "http://localhost/EmailDemo/ActivateServlet?id="
                + user.getUserID() + "&" + CHECK_CODE + "=" + generateCheckcode(user);
    }


    /**
     * 生成校验码，用户名+UUID唯一标识符，为安全把他们加密发送
     * @param user
     * @return
     */
    public static String generateCheckcode(User user) {
        String email = user.getEmail();
        String randomCode = UUID.randomUUID().toString() ;
        return md5(email + ":" + randomCode);
    }


    /**
     * 接收回来的校验码和发送出去的是不是同一份
     * @param user
     * @param request
     * @return
     */
    public static boolean verifyCheckcode(User user,ServletRequest request) {

        String checkCode = request.getParameter(CHECK_CODE);

        String correct_checkCode = generateCheckcode(user) ;

        boolean success = checkCode.equals(correct_checkCode) ;
        System.out.println("Account verify "+ success);

        return success ;
    }

    private static String md5(String string) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
            md.update(string.getBytes());
            byte[] md5Bytes = md.digest();
            return bytes2Hex(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Error from md5...");
        }

        return null;
    }

    private static String bytes2Hex(byte[] byteArray)
    {
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++)
        {
            if(byteArray[i] >= 0 && byteArray[i] < 16)
            {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
        }
        return strBuf.toString();
    }
}
