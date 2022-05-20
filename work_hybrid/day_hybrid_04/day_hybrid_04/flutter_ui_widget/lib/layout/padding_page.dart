import 'package:flutter/material.dart';

class PaddingPage extends StatelessWidget {
  const PaddingPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: Padding(
        padding: const EdgeInsets.all(70.0),
        child: Container(
          color: Colors.orange,
        ),
      ),
    );
  }
}
