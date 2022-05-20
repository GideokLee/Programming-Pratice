import 'package:flutter/material.dart';

class SizedBoxPage extends StatelessWidget {
  const SizedBoxPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: SizedBox(
        width: 100,
        height: 100,
        child: Container(
          color: Colors.lightGreenAccent,
        ),
      ),
    );
  }
}
