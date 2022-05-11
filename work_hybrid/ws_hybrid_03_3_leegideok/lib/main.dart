import 'package:flutter/material.dart';
import 'shape.dart' as shape;
void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter WS_Day3'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  String result = "결과 값:";
  String input1 = "";
  String input2 = "";
  double distance = 0.0;
  int area = 0;
  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }
  void calSquare(){
      var a = shape.Position();
      a.x = 0;
      a.y = 0;
      var b = shape.Position();
      b.x = int.parse(input1);
      b.y = int.parse(input2);
      var cal = shape.CalcSquare();
      cal.x =int.parse(input1);
      cal.y = int.parse(input2);
      cal.width = cal.x;
      cal.height = cal.y;
      distance = cal.distanceTo(a);
      area = cal.getArea();
      result = "연산 결과 x=${b.x}, y=${b.y}\n거리 $distance\n넓이 :$area";
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        // Here we take the value from the MyHomePage object that was created by
        // the App.build method, and use it to set our appbar title.
        title: Text(widget.title),
      ),
      body: Center(
        // Center is a layout widget. It takes a single child and positions it
        // in the middle of the parent.
        child: Column(
          // horizontal).
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              '거리 & 사각형 면적 계산하기',
              style: TextStyle(fontSize: 20.0),
            ),
            TextField(
              onChanged: (text){
                setState(() {
                  input1 = text;
                });
              },
            ),
            TextField(
              onChanged: (text){
                setState(() {
                  input2 = text;
                });
              },
            )
            ,
            Text(
              '$result',
              style: TextStyle(
                fontSize: 15.0
              )
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: calSquare,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
