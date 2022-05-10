class Fly {
  void go() {
    print("Fly.go()");
  }
}

class Person {
  void go() {
    print("Person.go()");
  }
}

// Teacher는 상속 받은 기능 외에 Fly의 기능도 가지고 있다.
class Teacher extends Person with Fly {
  // do Something ~

  void go() {
    print("Teacher.go()");
  }
}

void main() {
  Fly f = new Teacher();
  f.go();  // 실행 결과는? (힌트: 동적 바인딩)
}