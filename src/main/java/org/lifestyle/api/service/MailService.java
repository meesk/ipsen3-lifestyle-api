/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lifestyle.api.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.lifestyle.api.model.User;


public class MailService{

    public static boolean forgotPassword(User user, String newPassword)
    {
        try{

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true"); 
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {

                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("username@yahoo.com", "password");
                }
            });

            mailSession.setDebug(true); // Enable the debug mode

            Message msg = new MimeMessage( mailSession );

            //--[ Set the FROM, TO, DATE and SUBJECT fields
            msg.setFrom( new InternetAddress( "donotreply@lscn.com" ) );
            msg.setRecipients( Message.RecipientType.TO,InternetAddress.parse(user.getEmailAddress()) );
            msg.setSentDate( new Date());
            msg.setSubject( "Wachtwoord vergeten LSCN!");

            //--[ Create the body of the mail
            msg.setText( "Beste %s, \n\n Hierbij ontvangt u uw nieuwe wachtwoord voor LSCN: \n\n"
                    + newPassword + "\n\n U kunt hiermee inloggen op de LSCN website. \n\n" );

            //--[ Ask the Transport class to send our mail message
            Transport.send( msg );

        }catch(Exception E){
            System.out.println( "Er is iets misgegaan!");
            System.out.println( E );
            return false;
        }
        return true;
    }
}
