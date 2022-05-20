import 'package:flutter/material.dart';

class SliverFillRemainingPage extends StatelessWidget {
  const SliverFillRemainingPage({Key? key, required this.title}) : super(key: key);

  final String title;

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
            ),
          ),
          const SliverFillRemaining(
            child: Center(
              child: Text('center'),
            ),
          ),
        ],
      ),
    );
  }
}
