import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() => runApp(const SimpleHttp());

class SimpleHttp extends StatelessWidget {
  const SimpleHttp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: HttpApp(),
    );
  }
}

class HttpApp extends StatefulWidget {
  const HttpApp({Key? key}) : super(key: key);

  @override
  State<StatefulWidget> createState() => _HttpApp();
}

// 1. SingleChildScrollView로 감싸서 scroll 만들기
// 2. UTF-8 대응 한글 깨지지 않게 만들기
// 3. WebView 열리게 만들기
class _HttpApp extends State<HttpApp> {
  String result = '';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Simple Http Test'),
      ),
      body: SingleChildScrollView(child: Text(result)),
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          Uri uri = Uri.parse("https://www.ssafy.com/");
          var response = await http.get(uri);
          setState(() {
            result = utf8.decode(response.bodyBytes);
          });
        },
        child: const Icon(Icons.download),
      ),
    );
  }
}
