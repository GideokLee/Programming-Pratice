import 'package:flutter/material.dart';

class TabPage extends StatelessWidget {
  const TabPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 3,
      child: Scaffold(
        appBar: AppBar(
          title: Text(title),
          bottom: TabBar(
            tabs: <Widget>[
              Tab(icon: Icon(Icons.tag_faces)),
              Tab(text: '공지사항'),
              Tab(icon: Icon(Icons.wifi_rounded), text: '네트워크'),
            ],
          ),
        ),
        body: TabBarView(
            children: <Widget>[
              Container(color: Colors.yellow,),
              Container(color: Colors.orange,),
              Container(color: Colors.black26,),
            ]),
      ),
    );
  }
}
