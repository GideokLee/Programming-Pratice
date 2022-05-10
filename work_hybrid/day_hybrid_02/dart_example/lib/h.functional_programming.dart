void calc(int a, int b) {
  print(a + b);
}

void calc2(Function(int a) f) {
  f(20);
}

void main() {
  // 1.
  var fun = calc;
  fun(4, 5);

  // 2.
  calc2((a) {
    print(a);
  });
}


