package com.gdu.movie.util;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	// 크로스 사이트 스크립팅(Cross Site Scripting) 방지하기
	public String preventXSS(String str) {  // 가지고온 값이 <script>로 들어오더라도 내보낼 땐 &lt; + script + &gt;로 내보내라 해주지않으면 인식을 못하기 때문
		str = str.replace("<", "&lt;");
		str = str.replace(">", "&gt;");
		return str;
	}
	
}
