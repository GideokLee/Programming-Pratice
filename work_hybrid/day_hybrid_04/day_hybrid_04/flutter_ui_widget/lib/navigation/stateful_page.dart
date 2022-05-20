import 'package:flutter/material.dart';

class FirstStatefulPage extends StatefulWidget {
  const FirstStatefulPage({Key? key}) : super(key: key);

  @override
  _FirstStatefulPageState createState() => _FirstStatefulPageState();
}

class _FirstStatefulPageState extends State<FirstStatefulPage> {

  @override
  void initState() {
    super.initState();
    print('FirstPage initState()');
  }

  @override
  void dispose() {
    super.dispose();
    print('FirstPage dispose()');
  }

  @override
  Widget build(BuildContext context) {
    print('FirstPage build()');
    return Scaffold(
      appBar: AppBar(
        title: const Text('FirstStatefulPage')
      ),
      body: ElevatedButton(
        child: const Text('다음 페이지로'),
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const SecondStatefulPage()),
          );
        },
      ),
    );
  }
}

class SecondStatefulPage extends StatefulWidget {
  const SecondStatefulPage({Key? key}) : super(key: key);

  @override
  _SecondStatefulPageState createState() => _SecondStatefulPageState();
}

class _SecondStatefulPageState extends State<SecondStatefulPage> {

  @override
  void initState() {
    super.initState();
    print('SecondPage initState()');
  }

  @override
  void dispose() {
    super.dispose();
    print('SecondPage dispose()');
  }

  @override
  Widget build(BuildContext context) {
    print('SecondPage build()');
    return Scaffold(
      appBar: AppBar(
        title: const Text('SecondStatefulPage'),
      ),
      body: ElevatedButton(
        child: const Text('이전 페이지로'),
        onPressed: () {
          Navigator.pop(context);
        },
      ),
    );
  }
}

