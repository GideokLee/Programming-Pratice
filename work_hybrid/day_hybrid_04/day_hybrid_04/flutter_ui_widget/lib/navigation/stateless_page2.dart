import 'package:flutter/material.dart';

class Person2 {
  String name;
  int age;

  Person2(this.name, this.age);
}

class FirstPage2 extends StatelessWidget {
  const FirstPage2({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    print('FirstPage2 build()');
    return Scaffold(
      appBar: AppBar(
        title: const Text('FirstPage2')
      ),
      body: ElevatedButton(
        child: const Text('SecondPage2 페이지로 이동'),
        onPressed: () async {
          final person = Person2('홍길동', 20);
          final result = await Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => SecondPage2(person: person)),
          );
          print(result);
        },
      ),
    );
  }
}

class SecondPage2 extends StatelessWidget {
  const SecondPage2({Key? key, required this.person}) : super(key: key);

  final Person2 person;

  @override
  Widget build(BuildContext context) {
    print('SecondPage2 build()');
    return Scaffold(
      appBar: AppBar(
        title: Text(person.name),
      ),
      body: ElevatedButton(
        child: const Text('처음 페이지로 이동'),
        onPressed: () {
          Navigator.pop(context, 'Save OK...');
        },
      ),
    );
  }
}

