import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/input/check_box_switch_page.dart';
import 'package:flutter_ui_widget/input/dropdown_page.dart';
import 'package:flutter_ui_widget/input/radio_page.dart';
import 'package:flutter_ui_widget/input/text_field_page.dart';

class InputMenuPage extends StatelessWidget {
  const InputMenuPage({Key? key, required this.title}) : super(key: key);

  final String title;
  static const titles = [
    'TextField',
    'CheckBox / Switch',
    'Radio / RadioListTile',
    'DropDownButton'
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
                MaterialPageRoute(builder: (context) => TextFieldPage(title: titles[0])),
              );
            },
          ),
          ListTile(
            title: Text(titles[1]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => CheckBoxSwitchPage(title: titles[1])),
              );
            },
          ),
          ListTile(
            title: Text(titles[2]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => RadioPage(title: titles[2])),
              );
            },
          ),
          ListTile(
            title: Text(titles[3]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => DropdownPage(title: titles[3])),
              );
            },
          ),
        ],
      ),
    );
  }
}
