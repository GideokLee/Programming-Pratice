void ifTest() {
  int num1 = 3;

  if (num1 % 2 == 0) {
    print('짝수');
  }
  else {
    print('홀수');
  }
}

void switchTest() {
  String choose = "가위";

  switch (choose) {
    case "가위":
      print("가위를 내셨습니다.");
      break;

    case "바위":
      print("바위를 내셨습니다.");
      break;

    case "보":
      print("보를 내셨습니다.");
      break;

    default:
      print("잘못 입력하셨습니다.");
  }
}

void forTest() {
  var model = ['TV', '냉장고','에어콘'];
  print(model.runtimeType);  // model은 배열이 아닌 컬렉션

  for (int i = 0; i < model.length; i++) {
    print(model[i]);
  }
}

void anonymousFunctionTest() {
  var result = (num3) {
    return num3 % 2 == 0;
  } (10);

  // bool noname(num3) {
  //   return num3 % 2 == 0;
  // }
  // var result = noname(10);

  print(result);

  var test2 = (int i) => i * i;
  print(test2(20));
}

void showInfo(String name, int age) {
  print('name: $name, age: $age');
}

void showInfo1(String name, {int? age}) {
  print('name: ${name}, age: ${age}');
}

void main2() {
  showInfo1('김열심');  // 정상 실행
  //showInfo1(age: 10);  // error
}

void main() {
  ifTest();
  //switchTest();
  //forTest();
  //anonymousFunctionTest();

  //showInfo('김길동', 28);
  //showInfo(name: '나간다');
  //showInfo(age: 35);
  //showInfo();

  //main2();
}