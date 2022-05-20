import 'package:flutter/material.dart';

class GesturePage extends StatelessWidget {
  const GesturePage({Key? key, required this.title}) : super(key: key);

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
            GestureDetector(
              onTap: () {
                print('GestureDetector 클릭!!');
              },
              child: const Text('클릭 Me!!'),
            ),
            const SizedBox(
              height: 40,
            ),
            InkWell(
              onTap: () {
                print('InkWell 클릭!!');
              },
              child: const Text('클릭 Me!!'),
            ),
          ],
        ),
      ),
    );
  }
}
