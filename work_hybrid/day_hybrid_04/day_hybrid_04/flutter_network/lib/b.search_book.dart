import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() => runApp(const SearchBook());

class SearchBook extends StatelessWidget {
  const SearchBook({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: HttpBookApp(),
    );
  }
}

class HttpBookApp extends StatefulWidget {
  const HttpBookApp({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _HttpApp();
}

class _HttpApp extends State<HttpBookApp> {
  String result = '';
  late TextEditingController _editingController;
  late ScrollController _scrollController;

  late List data;
  int page = 1;

  @override
  void initState() {
    super.initState();
    data = [];
    _editingController = TextEditingController();
    _scrollController = ScrollController();
    _scrollController.addListener(() {
      // offset: 현재 스크롤 위치
      // position.maxScrollExtent: 스크롤 할 수 있는 최대 픽셀
      // position.outOfRange: 스크롤 경계면
      if (_scrollController.offset >=
          _scrollController.position.maxScrollExtent &&
          !_scrollController.position.outOfRange) {
        print('bottom');
        page++;
        getJSONData();
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    
    // 데이터가 존재하지 않을 경우 표시할 위젯
    var _textDataNotFound =
    const Text(
        '데이터가 존재하지 않습니다.\n다시 검색해주세요',
        style: TextStyle(fontSize: 20),
        textAlign: TextAlign.center
    );

    // 데이터가 존재할 경우 표시할 위젯
    var _lvData = ListView.builder(
      controller: _scrollController,
      itemCount: data.length,
      itemBuilder: (context, index) {
        return Card(
          child: Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: <Widget>[
              Image.network(
                data[index]['thumbnail'],
                height: 100,
                width: 100,
                fit: BoxFit.contain,
                errorBuilder: (context, exception, trace) => const Icon(Icons.error),
              ),
              Column(
                children: <Widget>[
                  SizedBox(
                    width: MediaQuery.of(context).size.width - 150,
                    child: Text(
                      data[index]['title'].toString(),
                      textAlign: TextAlign.center,
                    ),
                  ),
                  SizedBox(
                    width: MediaQuery.of(context).size.width-150,
                    child: Text(
                      '저자 : ${data[index]['authors'].toString()}',
                      textAlign: TextAlign.center,
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                  Text('가격 : ${data[index]['sale_price'].toString()}'),
                  Text('판매중 : ${data[index]['status'].toString()}'),
                ],
              )
            ],
          ),
        );
      },
    );

    // 화면 그리기
    return Scaffold(
      appBar: AppBar(
        title: TextField(
          controller: _editingController,
          style: const TextStyle(color: Colors.white),
          keyboardType: TextInputType.text,
          decoration: const InputDecoration(hintText: '검색어를 입력하세요'),
        ),
      ),
      body: Center(child: data.isEmpty ? _textDataNotFound : _lvData),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          page = 1;
          data.clear();
          getJSONData();
        },
        child: const Icon(Icons.search),
      ),
    );
  }

  Future<String> getJSONData() async {
    var url = Uri.encodeFull('https://dapi.kakao.com/v3/search/book?target=title&page=$page'
        '&query=${_editingController.value.text}');

    var response = await http.get(
        Uri.parse(url),
        headers: {"Authorization": "KakaoAK a5cfbf43fe7878bb7e6f1cb43f4571c5"}
    );
    print(response.body); // 검색 결과 로그창으로 확인

    setState(() {
      var dataConvertedToJSON = json.decode(response.body);
      List result = dataConvertedToJSON['documents'];
      data.addAll(result);
    });
    return response.body;
  }
}
