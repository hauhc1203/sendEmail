package hauhc1203.sendmail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import hauhc1203.sendmail.service.SendMailService;

@RestController
@CrossOrigin("*")
@RequestMapping("/mail")
public class MailAPI {
    @Autowired
    SendMailService sendMailService;

    @GetMapping()
    public String send(@RequestParam("email") String email){

        return sendMailService.sendMail(email,
                "Xác nhận tài khoản","Mã xác nhận của bạn là: ");
    }

}
