import 'package:flutter/material.dart';

class ProgressPage extends StatelessWidget {
  const ProgressPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            CircularProgressIndicator(
              backgroundColor: Colors.orange,
            ),
            SizedBox(
              height: 20,
            ),
            LinearProgressIndicator(
              color: Colors.purpleAccent,
            ),
          ],
        ),
      ),
    );
  }
}
