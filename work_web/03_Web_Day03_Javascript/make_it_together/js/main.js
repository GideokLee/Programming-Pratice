window.onload = function () {
  var question = localStorage.getItem("question");
  var pollBtnDiv = document.querySelector(".content-left-poll-btn");
  var pollDiv = document.querySelector(".content-left-poll");

  if (question) { // 질문이 있다면. >> 투표 내용 출력.
    var answers = localStorage.getItem("answers");
    var answerArr = JSON.parse(answers); // answers.split(",")
    var poll = `
    <div class="vote">
      <div class="vote-title">[ 당신의 선택 ]</div>
      <div class="vote-question">${question}</div>
      <div class="vote-answer">
        <ul>
      `;
    for (var i = 0; i < answerArr.length; i++) {
      poll += `
          <li>
            <label><input type="radio" name="vote-answer" value="${answerArr[i]}" /> ${answerArr[i]}</label>
          </li>
          `;
    }
    poll += `
        </ul >
      </div >
      <div class="vote-button">
        <button class="button btn-primary" onclick="javascript:poll();">투표하기</button>
        <button class="button">결과보기</button>
      </div>
      <div class="vote-date">투표기간 : ${localStorage.getItem("sd")} ~ ${localStorage.getItem("ed")}</div>
    </div >
      `;
    pollDiv.innerHTML = poll;
    pollBtnDiv.style.display = "none";
    pollDiv.style.display = "";
  } else {
    var poll = "진행중인 투표가 없습니다.";
    pollDiv.innerHTML = poll;
    pollBtnDiv.style.display = "";
    pollDiv.style.display = "";
  }

  document.getElementById("btn-makepoll").onclick = function () {
    window.open("./makepoll.html", "mp", "width=500, height=500, top=200, left=300");
  }
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
