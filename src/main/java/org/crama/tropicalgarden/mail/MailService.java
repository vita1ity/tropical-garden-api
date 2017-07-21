package org.crama.tropicalgarden.mail;

import java.util.Locale;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.crama.tropicalgarden.surfing.SurfingWebsite;
import org.crama.tropicalgarden.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	
	@Value("${email.support}")
	private String emailSupport;
	
	@Value("${app.localhost}")
	private String appLocalhost;
	
	@Value("${app.host}")
	private String appHost;
	
    @Autowired
    private MessageSource messages;
    
    @Autowired
    private JavaMailSender javaMailSender;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Async
	public void sendEmailVerificationLink(User user) {
		
		final String subject = messages.getMessage("email.regSuccessSubject" , null, Locale.ENGLISH);
		String text = messages.getMessage("email.regSuccessText", null, Locale.ENGLISH);
        
		text = text + appLocalhost + "/api/user/activate?token=" + user.getToken().getToken();
		
		try {
			sendEmail(emailSupport, user.getEmail(), subject, text);
		} catch (MailException | InterruptedException | MessagingException e) {
			logger.info("Error sending email: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	@Async
	public void sendResetPasswordLink(User user, String token) {
		
		final String subject = messages.getMessage("email.resetPasswordSubject" , null, Locale.ENGLISH);
		String text = messages.getMessage("email.resetPasswordText", null, Locale.ENGLISH);
        
		text = text + appLocalhost + "/api/user/reset-password-link?id=" + user.getId() + "&token=" + token;
		
		try {
			sendEmail(emailSupport, user.getEmail(), subject, text);
		} catch (MailException | InterruptedException | MessagingException e) {
			logger.info("Error ossured while sending email: " + e.getMessage());
			e.printStackTrace();
		}
		 
	}
	
	@Async
	public void sendWebsiteApproval(SurfingWebsite website) {
		
		final String subject = messages.getMessage("email.websiteApprovalSubject" , null, Locale.ENGLISH);
		String text = messages.getMessage("email.websiteApprovalText", 
				new Object[] {website.getId(), website.getSiteName(), website.getSiteDescription(), website.getSiteUrl()}, Locale.ENGLISH);
        
		try {
			sendEmail(emailSupport, emailSupport, subject, text);
		} catch (MailException | InterruptedException | MessagingException e) {
			logger.info("Error ossured while sending email: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Async
	public void sendWebsitePausedNotification(SurfingWebsite website) {

		final String subject = messages.getMessage("email.websitePausedSubject" , null, Locale.ENGLISH);
		String text = messages.getMessage("email.websitePausedText", 
				new Object[] {website.getId(), website.getSiteName(), website.getSiteDescription(), website.getSiteUrl()}, Locale.ENGLISH);
        
		try {
			sendEmail(emailSupport, website.getUser().getEmail(), subject, text);
		} catch (MailException | InterruptedException | MessagingException e) {
			logger.info("Error ossured while sending email: " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	private void sendEmail(String from, String to, String subject, String body) throws MailException, InterruptedException, AddressException, MessagingException {
		
        logger.info("Sending email...");
        logger.info("To: " + to);
	    logger.info("From: " + from);
	    logger.info("Subject: " + subject);
	    logger.info("Body: " + body);
	    
	    MimeMessage mail = javaMailSender.createMimeMessage();
		mail.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		mail.setFrom(from);
		mail.setSubject(subject);
		mail.setContent(body, "text/html");
		
		javaMailSender.send(mail);
		
		logger.info("Email Sent!");
	}

	

	
	

	
}
