package com.ssafy.school.person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonMgrImpl implements PersonMgr {
	private List<Person> people;

	private static PersonMgr instance;

	private PersonMgrImpl() {
		people = new ArrayList<Person>();
	}

	public static PersonMgr getInstance() {

		if (instance == null) {
			instance = new PersonMgrImpl();
		}

		return instance;
	}

	@Override
	public void add(Person p) {
		people.add(p);
	}

	@Override
	public void clear() {
		if (people != null)
			people.clear();
	}

	@Override
	public List<Person> search() {
		return people;
	}

	@Override
	public void load(String fileName) throws IOException {
		File f = new File(fileName);
		if (f.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
				ois.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("[SYSTEM] 파일 읽기 실패");
			}
		}
	}

	@Override
	public void save(String fileName) throws IOException {
		/* 기존에 저장된 파일 삭제하기 */
		// 여기에 작성해주세요.

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(people);
		oos.flush();
	}

	@Override
	public List<Person> orderByName() {

		List<Person> list = new ArrayList<>();
		list.addAll(people);

		Collections.sort(list);

		return list;
	}
}
