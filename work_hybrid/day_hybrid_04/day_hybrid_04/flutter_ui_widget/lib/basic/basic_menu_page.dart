import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/basic/circle_avatar_page.dart';
import 'package:flutter_ui_widget/basic/form_page.dart';
import 'package:flutter_ui_widget/basic/icon_page.dart';
import 'package:flutter_ui_widget/basic/image_page.dart';
import 'package:flutter_ui_widget/basic/progress_page.dart';
import 'package:flutter_ui_widget/basic/text_page.dart';

class BasicMenuPage extends StatelessWidget {
  const BasicMenuPage({Key? key, required this.title}) : super(key: key);

  final String title;
  static const List<String> titles = [
    'Text',
    'Image',
    'Icon',
    'Progress',
    'CircleAvatar',
    'Form'
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
                MaterialPageRoute(builder: (context) => TextPage(title: titles[0])),
              );
            },
          ),
          ListTile(
            title: Text(titles[1]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => ImagePage(title: titles[1])),
              );
            },
          ),
          ListTile(
            title: Text(titles[2]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => IconPage(title: titles[2])),
              );
            },
          ),
          ListTile(
            title: Text(titles[3]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => ProgressPage(title: titles[3])),
              );
            },
          ),
          ListTile(
            title: Text(titles[4]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => CircleAvatarPage(title: titles[4])),
              );
            },
          ),
          ListTile(
            title: Text(titles[5]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => FormPage(title: titles[5])),
              );
            },
          ),
        ],
      ),
    );
  }
}