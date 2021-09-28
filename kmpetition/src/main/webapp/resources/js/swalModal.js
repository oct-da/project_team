var idReg = /^[a-zA-Z0-9]{6,16}$/; // 아이디 형식
var pwReg = /^[a-zA-Z0-9]{8,16}$/; // 패스워드 형식
var emailReg = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/; // 이메일 형식
var numReg = /^\d{8,11}$/; // 전화번호 형식

var Popup = {};

var contextPath=sessionStorage.getItem("contextpath");

function popAlert(title, text, icon, confirmButtonText) {
    Swal.fire({
        title: title,
        text: text,
        icon: icon,
        confirmButtonText: confirmButtonText
    })
};

// 아이디 중복 체크
function idCheck(form) {
    var _id = $("#input_id").val();

    if (_id == '') {
        popAlert("아이디 입력", "아이디를 입력해 주세요.", "warning", "확인");
        return;
    }

    if (!idReg.test(input_id.value)) {
        popAlert("아이디 입력", "아이디는 영문/숫자 조합 6~16자리로 입력해 주세요.", "error", "확인")
        form.input_id.value = "";
        form.input_id.focus();
        return;
    }
	var url = contextPath+"/member/overlapped.do";
	
    $.ajax({
        type : "post",
        async : false,
        url : url,
        dataType : "text",
        data : {
            id : _id
        },
        success : function(data, textStatus) {
            if (data == 'false') {
                popAlert("아이디 확인", "사용 가능한 아이디입니다.", "success", "확인");
                $('#idCheckBtn').prop("readonly", true);
                $('#input_id').prop("readonly", true);
                $('#input_id').val(_id);
                $("input[name='checkedId']").val('y');
            } else {
                popAlert("아이디 확인", "사용할 수 없는 아이디입니다.", "error", "확인");
            }
        },
        error : function(data, textStatus) {
            popAlert("알 수 없는 에러", "에러가 발생했습니다.", "question", "확인");
        },
        complete : function(data, textStatus) {
            //alert("작업을 완료했습니다");
        }
    }); //end ajax	 
}

// 이메일 중복 체크
function emailCheck() {
    var _email = $("#input_email").val();
	var url = contextPath+"/member/checkEmail.do";
	
    if (_email == '') {
        popAlert("이메일 입력", "이메일을 입력해 주세요.", "warning", "확인");
        return;
    }

    if (!emailReg.test(input_email.value)) {
        popAlert("이메일 확인", "이메일 주소가 올바르지 않습니다.", "error", "확인");
        form.input_email.value = "";
        form.input_email.focus();
        return;
    }

    $.ajax({
        type : "post",
        async : false,
        url : url,
        dataType : "text",
        data : {
            email : _email
        },
        success : function(data, textStatus) {
            if (data == 'false') {
                popAlert("이메일 확인", "사용 가능한 이메일입니다.", "success", "확인");
                $('#emailCheckBtn').prop("readonly", true);
                $('#input_email').prop("readonly", true);
                $('#input_email').val(_email);
                $("input[name='checkedEmail']").val('y');
            } else {
                popAlert("이메일 확인", "사용할 수 없는 이메일입니다.", "error", "확인");
            }
        },
        error : function(data, textStatus) {
            popAlert("알 수 없는 에러", "에러가 발생했습니다.", "question", "확인");
        },
        complete : function(data, textStatus) {
            //alert("작업을 완료했습니다.");
        }
    }); //end ajax	 
}

// $(function() {
//     $('#select').change(function() {
//         if ($('#select').val() == 'directly') {
//             $('#textEmail').attr("disabled", false);
//             $('#textEmail').val("");
//             $('#textEmail').focus();
//         } else {
//             $('#textEmail').val($('#select').val());
//         }
//     })
// });

// 회원가입 양식 유효성 검사
function joinChk() {
    var form = document.frmJoin;

    if (!form.input_id.value) {
        popAlert("아이디 입력", "아이디를 입력해 주세요.", "warning", "확인");
        form.input_id.focus();
        return;
    }

    if (!idReg.test(input_id.value)) {
        popAlert("아이디 입력", "아이디는 영문/숫자 조합 6~16자리로 입력해 주세요.", "error", "확인")
        form.input_id.value = "";
        form.input_id.focus();
        return;
    }

    if ($("input[name='checkedId']").val() == ''){
        popAlert("아이디 중복 확인", "아이디 중복 확인을 해 주세요.", "warning", "확인");
        $("input[name='checkedId']").eq(0).focus();
        return;
    }

    if (!form.input_pw.value) {
        popAlert("비밀번호 입력", "비밀번호를 입력해 주세요.", "warning", "확인");
        form.input_pw.focus();
        return;
    }

    if (!form.input_pw.value || !pwReg.test(input_pw.value)) {
        popAlert("비밀번호 입력", "비밀번호는 영문/숫자 조합 8~16자리로 입력해 주세요.", "error", "확인");
        form.input_pw.value = "";
        form.input_pw.focus();
        return;
    }

    if (!form.input_pw_re.value) {
        popAlert("비밀번호 확인", "비밀번호를 확인해 주세요.", "warning", "확인");
        form.input_pw_re.focus();
        return;
    }

    if (form.input_pw_re.value != form.input_pw.value) {
        popAlert("비밀번호 확인", "비밀번호가 일치하지 않습니다. 다시 확인해 주세요.", "error", "확인");
        form.input_pw_re.value = "";
        form.input_pw_re.focus();
        return;
    }

    if (!form.input_name.value) {
        popAlert("이름 확인", "이름을 입력해 주세요.", "warning", "확인");
        form.input_name.focus();
        return;
    }

    if (!form.input_email.value) {
        popAlert("이메일 확인", "이메일을 입력해 주세요.", "warning", "확인");
        form.input_email.focus();
        return;
    }

    if (!emailReg.test(input_email.value)) {
        popAlert("이메일 확인", "이메일 주소가 올바르지 않습니다.", "error", "확인");
        form.input_email.value = "";
        form.input_email.focus();
        return;
    }

    if ($("input[name='checkedEmail']").val() == ''){
        popAlert("이메일 중복 확인", "이메일 중복 확인을 해 주세요.", "warning", "확인");
        $("input[name='checkedEmail']").eq(0).focus();
        return;
    }

    if (!form.input_num.value) {
        popAlert("전화번호 확인", "전화번호를 입력해 주세요.", "warning", "확인");
        form.input_num.focus();
        return;
    }

    if (!numReg.test(input_num.value)) {
        popAlert("전화번호 확인", "전화번호가 올바르지 않습니다.", "error", "확인")
        form.input_num.value = "";
        form.input_num.focus();
        return;
    }

    var chk1 = document.getElementById('chkbox1').checked; // 첫 번째 동의 체크 여부
    var chk2 = document.getElementById('chkbox2').checked; // 두 번째 동의 체크 여부

    if (chk1 == false) {
        popAlert("동의 확인", "개인정보 취급 및 수집에 동의해 주세요.", "warning", "확인");
        return;
    }

    if (chk2 == false) {
        popAlert("동의 확인", "이용 약관에 동의해 주세요.", "warning", "확인");
        return;
    }

    form.submit();
    popAlert("회원가입 완료", "정상적으로 가입이 완료되었습니다.", "success", "확인");
};

// 로그인 유효성 검사
function loginChk() {
    var form = document.frmLogin;
    
    if (!form.input_id.value) {
        popAlert("아이디 입력", "아이디를 입력해 주세요.", "warning", "확인");
        form.input_id.focus();
        return;
    }
    
    if (!idReg.test(input_id.value)) {
        popAlert("아이디 확인", "올바르지 않은 아이디입니다.", "error", "확인")
        form.input_id.value = "";
        form.input_id.focus();
        return;
    }

    if (!form.input_pw.value) {
        popAlert("비밀번호 입력", "비밀번호를 입력해 주세요.", "warning", "확인");
        form.input_pw.focus();
        return;
    }
    
    if (!pwReg.test(input_id.value)) {
        popAlert("비밀번호 확인", "올바르지 않은 비밀번호입니다.", "error", "확인")
        form.input_id.value = "";
        form.input_id.focus();
        return;
    }
    
    form.submit();
}

// 아이디 찾기 양식 유효성 검사
function findIdChk() {
    var form = document.frmFindId;
    
    if (!form.input_name.value) {
        popAlert("이름 입력", "이름을 입력해 주세요.", "warning", "확인");
        form.input_name.focus();
        return;
    }

    if (!form.input_email.value) {
        popAlert("이메일 입력", "이메일을 입력해 주세요.", "warning", "확인");
        form.input_email.focus();
        return;
    }
    
    if (!emailReg.test(input_email.value)) {
        popAlert("이메일 확인", "올바르지 않은 이메일 형식입니다.", "error", "확인")
        form.input_email.value = "";
        form.input_email.focus();
        return;
    }

    form.submit();
}

// 비밀번호 찾기 양식 유효성 검사
function findPwChk() {
    var form = document.frmFindPw;
    
    if (!form.input_id.value) {
        popAlert("아이디 입력", "아이디를 입력해 주세요.", "warning", "확인");
        form.input_id.focus();
        return;
    }
    
    if (!form.input_name.value) {
        popAlert("이름 입력", "이름을 입력해 주세요.", "warning", "확인");
        form.input_name.focus();
        return;
    }

    if (!form.input_email.value) {
        popAlert("이메일 입력", "이메일을 입력해 주세요.", "warning", "확인");
        form.input_email.focus();
        return;
    }
    
    if (!emailReg.test(input_email.value)) {
        popAlert("이메일 확인", "올바르지 않은 이메일 형식입니다.", "error", "확인")
        form.input_email.value = "";
        form.input_email.focus();
        return;
    }

    form.submit();
}

// 비밀번호 변경 유효성 검사
function pwChk() {
    var form = document.frmChangePw;
    
    if (!form.input_pw.value) {
        popAlert("비밀번호 입력", "비밀번호를 입력해 주세요.", "warning", "확인");
        form.input_pw.focus();
        return;
    }

    if (!form.input_pw.value || !pwReg.test(input_pw.value)) {
        popAlert("비밀번호 입력", "비밀번호는 영문/숫자 조합 8~16자리로 입력해 주세요.", "error", "확인");
        form.input_pw.focus();
        return;
    }
    
    if (!form.input_pw_re.value) {
        popAlert("비밀번호 확인", "비밀번호를 확인해 주세요.", "warning", "확인");
        form.input_pw_re.focus();
        return;
    }
    
    if (form.input_pw_re.value != form.input_pw.value) {
        popAlert("비밀번호 확인", "비밀번호가 일치하지 않습니다. 다시 확인해 주세요.", "error", "확인");
        form.input_pw_re.value = "";
        form.input_pw_re.focus();
        return;
    }

    form.submit();
}

// 휴면 해제 유효성 검사
function awakeChk() {
    var form = document.frmAwakeId;
    
    if (!form.input_id.value) {
        popAlert("아이디 입력", "아이디를 입력해 주세요.", "warning", "확인" );
        form.input_id.focus();
        return;
    }
    
    if (!form.input_pw.value) {
        popAlert("비밀번호 입력", "비밀번호를 입력해 주세요.", "warning", "확인");
        form.input_pw.focus();
        return;
    }
    
    if (!form.input_name.value) {
        popAlert("이름 입력", "이름을 입력해 주세요.", "warning", "확인");
        form.input_name.focus();
        return;
    }

    if (!form.input_email.value) {
        popAlert("이메일 입력", "이메일을 입력해 주세요.", "warning", "확인");
        form.input_email.focus();
        return;
    }
    
    if (!emailReg.test(input_email.value)) {
        popAlert("이메일 확인", "올바르지 않은 이메일 형식입니다.", "error", "확인")
        form.input_email.value = "";
        form.input_email.focus();
        return;
    }

    form.submit();
}

// 회원정보 수정 유효성 검사
function modCheck() {
    var form = document.frmModMember;

    if (!form.input_pw.value) {
        popAlert("비밀번호 입력", "비밀번호를 입력해 주세요.", "warning", "확인");
        form.input_pw.focus();
        return;
    }

    if (!form.input_pw.value || !pwReg.test(input_pw.value)) {
        popAlert("비밀번호 입력", "비밀번호는 영문/숫자 조합 8~16자리로 입력해 주세요.", "error", "확인");
        form.input_pw.value = "";
        form.input_pw.focus();
        return;
    }

    if (!form.input_pw_re.value) {
        popAlert("비밀번호 확인", "비밀번호를 확인해 주세요.", "warning", "확인");
        form.input_pw_re.focus();
        return;
    }

    if (form.input_pw_re.value != form.input_pw.value) {
        popAlert("비밀번호 확인", "비밀번호가 일치하지 않습니다. 다시 확인해 주세요.", "error", "확인");
        form.input_pw_re.value = "";
        form.input_pw_re.focus();
        return;
    }

    if (!form.input_name.value) {
        popAlert("이름 확인", "이름을 입력해 주세요.", "warning", "확인");
        form.input_name.focus();
        return;
    }

    if (!form.input_email.value) {
        popAlert("이메일 확인", "이메일을 입력해 주세요.", "warning", "확인");
        form.input_email.focus();
        return;
    }

    if (!emailReg.test(input_email.value)) {
        popAlert("이메일 확인", "이메일 주소가 올바르지 않습니다.", "error", "확인");
        form.input_email.value = "";
        form.input_email.focus();
        return;
    }

    if ($("input[name='checkedEmail']").val() == ''){
        popAlert("이메일 중복 확인", "이메일 중복 확인을 해 주세요.", "warning", "확인");
        $("input[name='checkedEmail']").eq(0).focus();
        return;
    }

    if (!form.input_num.value) {
        popAlert("전화번호 확인", "전화번호를 입력해 주세요.", "warning", "확인");
        form.input_num.focus();
        return;
    }

    if (!numReg.test(input_num.value)) {
        popAlert("전화번호 확인", "전화번호가 올바르지 않습니다.", "error", "확인")
        form.input_num.value = "";
        form.input_num.focus();
        return;
    }
    
    form.submit();
    popAlert("정보 수정 완료", "정상적으로 정보가 수정되었습니다.", "success", "확인");
};