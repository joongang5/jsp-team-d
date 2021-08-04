<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="UTF-8">
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<title>Document</title>
</head>
<body>

	<br>임시
	<br>

	<button id="kakaoLogin" onclick="kakaoLogin()">kakaoLogin</button>
	<button id="kakaoLogout" onclick="kakaoLogout()">kakaoLogout</button>

	<a href="javascript:kakaoLogin();">카톡 로그인 (a tag)</a>


	<script>
      window.Kakao.init('2d52ddc31775dc1f031f8163183ae794')
      
      
      
      function kakaoLogin() {
    //로그인하고
    	window.Kakao.Auth.login({
    		scope:'profile, account_email',
      		success: function (authObj) {
      			console.log(authObj);
      			window.Kakao.API.request({
          			url: '/v2/user/me', //계정 정보를 가져오는 request url
          			success: success: res => {
          			const kakao_account = res.kakao_account;
            let user = response.kakao_account //카카오 계정 정보
            //console.log(user)
            user.host = 'kakao' //다른 로그인 서비스와 구분하기 위해서 개인적으로 추가했음
            // 해당 페이지에서 객체를 만들고 곧바로 user 정보를 사용할 수 도 있고,
            // input 엘리먼트에 json으로 저장해뒀다가 나중에 사용할 수도 있음. 여기서는 input에 저장
            const userinfo = document.querySelector('#userinfo')
            if (userinfo) userinfo.value = JSON.stringify(user) //user를 json문자열로 변환해서 저장해두기
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
  function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      //토큰이 있으면
      Kakao.API.request({
        //로그아웃하고
        url: '/v1/user/unlink',
        success: function (response) {
          //console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      //토큰도 삭제
      Kakao.Auth.setAccessToken(undefined)
      //유저정보도 삭제
      const userinfoElem = document.querySelector('#userinfo') 
      if(userinfoElem) userinfoElem.value = ''
    }
  }
    </script>

	<script>
    let user
    const userinfoElem = document.querySelector('#userinfo') //유저정보가 들어있는 input 엘리먼트
    //1초마다 유저정보가 있는지 체크하기
    let frame = setInterval(function(){
      console.log('유저정보 체크')
      if (userinfoElem && userinfoElem.value) { 
        //유저정보가 있으면 체크 멈추고
        clearInterval(frame) 
        //유저 정보를 저장
        user = JSON.parse(userinfoElem.value) 
        
        //필요하면 백엔드로 유저정보 보내서 토큰 만들고 쿠키에 저장하기
        Axios.post('backend_url', {user: user}, {withCredentials: true})
          .then(res => {
            console.log(`${user.username}로그인 성공`)
          })
          .catch(error => {
            console.log(error)
          })         
      }
    }, 1000)
</script>

</body>
</html>