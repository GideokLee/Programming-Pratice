1. Room, Coroutines을 쓰기 위한 Dependency 추가
2. DTO를 Room에 맞게 변경해준다.
1) Entity를 선언
2) PrimaryKey를 지정
3. database package를 생성한다.
4. database에 GalleryDatabase 추상클래스를 구현한다.
   1) RoomDatabase를 상속
   2) Photo Class를 Entity로 Database를 형성
5. 기존 Dao 클래스를 삭제하고 (기존 메서드는 백업해둔다.)
database에 Dao 인터페이스를 구현한다.
6. Repository를 구현한다.
7. Application 클래스를 구현한다.
8. 기존 Dao 호출 부분을 Repository에서 불러오는 방식으로 수정한다.
