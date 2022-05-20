import 'package:flutter/material.dart';

class PageViewPage extends StatelessWidget {
  const PageViewPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: PageView(
        children: <Widget>[
          Container(
            color: Colors.pink,
          ),
          Container(
            color: Colors.black,
          ),
          Container(
            color: Colors.cyan,
          ),
        ],
      ),
    );
  }
}
