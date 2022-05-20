import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/dialog/alert_dialog_page.dart';
import 'package:flutter_ui_widget/dialog/date_picker_page.dart';
import 'package:flutter_ui_widget/dialog/time_picker_page.dart';

class DialogMenuPage extends StatelessWidget {
  const DialogMenuPage({Key? key, required this.title}) : super(key: key);

  final String title;
  static const titles = [
    'AlertDialog',
    'DatePicker',
    'TimePicker'
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
                MaterialPageRoute(builder: (context) => AlertDialogPage(title: titles[0])),
              );
            },
          ),
          ListTile(
            title: Text(titles[1]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => DatePickerPage(title: titles[1])),
              );
            },
          ),
          ListTile(
            title: Text(titles[2]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => TimePickerPage(title: titles[2])),
              );
            },
          ),
        ],
      ),
    );
  }
}
