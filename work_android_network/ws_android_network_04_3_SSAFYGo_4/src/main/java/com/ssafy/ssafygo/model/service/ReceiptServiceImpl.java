package com.ssafy.ssafygo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafygo.model.dao.ReceiptDao;
import com.ssafy.ssafygo.model.dto.Receipt;

@Service
public class ReceiptServiceImpl implements ReceiptService {

	@Autowired
	private ReceiptDao receiptDao;

	@Override
	public List<Receipt> findAllReceipt() {
		try {
	    	return receiptDao.findAllReceipt();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Receipt findLastReceipt() {
		try {
	    	return receiptDao.findLastReceipt();
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public int registerReceipt(Receipt receipt) {
		try {
			receiptDao.registerReceipt(receipt);
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
