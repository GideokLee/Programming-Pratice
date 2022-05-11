import 'package:flutter/material.dart';

class TextButtonPage extends StatelessWidget {
  TextButtonPage({Key? key, required this.title}) : super(key: key);

  final String title;
  var count = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(

      appBar: AppBar(
        title: Text(title)
      ),
      body: Center(
        child: TextButton(
          child: Text('TextButton Click'),
          onPressed: () {
            count++;
            print("증가된 값은 $count");
          },
        ),
      ),
    );
  }
}
