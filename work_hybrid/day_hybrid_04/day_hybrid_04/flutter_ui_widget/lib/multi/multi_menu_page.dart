import 'package:flutter/material.dart';
import 'package:flutter_ui_widget/multi/bottom_navigation_bar_page.dart';
import 'package:flutter_ui_widget/multi/column_page.dart';
import 'package:flutter_ui_widget/multi/container_page.dart';
import 'package:flutter_ui_widget/multi/grid_view_page.dart';
import 'package:flutter_ui_widget/multi/list_view_page.dart';
import 'package:flutter_ui_widget/multi/page_view_page.dart';
import 'package:flutter_ui_widget/multi/row_page.dart';
import 'package:flutter_ui_widget/multi/single_child_scroll_view_page.dart';
import 'package:flutter_ui_widget/multi/stack_page.dart';
import 'package:flutter_ui_widget/multi/tab_page.dart';

class MultiMenuPage extends StatelessWidget {
  const MultiMenuPage({Key? key, required this.title}) : super(key: key);

  final String title;
  static const List<String> titles = [
    'Container',
    'Column',
    'Row',
    'Stack',
    'SingleChildScrollView',
    'ListView / ListTile',
    'GridView',
    'PageView',
    'AppBar / TabBar / Tab / TabBarView',
    'BottomNavigationBar'
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title)
      ),
      body: ListView(
        children: [
          ListTile(
            title: Text(titles[0]),
            onTap: () {
              Navigator.push(context,
                MaterialPageRoute(builder: (context) => ContainerPage(title: titles[0]))
              );
            }
          ),
          ListTile(
              title: Text(titles[1]),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => ColumnPage(title: titles[1]))
                );
              }
          ),
          ListTile(
              title: Text(titles[2]),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => RowPage(title: titles[2]))
                );
              }
          ),
          ListTile(
              title: Text(titles[3]),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => StackPage(title: titles[3]))
                );
              }
          ),
          ListTile(
              title: Text(titles[4]),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => SingleChildScrollViewPage(title: titles[4]))
                );
              }
          ),
          ListTile(
              title: Text(titles[5]),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => ListViewPage(title: titles[5]))
                );
              }
          ),
          ListTile(
              title: Text(titles[6]),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => GridViewPage(title: titles[6]))
                );
              }
          ),
          ListTile(
              title: Text(titles[7]),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => PageViewPage(title: titles[7]))
                );
              }
          ),
          ListTile(
              title: Text(titles[8]),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => TabPage(title: titles[8]))
                );
              }
          ),
          ListTile(
              title: Text(titles[9]),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => BottomNavigationBarPage(title: titles[9]))
                );
              }
          )
        ],
      )
    );
  }
}