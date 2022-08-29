package hauhc1203.sendmail.service;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class SendMailService {
    @Autowired
    JavaMailSender mailSender;
//    String superCode="asj1hxnqj11$^&**()(*&^%$@!#$%^&*((*&^((*&^yhHJHVUHJK2";
//    String code=superCode;
     String [] upper={ "A", "B", "C", "D", "E", "F", "G", "H", "I" ,"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
     String [] lower={ "a", "b", "c", "d", "e", "f", "g", "h" ,"i","j" ,"k" , "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
     int [] number={0,1,2,3,4,5,6,7,8,9};

     int size1=upper.length-1;
     int size2=number.length-1;
    public  int makeRandom(int min, int max){
        return (int) ((Math.random())*((max-min)+1)+min);
    }


    public  String createCode(){
        String newCode="";
        for (int i=0;i<8;i++){
            int from=makeRandom(1,3);
            switch (from){
                case 1:
                    newCode+=upper[makeRandom(0,size1)];
                    break;
                case 2:
                    newCode+=lower[makeRandom(0,size1)];
                    break;
                case 3:
                    newCode+=number[makeRandom(0,size2)];
                    break;
            }
        }

        return newCode;
    }


    public String sendMail(String toMail,String subject,String content){
        String code=createCode();

//        SimpleMailMessage message =new SimpleMailMessage();
        String toAddress = toMail;
        String fromAddress = "hch.123.shop@gmail.com";
        String senderName = "Your company name";
         subject = "Please verify your registration";
         content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                 +"<button > Xác Nhận</button>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("[[name]]", "user.getFullName()");
//            String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

            content = content.replace("[[URL]]", code);

            helper.setText(content, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


//
//        mailSender.send(message);
//        message.setFrom("hch.123.shop@gmail.com");
//        message.setTo(toMail);
//        message.setSubject(subject);
//        message.setText(content+code);

        try {
            mailSender.send(message);
            return code;
        }catch (MailException mailException){

            return "";
        }

    }



}
