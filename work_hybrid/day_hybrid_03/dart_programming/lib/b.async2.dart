Future<void> fetchUserOrder() {
  // 이 함수는 다른 서비스나 데이터베이스에서 사용자 정보를 가져오고 있다고 가정한 함수. (2초 소요)
  return Future.delayed(const Duration(seconds: 2), () => print('Large Latte'));
}

void main() {
  fetchUserOrder();
  print('사용자 주문 정보 가져오는 중 ...');
}

// 에러 발생
// Future<void> fetchUserOrder() {
//   // 이 함수는 사용자 정보를 가져오는 중에 버그가 발생했다고 가정한 함수.
//   return Future.delayed(const Duration(seconds: 2),
//           () => throw Exception('로그아웃 실패: 유효하지 않은 사용자 ID'));
// }
//
// void main() {
//   fetchUserOrder();
//   print('사용자 주문 정보 가져오는 중 ...');
// }


