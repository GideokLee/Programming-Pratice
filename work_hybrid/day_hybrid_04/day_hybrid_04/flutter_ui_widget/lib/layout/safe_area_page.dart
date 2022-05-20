import 'package:flutter/material.dart';

// SafeArea: 기기별로 화면의 padding을 만들어 주는 위젯
class SafeAreaPage extends StatelessWidget {
  const SafeAreaPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: SafeArea(
        child: Container(
          color: Colors.red,
        ),
      ),
    );
  }
}
