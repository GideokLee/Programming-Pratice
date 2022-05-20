import 'package:flutter/material.dart';
import 'package:flutter/cupertino.dart';
import 'dart:io';

import 'package:flutter/services.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    if (Platform.isIOS) {
      return const CupertinoApp(
        home: CupertinoNativeApp(),
      );
    }
    else {
      return MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: NativeApp(),
      );
    }
  }
}

class CupertinoNativeApp extends StatefulWidget {
  const CupertinoNativeApp({Key? key}) : super(key: key);

  @override
  _CupertinoNative createState() {
    return _CupertinoNative();
  }
}

class _CupertinoNative extends State<CupertinoNativeApp> {
  @override
  Widget build(BuildContext context) {
    return const Scaffold();
  }
}

class NativeApp extends StatefulWidget {
  const NativeApp({Key? key}) : super(key: key);

  @override
  _NativeApp createState() => _NativeApp();
}

class _NativeApp extends State<NativeApp> {

  String _deviceInfo = '';  // 나중에 안드로이드로부터 정보가 들어올 변수
  static const methodChannel = MethodChannel('com.flutter.dev/info');

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Native 통신 예제'),
      ),
      body: Center(
        child: Text(_deviceInfo, style: const TextStyle(fontSize: 30)),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          _getDeviceInfo();
        },
        child: const Icon(Icons.get_app),
      ),
    );
  }

  Future<void> _getDeviceInfo() async {
    String deviceInfo;

    try {
      final String result = await methodChannel.invokeMethod('getDeviceInfo');
      deviceInfo = 'Device info : $result';
    }
    on PlatformException catch (e) {
      deviceInfo = "Failed to get Device info: '${e.message}'.";
    }

    setState(() {
      _deviceInfo = deviceInfo;
    });
  }
}
