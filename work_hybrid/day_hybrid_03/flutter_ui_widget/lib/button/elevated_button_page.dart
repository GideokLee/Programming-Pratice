import 'package:flutter/material.dart';

class ElevatedButtonPage extends StatelessWidget {
  ElevatedButtonPage({Key? key, required this.title}) : super(key: key);

  final String title;
  var count = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: Center(
        child: ElevatedButton(
          child: Text('ElevatedButton'),
          onPressed: () {
            count++;
            print("증가된 값은 $count");
          },
        ),
      ),
    );
  }
}
