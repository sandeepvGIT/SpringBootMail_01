package com.svi.san.service;

public interface IPurchaseItems {
	public String purchaseItems(String subject,String[] items,double[] prices,String[] toEmails)throws Exception;

}
