package com.ssafy.ssafygo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafygo.model.dto.Receipt;
import com.ssafy.ssafygo.model.service.ReceiptService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	
	@Autowired
	ReceiptService receiptService;

	@GetMapping("/all")
    @ApiOperation(value = "모든 영수증 정보 반환")
    public List<Receipt> findAllReceipt() {
        System.out.println("findAllReceipt");
        return receiptService.findAllReceipt();
    }
	
	@GetMapping("")
    @ApiOperation(value = "마지막 영수증 정보 반환")
    public Receipt findLastReceipt() {
        System.out.println("findLastReceipt");
        return receiptService.findLastReceipt();
    }

	@PostMapping("")
    @ApiOperation(value = "영수증 추가")
    public Boolean registerReceipt(@RequestBody Receipt receipt) {
		// 영수증 추가
		int res = receiptService.registerReceipt(receipt);
		System.out.println("registerReceipt");
		
		return res != -1 ? true : false;
    }
}
