/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Craitson
 */
public class Email {

    public static void enviaEmail(String login) {

        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("trabalhosemauditoria@gmail.com", "32547698");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("trabalhosemauditoria@gmail.com")); //Remetente

            Address[] toUser = InternetAddress //Destinatário
                    .parse("craitson@gmail.com");

            message.setRecipients(Message.RecipientType.TO, toUser);
            //Assunto
            message.setSubject("Usuário tentando Logar");
            //Corpo
            message.setText("O usuário '" + login + "' tentou fazer login mais de 3 vezes sem sucesso e foi desativado.");
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
