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

  };
  
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