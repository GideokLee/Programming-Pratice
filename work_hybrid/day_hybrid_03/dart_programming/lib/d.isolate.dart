import 'dart:isolate';

  void main() {
    Isolate.spawn(sendMessage, '1. Kotlin');
    Isolate.spawn(sendMessage, '2. Android');
    Isolate.spawn(sendMessage, '3. Hybrid');
  }

  void sendMessage(var message) {
    print('This is a ${message}');
  }

