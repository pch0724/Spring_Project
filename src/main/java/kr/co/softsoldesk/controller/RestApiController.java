package kr.co.softsoldesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.softsoldesk.service.UserService;

@RestController
public class RestApiController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		
		boolean chk=userService.checkUserIdExist(user_id);
		
		return chk + ""; // jsp로 가라는게 아님 / 데이터(true/false)를 넘김
	}
		

}
// 동기식 : 서버에 모든 데이터가 한꺼번에 리로드
// 비동기식 : 서버에 하나의 데이터가 한꺼번에 리로드
// Asynchronous : JavaScript XMLHttpRequest => JSON
// A              Ja         X                 J