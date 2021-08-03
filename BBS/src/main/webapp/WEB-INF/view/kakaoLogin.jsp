<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <a href="javascript:kakaoLogin();"><img src="./kakao_login.png" alt="īī������ �α���" style="height: 100px;"/></a>

    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
        Kakao.init('2d52ddc31775dc1f031f8163183ae794');
        console.log(Kakao.isInitialized());
        
        function kakaoLogin() {
            window.Kakao.Auth.login({
                scope: 'profile, account_email', //�����׸� �������� �ִ� �������� ��ȣ ���̺��� Ȱ��ȭ�� ID���� �ֽ��ϴ�.
                success: function(response) {
                    console.log(response) // �α��� �����ϸ� �޾ƿ��� ������
                    window.Kakao.API.request({ // ����� ���� �������� 
                        url: '/v2/user/me',
                        success: (res) => {
                            const kakao_account = res.kakao_account;
                            console.log(kakao_account)
                        }
                    });
                    window.location.href='index.do'
                },
                fail: function(error) {
                    console.log(error);
                }
            });
        }
    </script>
</body>
</html>