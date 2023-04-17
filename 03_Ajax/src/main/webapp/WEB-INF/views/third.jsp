<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>

	function fn1(){
		$.ajax({
			// 요청
			type: 'post', 			// 서버로 보낼 데이터를 요청 본문(request body)에 저장해서 보낸다.
			url: '${contextPath}/third/ajax1',
			data: JSON.stringify({	// json내장객체 중 stringify는 자바스크립트 객체를 문자열 형식의 json처럼 바꿔준다.
									// 문자열 형식의 JSON 데이터를 서버로 보낸다. 파라미터 이름이 없음에 주의한다.(서버에서 파라미터로 받을 수 없다.)('param='JSON.stringify이런 것 처럼)
				'name': $('#name').val(), 	// name이란 property로 만든다.
				'tel': $('#tel').val()
			}),		
			/* 예시 - data: '{"name": "kim", "tel" : "010-2682-0247"}' 파라미터 이름이 없기 때문에*/
			contentType: 'application/json', // 서버로 보내는 data의 타입을 서버에 알려준다. 
			// 응답
			dataType: 'json',
			success: function(resData){		// resData = {"name": "정병인" ,"tel" : "010-1111-1111"}이런 걸 받아온다.
				let str = '<ul>';
				str += '<li>' + resData.name;	// 닫는 태그도 없어도 알아서한다.
				str += '<li>' + resData.tel;
				$('#result').html(str);		//result에다가 뿌린다. append는 원래있던거에 추가. 원래있던것을 지워주는 empty. html 내용을 갈아 끼우는 것 기존의 내용이 없이지고 덮어씌어짐
			},
			error: function(jqXHR){
				if(jqXHR.status == 400){
					alert('이름과 전화번호는 필수입니다.');
				}				
			}
		})
	}
	function fn2(){
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/third/ajax2',
			data: JSON.stringify({
				'name': $('#name').val(),
				'tel': $('#tel').val()
			}),
			contentType: 'application/json',
			// 응답
			dataType: 'json',
			success: function(resData){
				let str = '<ul>';
				str += '<li>' + resData.name;
				str += '<li>' + resData.tel;
				$('#result').html(str);
			},
			error: function(jqXHR){
				if(jqXHR.status == 400){
					alert('이름과 전화번호는 필수입니다.');
				}
			}
				
		})
	}
	
</script>
</head>
<body>
	
	<div>
      <form id="frm">
         <div>
            <label for="name">이름</label>
            <input id="name" name="name">
         </div>
         <div>
            <label for="tel">전화번호</label>
            <input id="tel" name="tel">
         </div>
         <div>
            <input type="button" value="전송1" onclick="fn1()">
            <input type="button" value="전송2" onclick="fn2()">
         </div>
      </form>
   </div>
   
   <hr>
   
   <div id="result"></div>
   
   

</body>
</html>