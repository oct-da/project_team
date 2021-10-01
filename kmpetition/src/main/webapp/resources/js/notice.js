var Popup = {};

function popAlert(title, text, icon, confirmButtonText) {
    Swal.fire({
        title: title,
        text: text,
        icon: icon,
        confirmButtonText: confirmButtonText
    })
};


// 공지사항
function noticeChk(form) {
    var form = document.frmNotice;
    
    if (!form.title_id.value) {
        popAlert("제목 입력", "제목를 입력해 주세요.", "warning", "확인");
        form.title_id.focus();
        return;
    }

    if (!form.story.value) {
        popAlert("내용 입력", "내용를 입력해 주세요.", "warning", "확인");
        form.story.focus();
        return;
    }
    form.submit();
};