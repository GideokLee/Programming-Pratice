import 'dart:math';

import 'package:flutter/material.dart';

class AnimatedContainerPage extends StatefulWidget {
  const AnimatedContainerPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _AnimatedContainerPageState createState() => _AnimatedContainerPageState();
}

class _AnimatedContainerPageState extends State<AnimatedContainerPage> {
  var _size = 100.0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title)
      ),
      body: Center(
        child: GestureDetector(
          onTap: () {
            final random = Random();
            setState(() {
              _size = random.nextInt(200).toDouble() + 100;
              print(_size);
            });
          },
          child: AnimatedContainer(
            duration: const Duration(seconds: 1),
            // width: _size,
            height: _size,
            child: Image.asset('assets/sample.jpg'),
            curve: Curves.fastOutSlowIn,
          ),
        ),
      ),
    );
  }
}