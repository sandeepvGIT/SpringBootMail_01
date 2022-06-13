package com.svi.san.service.impl;

import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.svi.san.service.IPurchaseItems;
@Service("service")
public class PurchaseItemsImpl implements IPurchaseItems {
	@Autowired
	private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String fromMail;

	@Override
	public String purchaseItems(String subject,String[] items, double[] prices, String[] toEmails)throws Exception {
		Double billAmt=0.0;
		for(Double price:prices) {
			billAmt=billAmt+price;
		}
		String msg=Arrays.toString(items)+" are purchased  having prices "+Arrays.toString(prices)+" Bill Amts "+billAmt;
		String status=sendMail(msg,subject,toEmails);
		return msg+"  "+status;
	}
	
	private String sendMail(String msg,String subject,String[] toEmails) throws MessagingException {
		MimeMessage message=sender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message, true);
		helper.setFrom(fromMail);
		helper.setCc(toEmails);
		helper.setSubject(subject);
		helper.setText(msg);
		helper.addAttachment("doctor", new ClassPathResource("doctor.jpg"));
		//send the mail
		sender.send(message);
		return "mail sent";
	}

}
