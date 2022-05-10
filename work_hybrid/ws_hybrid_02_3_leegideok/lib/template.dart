import 'dart:convert';
import 'dart:io';

void main() {
  File('files/sample_input.txt').readAsString().then((String contents) {
    var lineSplitter = LineSplitter();
    var lines = lineSplitter.convert(contents);
    var it = lines.iterator;

    it.moveNext();
    var T = int.parse(it.current);
    for (int test_case = 1; test_case <= T; test_case++) {
      stdout.write('#$test_case ');
      
      // 알고리즘 코드 작성
      var solution = Solution();
      solution.run(it);
    }
  });
}

class Solution {
  int _N = 0;
  int _M = 0;
  void run(Iterator<String> it) {
    it.moveNext();
    var t = it.current.split(' ');

    _N = int.parse(t.first);
    _M = int.parse(t.last);

    var a = List.generate(_N, (index) => List.filled(_N, 0)); // 이차원 배열
    var list = List.generate(_M, (index) => Sort());
    for(int k =0; k <_M; k++ ){
      it.moveNext();
      var raw = it.current.split(' ');
      list[k].i = int.parse(raw[0]);
      list[k].j = int.parse(raw[1]);
      list[k].d = int.parse(raw[2]);
      a[list[k].i][list[k].j] = 1;
    }
    var move_i = [0,1,-1,0,0];
    var move_j = [0,0,0,-1,1];
    int result = _M;

    for(int i = 3; i > 0; i--){
      for(int j = 0; j < _M; j++){
        if(list[j].d != 0){
          int curr_i = list[j].i + (i * move_i[list[j].d]);
          int curr_j = list[j].j + (i * move_j[list[j].d]);

          if(curr_i< 0 || curr_i >= _N){
          }else if(curr_j < 0 || curr_j >= _N){
          }else if(a[curr_i][curr_j] == 1){
          }else{
            a[curr_i][curr_j] = 1;
            a[list[j].i][list[j].j] = 0;
            continue;
          }
          list[j].d = 0;
          result--;
          a[list[j].i][list[j].j] = 0;
        }
      }
    }
    stdout.write(' $result \n');
  }
}

class Sort{
  int i = 0;
  int j = 0;
  int d = 0;
}