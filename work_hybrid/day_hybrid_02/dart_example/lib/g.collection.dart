void main() {
  // List =====================================
  var player = ['손홍민', '메시', '호날두'];

  print('넘버원 player: ${player[0]}');

  for (int i = 0; i < player.length; i++) {
    print(player[i]);
  }

  // Map ======================================

  // Map 생성
  // var result = {
  //   '김싸피': 90,
  //   '이싸피': 55,
  //   '박싸피': 30
  // };
  //
  // // 값 조회
  // print(result['김싸피']);
  //
  // // 값 수정
  // result['김싸피'] = 98;
  //
  // // 수정 값 조회
  // print(result);

  // Set ======================================
  // var prod = {'TV', '에어콘', '냉장고', 'TV'};
  //
  // // 중복된 데이터를 허용하지 않음
  // print(prod);  // 결과값 : {TV, 에어콘, 냉장고}
  // prod.add('청소기');
  // print(prod.contains('TV'));
}

