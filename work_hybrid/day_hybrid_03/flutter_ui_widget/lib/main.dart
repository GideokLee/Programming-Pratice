import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/basic/basic_menu_page.dart';
import 'package:flutter_ui_widget/button/button_menu.dart';
import 'package:flutter_ui_widget/layout/layout_menu_page.dart';
import 'package:flutter_ui_widget/multi/multi_menu_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MenuPage(title: 'UI Widget'),
    );
  }
}

class MenuPage extends StatelessWidget {
  const MenuPage({Key? key, required this.title}) : super(key: key);

  final String title;
  static const List<String> titles = [
    '1. 화면 배치를 위한 위젯',
    '2. 위치, 정렬, 크기를 위한 위젯',
    '3. 버튼 계열 위젯',
    '4. 화면 표시용 위젯'
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: ListView(
        children: [
          ListTile(
            title: Text(titles[0]),
            onTap: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => MultiMenuPage(title: titles[0]))
              );
            },
          ),
          ListTile(
            title: Text(titles[1]),
            onTap: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => LayoutMenuPage(title: titles[1]))
              );
            },
          ),
          ListTile(
            title: Text(titles[2]),
            onTap: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => ButtonMenuPage(title: titles[2]))
              );
            },
          ),
          ListTile(
            title: Text(titles[3]),
            onTap: () {
              Navigator.push(context,
                  MaterialPageRoute(builder: (context) => BasicMenuPage(title: titles[3]))
              );
            },
          ),
        ],
      ),
    );
  }
}
