package com.ssafy.ssafygo.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafygo.model.dto.Receipt;

@Repository
public class ReceiptDao {
	int _id=1;
	List<Receipt> receiptList = new ArrayList<>();

	// 모든 영수증
    public List<Receipt> findAllReceipt() {
    	return receiptList;
    }
    
	// 마지막 영수증
    public Receipt findLastReceipt() {
    	return receiptList.isEmpty() ? null : receiptList.get(receiptList.size()-1);
    }

    // 영수증 추가
    public int registerReceipt(Receipt receipt) {
    	receipt.setId(_id++);
    	receiptList.add(receipt);
    	return 0;
    }
}
