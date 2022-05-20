import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/button/button_page.dart';
import 'package:flutter_ui_widget/button/elevated_button_page.dart';
import 'package:flutter_ui_widget/button/floating_action_button_page.dart';
import 'package:flutter_ui_widget/button/icon_button_page.dart';
import 'package:flutter_ui_widget/button/text_button_page.dart';

class ButtonMenuPage extends StatelessWidget {
  const ButtonMenuPage({Key? key, required this.title}) : super(key: key);

  final String title;
  static const List<String> titles = [
    'ElevatedButton',
    'TextButton',
    'IconButton',
    'FloatingActionButton',
    'Buttons'
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: ListView(
        children: <Widget>[
          ListTile(
            title: Text(titles[0]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => ElevatedButtonPage(title: titles[0])),
              );
            },
          ),
          ListTile(
            title: Text(titles[1]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => TextButtonPage(title: titles[1])),
              );
            },
          ),
          ListTile(
            title: Text(titles[2]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => IconButtonPage(title: titles[2])),
              );
            },
          ),
          ListTile(
            title: Text(titles[3]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => FloatingActionButtonPage(title: titles[3])),
              );
            },
          ),
          ListTile(
            title: Text(titles[4]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => ButtonPage(title: titles[4])),
              );
            },
          ),
        ],
      ),
    );
  }
}
