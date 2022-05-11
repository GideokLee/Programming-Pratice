import 'package:flutter/material.dart';

class BottomNavigationBarPage extends StatefulWidget {
  const BottomNavigationBarPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _BottomNavigationBarPage createState() => _BottomNavigationBarPage();
}

class _BottomNavigationBarPage extends State<BottomNavigationBarPage> {

  int _selectedIndex = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title)
      ),
      bottomNavigationBar: BottomNavigationBar(
          currentIndex: _selectedIndex, //현재 선택된 Index
          onTap: (int index) {
            setState(() {
              _selectedIndex = index;
            });
          },
          items: [
        BottomNavigationBarItem(
          icon: Icon(Icons.home),
          label: '홈',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.person),
          label: '개인정보',
        ),
        BottomNavigationBarItem(
          icon: Icon(Icons.notifications),
          label: '알림',
        ),
      ]),
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex),
      ),
    );
  }
}

List _widgetOptions = [
  Text(
    '홈',
    style: TextStyle(fontSize: 30),
  ),
  Text(
    '개인정보',
    style: TextStyle(fontSize: 30),
  ),
  // FirstPage(),
  Text(
    '알림',
    style: TextStyle(fontSize: 30),
  ),
];
