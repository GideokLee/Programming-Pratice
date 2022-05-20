import 'package:flutter/material.dart';

class CheckBoxSwitchPage extends StatefulWidget {
  const CheckBoxSwitchPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _CheckBoxSwitchPageState createState() => _CheckBoxSwitchPageState();
}

class _CheckBoxSwitchPageState extends State<CheckBoxSwitchPage> {
  var _isChecked = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title)
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Checkbox(
                value: _isChecked,
                onChanged: (value) {
                  setState(() {
                    _isChecked = value as bool;
                  });
                },
              ),
              const SizedBox(
                height: 40,
              ),
              Switch(
                value: _isChecked,
                onChanged: (value) {
                  setState(() {
                    _isChecked = value;
                  });
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
