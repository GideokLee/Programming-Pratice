import 'package:flutter/material.dart';

class TextFieldPage extends StatelessWidget {
  const TextFieldPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Center(
          child: Column(
            children: const <Widget>[
              TextField(
                keyboardType: TextInputType.number,
              ),
              SizedBox(
                height: 40,
              ),
              TextField(
                decoration: InputDecoration(
                  labelText: '이름을 입력하세요',   // 힌트
                ),
              ),
              SizedBox(
                height: 40,
              ),
              TextField(
                decoration: InputDecoration(
                  border: OutlineInputBorder(),   // 외각선
                  labelText: '주소를 입력하세요',
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
