package com.ssafy.ssafygo.model.service;

import java.util.List;

import com.ssafy.ssafygo.model.dto.Receipt;

public interface ReceiptService {
	// 모든 영수증
    public List<Receipt> findAllReceipt();
    
	// 마지막 영수증
    public Receipt findLastReceipt();

    // 영수증 추가
    public int registerReceipt(Receipt receipt);
}
