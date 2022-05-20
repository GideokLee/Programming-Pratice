package com.ssafy.hw.step3;

public class ProductTest {
	public static void main(String[] args) {
		
		ProductMgr productMgr = new ProductMgr();
		Product[] result;
		final int MAX_SIZE = 100;
		
		//add()
		productMgr.add(new TV("1001", "벽걸이형 TV", 100000, 1, 50.0, "UHD"));
		productMgr.add(new TV("1002", "스탠드형 TV", 200000, 2, 60.0, "QLED"));
		productMgr.add(new TV("1003", "스탠드형 TV", 300000, 3, 70.0, "QLED"));
		productMgr.add(new Refrigerator("2001", "4도어 냉장고", 400000, 4, 700));
		productMgr.add(new Refrigerator("2002", "2도어 냉장고", 500000, 5, 750));
		productMgr.add(new Refrigerator("test", "remove() test", 600000, 4, 800));
		
		//remove()
		productMgr.remove("test");
		
		//getList()
		System.out.println("\t\t\t\t[전체 상품 정보]");
		result = productMgr.getList();
		for(int i =0; i< MAX_SIZE; i++) {
			if(result[i] == null) break;
			System.out.println(result[i].toString());
		}
		
		//getTVs()
		System.out.println("\n\t\t\t\t[TV 상품 정보]");
		result = productMgr.getTVs();
		for(int i =0; i< MAX_SIZE; i++) {
			if(result[i] == null) break;
			System.out.println(result[i].toString());
		}
		
		//getRefrigerators()
		System.out.println("\n\t\t\t\t[냉장고 상품 정보]");
		result = productMgr.getRefrigerators();
		for(int i =0; i< MAX_SIZE; i++) {
			if(result[i] == null) break;
			System.out.println(result[i].toString());
		}
		
		//searchByName()
		System.out.println("\n\t\t\t\t[제품 명 포함 검색 : 4도어 ]");
		result = productMgr.searchByName("4도어");
		for(int i =0; i< MAX_SIZE; i++) {
			if(result[i] == null) break;
			System.out.println(result[i].toString());
		}
		
		//searchByisPn()
		System.out.println("\n\t\t\t\t[제품 번호로 검색 : 1001]");
		System.out.println(productMgr.searchByIspn("1001").toString());
		
		//getTotalPrice()
		System.out.println("\n전체 재고 상품 금액 : " + productMgr.getTotalPrice());

	}
}
