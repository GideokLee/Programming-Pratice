import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/event/gesture_page.dart';
import 'package:flutter_ui_widget/main.dart';

class EventMenuPage extends StatelessWidget {
  const EventMenuPage({Key? key, required this.title}) : super(key: key);

  final String title;
  static const titles = [
    'GestureDetector / InkWell'
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: ListView(
        children: <Widget>[
          ListTile(
            title: Text(titles[0]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => GesturePage(title: titles[0])),
              );
            },
          ),
        ],
      ),
    );
  }
}
