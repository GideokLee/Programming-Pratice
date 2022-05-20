import 'package:flutter/material.dart';

class CenterPage extends StatelessWidget {
  const CenterPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: Center(
        child: Container(
          color: Colors.cyanAccent,
          width: 200,
          height: 200,
        ),
      ),
    );
  }
}
