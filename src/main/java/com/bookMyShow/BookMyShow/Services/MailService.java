package com.bookMyShow.BookMyShow.Services;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    public void generateMail(String to,String sub,String text) {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,"UTF-8");
        try {
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(sub);
            mimeMessageHelper.setText(text);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void generateMail(String to, String sub, String text, String path) {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(sub);
            mimeMessageHelper.setText(text);
            FileSystemResource fileSystemResource=new FileSystemResource(new File(path));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
