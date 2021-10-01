$(function() {
   const layer_button = document.getElementById("form_text");
   const layer = document.getElementById("layer");

   layer_button.addEventListener("click", function(e) {
      layer.setAttribute("style", "display: flex");


   });

   layer_close.addEventListener("click", function(e) {
      layer.setAttribute("style", "display: none");

   });
});




$(function() {

   $(".button").click(function() { //btnAdd라는 버튼을 눌렀을때 ->이벤트 등록


      var html = '<tr><td>' + '<input type="text">' + '</td>'; //tr, td를 열고 + 문자열로 바꾸고 +td 닫기
      html += '<td></p><button type="button" class="btn btn-outline-secondary btnDel">제거</button>'; //html변수에 삭제버튼을 대입
      html += '</td></tr>';

      $("#boxWrap").append(html); //list라는 아이디에 html을 추가해라
   });

   $("#boxWrap").on("click", ".btnDel", function() { //list안의 btnDel을 선택
      $(this).parent().parent().remove(); //this(btnDel)의 부모(td)의 부모(tr)를 삭제
   });



});




$(function() {
   $(".button2").click(function() { //btnAdd라는 버튼을 눌렀을때 ->이벤트 등록

      var html = '<tr><td>' + '<input type="text">' + '</td>'; //tr, td를 열고 + 문자열로 바꾸고 +td 닫기
      html += '<td></p><button type="button" class="btn btn-outline-secondary btnDel">제거</button>'; //html변수에 삭제버튼을 대입
      html += '</td></tr>';

      $("#boxWrap2").append(html); //list라는 아이디에 html을 추가해라
   });

   $("#boxWrap2").on("click", ".btnDel", function() { //list안의 btnDel을 선택
      $(this).parent().parent().remove(); //this(btnDel)의 부모(td)의 부모(tr)를 삭제
   });



});
$(document).ready(function() {
   document.getElementById('today').value = new Date().toISOString().substring(0, 10);
});


$(document).ready(function() {
   document.getElementById('today1').value = new Date().toISOString().substring(0, 10);
});


$(function() {
   $("#month1").click(function() {
      var Currentdate = new Date()
      Currentdate.setMonth(Currentdate.getMonth() - 1)



      document.getElementById('today1').value = Currentdate.toISOString().substring(0, 10);
   });

});

$(function() {
   $("#month2").click(function() {
      var Currentdate = new Date()
      Currentdate.setMonth(Currentdate.getMonth() - 2)



      document.getElementById('today1').value = Currentdate.toISOString().substring(0, 10);
   });

});
$(function() {
   $("#month3").click(function() {
      var Currentdate = new Date()
      Currentdate.setMonth(Currentdate.getMonth() - 3)



      document.getElementById('today1').value = Currentdate.toISOString().substring(0, 10);
   });

});