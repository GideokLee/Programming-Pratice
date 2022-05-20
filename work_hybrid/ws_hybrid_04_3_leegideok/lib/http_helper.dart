import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;
import 'package:ws_hybrid_04_3_jeongsanghoon/movie.dart';

class HttpHelper {
  final String _urlBase = 'https://api.themoviedb.org/3/movie';
  final String _urlUpcoming = '/upcoming?';
  final String _urlKey = 'api_key=[ 본인의 API키를 입력하세요. ]';
  final String _urlLanguage = '&language=ko-KR';

  Future<List<Movie>> getUpcoming() async {
    final String upcoming = _urlBase + _urlUpcoming + _urlKey + _urlLanguage;
    // http.get 함수를 이용하여 영화정보를 받고 전달된 JSON 타입의 결과를
    // 리스트 형태로 변환하여 리턴한다.
    // 코드 구현 --------------------------------


    return [];
  }
}
