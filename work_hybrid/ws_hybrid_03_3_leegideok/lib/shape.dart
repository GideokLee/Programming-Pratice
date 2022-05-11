import 'dart:math';

class Position{
  int x = 0;
  int y = 0;

  double distanceTo(Position other){
    double result = sqrt( (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y) );
    return result;
  }
}

class Square{
  int width = 0;
  int height = 0;

  int getArea(){
    return width * height as int;
  }

}

class CalcSquare extends Square with Position{
}