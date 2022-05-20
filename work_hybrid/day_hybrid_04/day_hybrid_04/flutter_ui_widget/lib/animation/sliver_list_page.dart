import 'package:flutter/material.dart';

class SliverListPage extends StatelessWidget {
  SliverListPage({Key? key, required this.title}) : super(key: key);

  final String title;

  final _items = List.generate(50, (i) => ListTile(title: Text('No. $i')));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CustomScrollView(
        slivers: <Widget>[
          SliverAppBar(
            pinned: true,
            expandedHeight: 180.0,
            flexibleSpace: FlexibleSpaceBar(
              title: Text(title),
              background: Image.asset(
                'assets/sample.jpg',
                fit: BoxFit.cover,
              ),
            )
          ),
          SliverList(
            delegate: SliverChildListDelegate(_items),
          ),
        ],
      ),
    );
  }
}
