import 'package:flutter/material.dart';

class Person {
  String name;
  int age;

  Person(this.name, this.age);
}

class FirstPage1 extends StatelessWidget {
  const FirstPage1({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    print('FirstPage build()');
    return Scaffold(
      appBar: AppBar(
        title: const Text('FirstPage1')
      ),
      body: ElevatedButton(
        child: const Text('SecondPage1 페이지로 이동'),
        onPressed: () async {
          final result = await Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const SecondPage1()),
          );

          print(result);
        },
      ),
    );
  }
}

class SecondPage1 extends StatelessWidget {
  const SecondPage1({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    print('SecondPage build()');
    return Scaffold(
      appBar: AppBar(
        title: const Text("SecondPage1"),
      ),
      body: ElevatedButton(
        child: const Text('처음 페이지로 이동'),
        onPressed: () {
          Navigator.pop(context, 'ok');
        },
      ),
    );
  }
}

