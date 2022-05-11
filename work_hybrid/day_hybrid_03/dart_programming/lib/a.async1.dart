// async와 await를 이용한 비동기 코드 구현 ===========================
void main() {
  print('start main');
  checkData();
  print('end main');
}

Future checkData() async {
  var data = await getAllData();
  print(data);
}

Future<int> getAllData() async {
  return 1000;
}

