import 'package:flutter/material.dart';

class TimePickerPage extends StatefulWidget {
  const TimePickerPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _TimePickerPageState createState() => _TimePickerPageState();
}

class _TimePickerPageState extends State<TimePickerPage> {
  String _selectedTime = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title)
      ),
      body: Column(
        children: <Widget>[
          ElevatedButton(
            onPressed: () {
              Future<TimeOfDay?> selectedTime = showTimePicker(
                initialTime: TimeOfDay.now(),
                context: context,
              );

              selectedTime.then((timeOfDay) {
                setState(() {
                  _selectedTime = '${timeOfDay?.hour}:${timeOfDay?.minute}';
                });
              });
            },
            child: const Text('Time Picker'),
          ),
          Text(_selectedTime),
        ],
      ),
    );
  }
}
