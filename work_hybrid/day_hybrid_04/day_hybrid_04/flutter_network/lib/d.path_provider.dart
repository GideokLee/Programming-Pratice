import 'package:flutter/material.dart';
import 'dart:io';
import 'package:path_provider/path_provider.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      title: 'Flutter Demo',
      home: FileApp(),
    );
  }
}

class FileApp extends StatefulWidget {
  const FileApp({Key? key}) : super(key: key);

  @override
  _FileApp createState() => _FileApp();
}

class _FileApp extends State<FileApp> {
  int _count = 0;

  @override
  void initState() {
    super.initState();
    initFile();
  }

  Future initFile() async {
    var dir = await getApplicationDocumentsDirectory();
    print('initFile() 호출 ... ${dir.path}');

    var file = File(dir.path + '/count.txt');
    if (await file.exists()) {
      readCountFile();
    }
    else {
      file.writeAsStringSync('0');
    }
  }

  Future readCountFile() async {
    try {
      var dir = await getApplicationDocumentsDirectory();
      var file = await File(dir.path + '/count.txt').readAsString();
      print('readCountFile() 호출 ... $file');

      setState(() {
        _count = int.parse(file);
      });
    }
    catch (e) {
      print(e.toString());
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('File Example')),
      body: Center(
        child: Text('$_count', style: const TextStyle(fontSize: 30)),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          setState(() {
            _count++;
          });
          writeCountFile(_count);
        },
        child: const Icon(Icons.add),
      ),
    );
  }

  Future writeCountFile(int count) async {
    var dir = await getApplicationDocumentsDirectory();
    File(dir.path + '/count.txt').writeAsStringSync(count.toString());
  }
}
