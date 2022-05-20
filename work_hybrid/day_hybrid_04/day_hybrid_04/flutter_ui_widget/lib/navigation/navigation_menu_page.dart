import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/navigation/stateful_page.dart';
import 'package:flutter_ui_widget/navigation/stateless_page1.dart';
import 'package:flutter_ui_widget/navigation/stateless_page2.dart';

class NavigationMenuPage extends StatelessWidget {
  const NavigationMenuPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: ListView(
        children: <Widget>[
          ListTile(
            title: const Text('StatelessWidget의 네비게이션'),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const FirstPage1()),
              );
            },
          ),
          ListTile(
            title: const Text('StatelessWidget2의 네비게이션'),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const FirstPage2()),
              );
            },
          ),
          ListTile(
            title: const Text('StatefulWidget의 네비게이션'),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const FirstStatefulPage()),
              );
            },
          ),
          ListTile(
            title: const Text('routes: FirstPage'),
            onTap: () {
              Navigator.pushNamed(context, '/first');
            },
          ),
          ListTile(
            title: const Text('routes: SecondPage'),
            onTap: () {
              Navigator.pushNamed(context, '/second');
            },
          ),
        ],
      ),
    );
  }
}
