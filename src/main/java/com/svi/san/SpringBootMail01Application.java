package com.svi.san;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.svi.san.service.IPurchaseItems;

@SpringBootApplication
public class SpringBootMail01Application {

	public static void main(String[] args) {
		ApplicationContext ctx=SpringApplication.run(SpringBootMail01Application.class, args);
		//get bean
		IPurchaseItems purchase=ctx.getBean("service", IPurchaseItems.class);
		String subject="cash memo";
		String[] items=new String[] {"jeans","phone","shirts"};
		double[] prices=new double[] {1999,34563,1633};
		String[] toEmails=new String[] {"aarav39@gmail.com","sandeepverma666@yahoo.com"};
		try {
			String status=purchase.purchaseItems(subject, items, prices, toEmails);
			System.out.println(status);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
