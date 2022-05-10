import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      debugShowCheckedModeBanner: true,
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _btnText = "hello";
  var _switchValue = false;

  void _pressButton() {
    setState(() {
      if (_btnText == "hello") {
        _btnText = "flutter";
      }
      else {
        _btnText = "hello";
      }
    });
  }

  void _changeSwitch(value) {
    setState(() {
      print("value : $value");
      _switchValue = value;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
        child: ElevatedButton(onPressed: _pressButton, child: Text(_btnText))
        //child: Switch(onChanged: _changeSwitch, value: _switchValue)
      )
    );
  }
}
