import 'package:flutter/material.dart';

class AlertDialogPage extends StatefulWidget {
  const AlertDialogPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _AlertDialogPageState createState() => _AlertDialogPageState();
}

class _AlertDialogPageState extends State<AlertDialogPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: ElevatedButton(
        onPressed: () {
          _neverSatisfied();
        },
        child: const Text('Alert Dialog'),
      ),
    );
  }

  Future<void> _neverSatisfied() async {
    return showDialog<void>(
      context: context,
      barrierDismissible: false,  // 버튼을 탭 해야 사라짐
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text('제목'),
          content: SingleChildScrollView(
            child: ListBody(
              children: const <Widget>[
                Text('Alert Dialog 입니다'),
                Text('OK를 눌러 닫습니다'),
              ],
            ),
          ),
          actions: <Widget>[
            TextButton(
              child: const Text('OK'),
              onPressed: () {
                // Todo
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: const Text('Cancel'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }
}
