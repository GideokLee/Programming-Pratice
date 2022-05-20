package com.ssafy.hw03.step3;

import java.util.ArrayList;

public class ProductTest {	
		public static void main(String[] args) {
			ProductMgrImpl productMgr = ProductMgrImpl.getInstance();
			ArrayList<Product> result;
			
			//상품정보(TV와 Refrigerator)를 저장
			productMgr.add(new TV("1001", "벽걸이형 TV", 100000, 10, 30.0, "UHD"));
			productMgr.add(new TV("1002", "스탠드형 TV", 200000, 20, 40.0, "QLED"));
			productMgr.add(new TV("1003", "스탠드형 TV", 300000, 30, 70.0, "QLED"));
			productMgr.add(new Refrigerator("2001", "4도어 냉장고", 400000, 40, 200));
			productMgr.add(new Refrigerator("2002", "2도어 냉장고", 500000, 50, 400));
			productMgr.add(new Refrigerator("test", "remove() test", 600000, 4, 800));
			
			//상품번호로 상품을 삭제하는 기능
			productMgr.remove("test");
			
			//상품정보 전체를 검색하는 기능
			System.out.println("\t\t\t\t[전체 상품 정보]");
			result = productMgr.getList();
			for(int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i).toString());
			}
			
			//TV정보만 검색
			System.out.println("\t\t\t\t[TV 상품 정보]");
			result = productMgr.getTVs();
			for(int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i).toString());
			}
			
			//Refrigerator만 검색
			System.out.println("\t\t\t\t[냉장고 상품 정보]");
			result = productMgr.getRefrigerators();
			for(int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i).toString());
			}
			
			//상품명으로 상품을 검색하는 기능(상품명 부분 검색 가능)
			System.out.println("\t\t\t\t[제품 명 포함 검색 : 4도어 ]");
			result = productMgr.searchByName("4도어");
			for(int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i).toString());
			}
			
			//상품번호로 상품을 검색하는 기능
			System.out.println("\t\t\t\t[제품 번호로 검색 : 1001]");
			System.out.println(productMgr.searchByIspn("1001").toString());
			
			//전체 재고 상품 금액을 구하는 기능
			System.out.println("\n전체 재고 상품 금액 : " + productMgr.getTotalPrice());
			
			
			//400L 이상의 Refrigerator 검색
			System.out.println("\t\t\t\t[400L 이상 냉장고 검색 ]");
			result = productMgr.searchByVolume(400);
			for(int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i).toString());
			}
			
			//50inch 이상의 TV검색
			System.out.println("\t\t\t\t[50inch 이상 TV 검색 ]");
			result = productMgr.searchByInch(50);
			for(int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i).toString());
			}
			
			//상품번호와 가격을 입력 받아 상품 가격을 변경할 수 있는 기능
			System.out.println("\t\t\t\t[가격 변경 1003 : 333333 ]");
			productMgr.setPrice("1003", 333333);
			System.out.println(productMgr.searchByIspn("1003").toString());
		}
}
