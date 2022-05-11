import 'package:flutter/material.dart';

class IconPage extends StatelessWidget {
  const IconPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: Center(
        child: Icon(
          Icons.home,
          color: Colors.red,
          size: 60.0, // 기본값 24.0
        ),
      ),
    );
  }
}
