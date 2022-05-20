import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  final title = '나만의 계산기';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: title,
      home: Calculator(title: title),
    );
  }
}

class Calculator extends StatefulWidget {
  const Calculator({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _CalculatorState createState() => _CalculatorState();
}

class _CalculatorState extends State<Calculator> {
  final List _buttonList = ['더하기', '빼기', '곱하기', '나누기'];
  final List<DropdownMenuItem<String>> _dropDownMenuItems = [];
  late String buttonText;
  String sum = '';
  TextEditingController value1 = TextEditingController();
  TextEditingController value2 = TextEditingController();

  @override
  void initState() {
    super.initState();

    for (var item in _buttonList) {
      _dropDownMenuItems.add(DropdownMenuItem(value: item, child: Text(item)));
    }
    buttonText = _dropDownMenuItems[0].value!;
  }

  @override
  void dispose() {
    value1.dispose();
    value2.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.all(15),
              child: Text(
                '결과 : $sum',
                style: const TextStyle(fontSize: 20),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 20, right: 20),
              child: TextField(
                keyboardType: TextInputType.number,
                controller: value1,
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 20, right: 20),
              child: TextField(
                keyboardType: TextInputType.number,
                controller: value2,
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(15),
              child: ElevatedButton(
                  child: Row(
                    children: <Widget>[const Icon(Icons.add_box), Text(buttonText)],
                  ),
                  //color: Colors.amber,
                  onPressed: () {
                    setState(() {
                      var value1Int = double.parse(value1.value.text);
                      var value2Int = double.parse(value2.value.text);
                      var result;
                      if (buttonText == '더하기') {
                        result = value1Int + value2Int;
                      }
                      else if (buttonText == '빼기') {
                        result = value1Int - value2Int;
                      }
                      else if (buttonText == '곱하기') {
                        result = value1Int * value2Int;
                      }
                      else {
                        result = value1Int / value2Int;
                      }
                      sum = result.toString();
                    });
                  }),
            ),
            Padding(
              padding: const EdgeInsets.all(15),
              child: DropdownButton(
                items: _dropDownMenuItems,
                onChanged: (value) {
                  setState(() {
                    buttonText = value as String;
                  });
                },
                value: buttonText,
              ),
            ),
          ],
        ),
      ),
    );
  }
}