import 'package:flutter/material.dart';

enum Gender { man, women }

class RadioPage extends StatefulWidget {
  const RadioPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _RadioPageState createState() => _RadioPageState();
}

class _RadioPageState extends State<RadioPage> {
  Gender _gender = Gender.man;

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
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              ListTile(
                title: const Text('남자'),
                leading: Radio(
                  value: Gender.man,
                  groupValue: _gender,
                  onChanged: (value) {
                    setState(() {
                      _gender = value as Gender;
                    });
                  },
                ),
              ),
              ListTile(
                title: const Text('여자'),
                leading: Radio(
                  value: Gender.women,
                  groupValue: _gender,
                  onChanged: (value) {
                    setState(() {
                      _gender = value as Gender;
                    });
                  },
                ),
              ),
              const SizedBox(
                height: 40,
              ),
              RadioListTile(
                title: const Text('남자'),
                value: Gender.man,
                groupValue: _gender,
                onChanged: (value) {
                  setState(() {
                    _gender = value as Gender;
                  });
                },
              ),
              RadioListTile(
                title: const Text('여자'),
                value: Gender.women,
                groupValue: _gender,
                onChanged: (value) {
                  setState(() {
                    _gender = value as Gender;
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
