package com.ssafy.school;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.ssafy.school.subject.Database;
import com.ssafy.school.subject.DuplicatedException;
import com.ssafy.school.subject.Web;
import com.ssafy.school.subject.NotFoundException;
import com.ssafy.school.subject.Subject;

public class SubjectTest extends AbstractTest {

	/**
	 * 과정을 등록합니다. 
	 * 등록이 되었다면, 조회한 후 비교하면 등록된 과정이 나올것입니다.  
	 */
	@Test
	@Order(1)
	public void 과정_등록() throws Exception {
		// given
		String subjectName1 = "Spring";
		String subjectName2 = "데이터베이스";

		vmgr.add(new Web(subjectName1, 2, "백엔드"));
		vmgr.add(new Database(subjectName2, 3, "MySQL"));

		// when
		Subject subject1 = vmgr.search(subjectName1);
		Subject subject2 = vmgr.search(subjectName2);

		// then
		assertEquals(subjectName1, subject1.getName());
		assertEquals(subjectName2, subject2.getName());
	}

	/**
	 * 같은이름으로 과정을 등록합니다. 
	 * 이름이 같으면 등록되지 않고, DuplicatedException 이 발생합니다. 
	 * 해당 Exception이 발생하고, 메시지가 동일한지 확인합니다. 
	 */
	@Test
	@Order(2)
	public void 같은이름으로_등록() {
		// given
		DuplicatedException exception = assertThrows(DuplicatedException.class, () -> {
			vmgr.add(new Web("Spring", 2, "백엔드"));
			vmgr.add(new Database("데이터베이스", 3, "MySQL"));
			vmgr.add(new Database("데이터베이스", 4, "Oracle"));
		});

		// when
		String message = exception.getMessage();

		// then
		assertEquals("데이터베이스: 등록된 과정입니다.", message);
	}

	/**
	 * 등록된 과정을 검색합니다. 
	 * 등록후 조회하여 2개가 등록되었는지와 
	 * 각각의 위치에 입력되었는지 확인합니다. 
	 */
	@Test
	@Order(3)
	public void 바이러스_전체검색() throws Exception {
		// given
		Subject s1 = new Web("Spring", 2, "백엔드");
		Subject s2 = new Database("데이터베이스", 3, "MySQL");
		
		vmgr.add(s1);
		vmgr.add(s2);

		// when
		Subject[] subject = vmgr.search();

		// then
		assertEquals(2, subject.length);
		assertEquals(s1, subject[0]);
		assertEquals(s2, subject[1]);
	}

	/**
	 * 등록되지 않은 과정을 검색하면, NotFoundException 이 발생합니다. 
	 * 해당 Exception이 발생하고, 메시지가 동일한지 확인합니다. 
	 */
	@Test
	@Order(4)
	public void 과정_조회() {

		// given
		NotFoundException exception = assertThrows(NotFoundException.class, () -> {
			vmgr.search("안드로이드");
		});

		// when
		String message = exception.getMessage();

		// then
		assertEquals("안드로이드: 미등록 과정입니다.", message);

	}
}
