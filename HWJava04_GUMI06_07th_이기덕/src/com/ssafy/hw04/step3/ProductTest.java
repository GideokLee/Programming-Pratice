package com.ssafy.hw04.step3;

import java.io.File;
import java.util.ArrayList;

public class ProductTest {	
		public static void main(String[] args) {
			//1. 데이터 파일이 존재하지 않는 경우
			System.out.println("데이터 파일이 존재하지 않는 경우");
			System.out.println("\t\t\t\t[전체 상품 정보]");
			// test를 위해 파일이 존재하면 삭제
			File file = new File("product.dat");
			if(file.exists()) {
				file.delete();
			}
			//객체 생성시 loadData()
			ProductMgrImpl productMgr = ProductMgrImpl.getInstance();
			ArrayList<Product> result;
			
			//상품정보 전체를 검색하는 기능

			result = productMgr.getList();
			for(int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i).toString());
			}
			
			
			//2. 데이터 파일이 존재하는 경우
			System.out.println("\n데이터 파일이 존재하는 경우");
			//파일에 상품정보(TV와 Refrigerator)를 저장
			productMgr.add(new TV("1001", "벽걸이형 TV", 100000, 10, 30.0, "UHD"));
			productMgr.add(new TV("1002", "스탠드형 TV", 200000, 20, 40.0, "QLED"));
			productMgr.add(new TV("1003", "스탠드형 TV", 300000, 30, 70.0, "QLED"));
			productMgr.add(new Refrigerator("2001", "4도어 냉장고", 400000, 40, 200));
			productMgr.add(new Refrigerator("2002", "2도어 냉장고", 500000, 50, 400));
			productMgr.saveData();
			
			//reload
			productMgr.reLoadTest();
			
			//상품정보 전체를 검색하는 기능
			System.out.println("\t\t\t\t[전체 상품 정보]");
			result = productMgr.getList();
			for(int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i).toString());
			}
		}
}
