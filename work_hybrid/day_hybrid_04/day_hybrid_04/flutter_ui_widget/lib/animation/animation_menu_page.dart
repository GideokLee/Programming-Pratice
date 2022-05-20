import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/animation/animated_container_page.dart';
import 'package:flutter_ui_widget/animation/hero_page.dart';
import 'package:flutter_ui_widget/animation/sliver_fill_remaining_page.dart';
import 'package:flutter_ui_widget/animation/sliver_list_page.dart';

class AnimationMenuPage extends StatelessWidget {
  const AnimationMenuPage({Key? key, required this.title}) : super(key: key);

  final String title;
  static const titles = [
    'Hero',
    'AnimatedContainer',
    'SliverAppBar / SliverFillRemaining',
    'SliverAppBar / SliverList'
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: ListView(
        children: <Widget>[
          ListTile(
            title: Text(titles[0]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => HeroPage(title: titles[0])),
              );
            },
          ),
          ListTile(
            title: Text(titles[1]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => AnimatedContainerPage(title: titles[1])),
              );
            },
          ),
          ListTile(
            title: Text(titles[2]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => SliverFillRemainingPage(title: titles[2])),
              );
            },
          ),
          ListTile(
            title: Text(titles[3]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => SliverListPage(title: titles[3])),
              );
            },
          ),
        ],
      ),
    );
  }
}
