import 'dart:convert';

// Json Decoding (JSON 문자열 -> Dart 객체로 변환)
void main() {
  var jsonString = '''
  [
  {"name":"홍길동"},
  {"name":"손홍민"}
  ]
  ''';

  var info = jsonDecode(jsonString);
  print(info);

  // List인지 체크
  print(info is List);

  // 첫 번째 Key:Value
  print(info[0]);
  print(info[0]['name']);
}

// Json Encoding (Dart 객체 -> JSON 문자열 변환)
// void main() {
//   var infoList = [
//     {"menu": "삼겹살"},
//     {"name": "탕수육"},
//     {"menu": "보쌈"},
//     {"menu": "짬뽕", "price": 10000}
//   ];
//
//   var infoText = jsonEncode(infoList);
//   print(infoText);
// }






