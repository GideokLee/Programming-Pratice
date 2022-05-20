import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class CupertinoPage extends StatefulWidget {
  const CupertinoPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _CupertinoPageState createState() => _CupertinoPageState();
}

class _CupertinoPageState extends State<CupertinoPage> {
  var _isOn = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CupertinoNavigationBar(
        middle: Text(widget.title)
      ),
      body: Column(
        children: <Widget>[
          CupertinoSwitch(
            value: _isOn,
            onChanged: (bool value) {
              setState(() {
                _isOn = value;
              });
            },
          ),
          CupertinoButton(
            borderRadius: BorderRadius.circular(16.0),
            color: Colors.orange,
            child: const Text('쿠페티노 AlertDialog'),
            onPressed: () {
              _showCupertinoDialog();
            },
          ),
          CupertinoButton(
            child: const Text('쿠페티노 Picker'),
            onPressed: () {
              _showCupertinoPicker();
            },
          ),
        ],
      ),
    );
  }

  _showCupertinoDialog() {
    showDialog(
      context: context,
      builder: (context) => CupertinoAlertDialog(
        title: const Text('제목'),
        content: const Text('내용'),
        actions: <Widget>[
          const CupertinoDialogAction(
            child: Text('Cancel'),
          ),
          CupertinoDialogAction(
            child: const Text('Ok'),
            onPressed: () {
              Navigator.of(context).pop();
            },
          ),
        ],
      ),
    );
  }

  _showCupertinoPicker() async {
    final _items = List.generate(10, (i) => i);
    var result = _items[0];
    await showCupertinoModalPopup(
      context: context,
      builder: (context) => SizedBox(
        height: 200.0,
        child: CupertinoPicker(
          children: _items.map((e) => Text('No. $e')).toList(),
          itemExtent: 50.0,
          onSelectedItemChanged: (int value) {
            result = _items[value];
          },
        ),
      ),
    );
    print(result);
  }
}
