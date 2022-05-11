import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/layout/align_page.dart';
import 'package:flutter_ui_widget/layout/card_page.dart';
import 'package:flutter_ui_widget/layout/center_page.dart';
import 'package:flutter_ui_widget/layout/expanded_page.dart';
import 'package:flutter_ui_widget/layout/padding_page.dart';
import 'package:flutter_ui_widget/layout/safe_area_page.dart';
import 'package:flutter_ui_widget/layout/sized_box_page.dart';

class LayoutMenuPage extends StatelessWidget {
  const LayoutMenuPage({Key? key, required this.title}) : super(key: key);

  final String title;
  static const titles = [
    'Center',
    'Padding',
    'Align',
    'Expanded',
    'SizedBox',
    'Card',
    'SafeArea'
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
                MaterialPageRoute(builder: (context) => CenterPage(title: titles[0])),
              );
            },
          ),
          ListTile(
            title: Text(titles[1]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => PaddingPage(title: titles[1])),
              );
            },
          ),
          ListTile(
            title: Text(titles[2]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => AlignPage(title: titles[2])),
              );
            },
          ),
          ListTile(
            title: Text(titles[3]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => ExpandedPage(title: titles[3])),
              );
            },
          ),
          ListTile(
            title: Text(titles[4]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => SizedBoxPage(title: titles[4])),
              );
            },
          ),
          ListTile(
            title: Text(titles[5]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => CardPage(title: titles[5])),
              );
            },
          ),
          ListTile(
            title: Text(titles[6]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => SafeAreaPage(title: titles[6])),
              );
            },
          ),
        ],
      ),
    );
  }
}
