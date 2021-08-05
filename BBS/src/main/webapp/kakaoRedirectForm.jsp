<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
const code = location.search.split('?code=')[1];
if (code !== undefined) {
    loginWithKakaoAjax(code);
}

function loginWithKakaoAjax(code) {
    const JS_APP_KEY ="188766d70b45863a165fa74d7d8a455b";
    const REDIRECT_URI = "http://localhost:8080/BBS/kakaoRedirectForm.jsp";
    const makeFormData = params => {
        const searchParams = new URLSearchParams()
            Object.keys(params).forEach(key => {
                searchParams.append(key, params[key])
            })
        return searchParams
    }

    $.ajax({
        type: "POST",
        url: 'https://kauth.kakao.com/oauth/token',
        data: {
            grant_type: 'authorization_code',
            client_id: JS_APP_KEY,
            redirect_uri: REDIRECT_URI,
            code
        },
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        },
           
        success: function (res) {
            console.log(res);
			
			$('body').append('access_token : success' + res);
        }
    });
}
</script>
</body>
</html>