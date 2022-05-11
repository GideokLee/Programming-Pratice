import 'package:flutter/material.dart';

class IconButtonPage extends StatelessWidget {
  const IconButtonPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: Center(
        child: IconButton(
          icon: Icon(Icons.add_a_photo),
          color: Colors.orange, // 아이콘 색상
          iconSize: 100.0, // 기본값 24.0
          onPressed: () {},
        ),
      ),
    );
  }
}
