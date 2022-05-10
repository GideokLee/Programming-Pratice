// 한줄 주석

/*
여러줄 주석
*/

/// 문서 주석

void test() {

  // 문자열
  String name;
  name = "SSAFY";
  name = 'SSAFY';

  int i1 = 10;
  double d1 = 10.0;

  // int와 double은 num 타입에 포함
  num n1 = 10;
  num n2 = 15.0;

  // 다트에선 자동 형변환 지원 안됨 (명시적으로 형변환 필요)
  int i2 = 10;
  //double d2 = i2;  // error

  // type casting 필요
  //double d3 = i2 as double;  // runtime error
  double d4 = i2.toDouble();

}

void test1() {

  int i1 = 10;
  double d1 = 10.0;

  // num 타입에는 int와 double 모두 대입할 수 있음
  // int, double의 부모는 num이기 때문
  num n1 = i1;
  num n2 = d1;

  // var를 통한 타입 추론
  var v1 = 15;  // int
  var v2 = true;  // bool
  //v1 = false;  // error

  // dynamic을 통한 타입 추론
  dynamic dy1 = 15;  // int
  dynamic dy2 = true;  // bool
  dy1 = false;

  // 상수: final
  final String finalString = "김싸피";
  //finalString = "이싸피";  // error
  final DateTime finalNow = new DateTime.now();  // runtime에 결정되는 값도 저장가능

  // 상수: const
  const String constString = "김싸피";
  //constString = "이싸피";  // error
  //const DateTime constNow = new DateTime.now();  // error
}

void test2() {

  var v1 = 20.5;
  int i1 = v1 as int;  // error: 부모-자식 관계가 아니므로 as 사용불가
  print(i1);

  dynamic e = 30.5;
  num n1 = e;  // int와 double은 num의 자식이므로 변환가능 (다형성)
  print(e);

}

void main() {
  test();
  //test1();
  //test2();
}