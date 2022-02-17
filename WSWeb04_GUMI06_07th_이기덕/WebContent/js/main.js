window.onload = function () {
  var login = false;
  login_layout(login);
  document.getElementById("bt_login").onclick = function(){
      var id  = prompt("아이디 입력");
      if(id != null && id.match("ssafy")){
        var password = prompt("비밀번호 입력");
        if(password.match("1234")){
            login = true;
            alert("로그인 성공!");
            login_layout(login);
        }
        return;
      }
      alert("로그인 실패!");
      login_layout(login);
  }

  document.getElementById("mypage").onclick = function () {
    window.open("./makepoll.html", "mp", "width=500, height=500, top=200, left=300");
  }

  
  const items = document.querySelectorAll('.store_item');

  function openCloseStore() {
    const answerId = this.id.replace('area', 'store');
    if(document.getElementById(answerId).style.display === 'block') {
      document.getElementById(answerId).style.display = 'none';
    } else {
      document.getElementById(answerId).style.display = 'block';
    }
  }
 
  items.forEach(item => item.addEventListener('click', openCloseStore));

  sendRequest2('./programming.xml', null, 'GET', makeListXml, error, "xml");
  sendRequest2('./essay.json', null, 'GET', makeListJson, error, "json");

  };
  function makeListXml(data) {
    console.log(data);
    let ul = document.getElementById("books_prog");

    let books = data.getElementsByTagName("book");

    for (var i = 0; i < books.length; i++) {
      let book = [];
      book[0] = books[i].getElementsByTagName("isbn")[0].childNodes[0].nodeValue;
      book[1] = books[i].getElementsByTagName("title")[0].childNodes[0].nodeValue;
      book[2] = books[i].getElementsByTagName("price")[0].childNodes[0].nodeValue;
      let li = document.createElement("li");
      let div1 = document.createElement("div");
      div1.className = "item";
      let img = document.createElement("img");
      img.src = "img/book/" + book[0] + ".png";
      div1.appendChild(img);

      let div2 = document.createElement("div");
      div2.className = "desc";
      div2.appendChild(document.createTextNode(book[1] + "\n(" + book[2]+ ")원"));

      div1.append(div2);
      li.append(div1);
      ul.append(li);
    }
  }
  function makeListJson(data) {

    let ul = document.getElementById("books_ess");

    let books = JSON.parse(data);
    console.log(books);
    for (var i = 0; i < books.length; i++) {

      let li = document.createElement("li");
      let div1 = document.createElement("div");
      div1.className = "item";
      let img = document.createElement("img");
      img.src = "img/book/" + books[i].isbn + ".png";
      div1.appendChild(img);

      let div2 = document.createElement("div");
      div2.className = "desc";
      div2.appendChild(document.createTextNode(books[i].title + "\n(" + books[i].price+ ")원"));

      div1.append(div2);
      li.append(div1);
      ul.append(li);
    }
  }
  
  function error(status, msg) {
    console.log("상태값: " + status + " / http에러메시지 :" + msg);
  }
  function poll() {
    var votes = document.getElementsByName("vote-answer");
    var selAnswer = "";
    for (var i = 0; i < votes.length; i++) {
      if (votes[i].checked) {
        selAnswer = votes[i].value;
        break;
      }
    }
    alert(selAnswer + "을 선택했습니다.");
  }
  
  function login_layout(togle){
    if(togle){
        document.getElementById("login_img").style.display = "block";
        document.getElementById("logout").style.display = "block";
        document.getElementById("mypage").style.display = "block";
        document.getElementById("manager").style.display = "block";
        document.getElementById("bt_login").style.display ="none";
        document.getElementById("logout_img").style.display ="none";
        document.getElementById("join").style.display = "none";
      }else{
        document.getElementById("login_img").style.display = "none";
        document.getElementById("logout").style.display = "none";
        document.getElementById("mypage").style.display = "none";
        document.getElementById("manager").style.display = "none";
      }
  }