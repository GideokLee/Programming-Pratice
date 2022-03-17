package com.ssafy.school;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.ssafy.school.person.Professor;
import com.ssafy.school.person.Student;
import com.ssafy.school.person.Person;

public class PersonTest extends AbstractTest {


	/**
	 * 교수와 학생을 등록합니다. 
	 * 3명이 등록되었으므로, 조회결과는 3이 나와야 합니다. 
	 */

	@Test
	@Order(1)
	public void 교수_학생_등록() {
		//given
		pmgr.add(new Professor("정교수", 37, "010-1010-0010", "구미6반", "객체지향"));
		pmgr.add(new Student("김싸피", 26, "010-0505-0005", "부산 사상구", "구미", true));
		pmgr.add(new Student("박싸피", 28, "010-0303-0003", "서울 강남구", "서울", true));

		//when
		List<Person> people = pmgr.search();

		//then
		assertEquals(3, people.size());
	}

	/**
	 * 전체 인원을 검색합니다. 
	 * 입력된 인원중에서 "정교수"는 0번째 등록되어 있으며, 
	 * 나이가 28인 학생은 제일 마지막에 등록되어 있습니다. 
	 */
	@Test
	@Order(2)
	public void 전체_인원_검색() {
		//given
		pmgr.add(new Professor("정교수", 37, "010-1010-0010", "구미6반", "객체지향"));
		pmgr.add(new Student("김싸피", 26, "010-0505-0005", "부산 사상구", "구미", true));
		pmgr.add(new Student("박싸피", 28, "010-0303-0003", "서울 강남구", "서울", true));

		//when
		List<Person> people = pmgr.search();

		//then
		assertEquals("정교수", people.get(0).getName());
		assertEquals(28, people.get(2).getAge());
	}

	/**
	 * 입력된 교수, 학생들을 이름을 기준으로 내림차순 정렬합니다.
	 * 정상적으로 정렬되었다면,
	 * "정교수"가 제일 먼저 출력되어야 합니다. 
	 * 이 기능은 기존에 입력되었던 순서를 변경하는 것이 아닙니다. 
	 */
	@Test
	@Order(3)
	public void 교수_학생_이름_가나다_역순으로_내림차순_정렬() {
		//given
		pmgr.add(new Student("김싸피", 26, "010-0505-0005", "부산 사상구", "구미", true));
		pmgr.add(new Student("박싸피", 28, "010-0303-0003", "서울 강남구", "서울", true));
		pmgr.add(new Professor("정교수", 37, "010-1010-0010", "구미6반", "객체지향"));

		//when
		List<Person> orgs = pmgr.orderByName();

		//then
		assertEquals("정교수", orgs.get(0).getName());
	}

	/** 
	 * 새로운 파일에 저장합니다.
	 * 이 테스트케이스에서는 저장을 확인합니다.
	 */
	@Test
	@Order(4)
	public void 전체_인원정보_새로운_파일에_저장() throws IOException {
		String fileName = "resource/people_new.dat";

		//given
		pmgr.add(new Student("김싸피", 26, "010-0505-0005", "부산 사상구", "구미", true));
		pmgr.add(new Student("박싸피", 28, "010-0303-0003", "서울 강남구", "서울", true));
		pmgr.add(new Professor("정교수", 37, "010-1010-0010", "구미6반", "객체지향"));

		File file = new File(fileName);
		if(file.exists()) file.delete();

		pmgr.save(fileName);
	}

	/** 
	 * 저장된 파일에서 읽었을 때, 4건이 조회되어야 합니다.  
	 */
	@Test
	@Order(5)
	public void 전체_인원정보_새로저장된_파일에서_읽기() throws IOException {
		pmgr.load("resource/people_new.dat");

		List<Person> people = pmgr.search();

		assertEquals(3, people.size());
	}

	/***********************************************************/	

	/**
	 * !! 이 메소드는 수정하지마시오. !!
	 * 각각의 테스트케이스를 별도로 수행하기 위하여 초기화
	 */
	@BeforeEach
	public void clear() {
		pmgr.clear();
	}

}
