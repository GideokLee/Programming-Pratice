package com.ssafy.school;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.ssafy.school.region.Region;
import com.ssafy.school.region.Gumi;
import com.ssafy.school.region.Seoul;

public class RegionTest extends AbstractTest {

	/**
	 * 전체 지역을 등록합니다. 
	 * 3개의 지역이 등록되었으므로, 등록된 지역의 숫자는 3이 나와야 합니다.  
	 */
	@Test
	@Order(1)
	public void 지역_등록() {
		//given

		// 서울 생성
		omgr.add(new Seoul("서울", 30, "1"));

		// 구미 생성
		omgr.add(new Gumi("구미", 20, "구미1반", 22, 2));
		omgr.add(new Gumi("구미", 24, "구미6반", 25, 1));

		//when
		List<Region> orgs = omgr.search();

		//then
		assertEquals(3, orgs.size());
	}

	/**
	 * 전체 지역을 검색합니다. 
	 * 입력된 지역중에서 "서울"은 0번째 등록되어 있으며, 
	 * 좌석 수가 25개인 지역은 제일 마지막에 등록되어 있습니다. 
	 */
	@Test
	@Order(2)
	public void 전체_지역_검색() {
		//given

		// 서울 생성
		omgr.add(new Seoul("서울", 30, "1"));

		// 구미 생성
		omgr.add(new Gumi("구미", 20, "구미1반", 22, 2));
		omgr.add(new Gumi("구미", 24, "구미6반", 25, 1));

		//when
		List<Region> orgs = omgr.search();

		//then
		assertEquals("서울", orgs.get(0).getName());
		assertEquals(25, ((Gumi) orgs.get(2)).getRoomTotalCount());
	}

	/**
	 * 등록된 지역중에서 학생수가 20명인 지역을 모두 삭제합니다. 
	 * 학생수가 20명인 지역이 2개 등록되어 있으므로, 
	 * 삭제후 남은 지역은 1개고, "구미"로 검색하면 결과는 없어야 합니다. 
	 */
	@Test
	@Order(3)
	public void 학생수_20명_모두_삭제() {
		//given

		// 서울 생성
		omgr.add(new Seoul("서울", 30, "1"));

		// 구미 생성
		omgr.add(new Gumi("구미", 20, "구미1반", 22, 2));
		omgr.add(new Gumi("구미", 20, "구미6반", 25, 1));

		//when
		omgr.deleteByStudentCount(10);

		//then
		List<Region> orgs = omgr.search();
		assertEquals(3, orgs.size());

		Region o = omgr.search("구미");
		assertNull(o);
	}

	/**
	 * 지역의 이름으로 검색합니다. 
	 */
	@Test
	@Order(4)
	public void 서울_조회__지역_이름이_같다면_조회() {
		//given

		// 서울 생성
		omgr.add(new Seoul("서울", 30, "1"));

		// 구미 생성
		omgr.add(new Gumi("구미", 20, "구미1반", 22, 2));
		omgr.add(new Gumi("구미", 24, "구미6반", 25, 1));

		//when
		Region org = omgr.search(new Seoul("서울", 30, "1"));

		//then
		assertEquals("서울", org.getName());
	}

	/***********************************************************/	

	/**
	 * !! 이 메소드는 수정하지마시오. !!
	 * 각각의 테스트케이스를 별도로 수행하기 위하여 초기화
	 */
	@BeforeEach
	public void clear() {
		omgr.clear();
	}
}
