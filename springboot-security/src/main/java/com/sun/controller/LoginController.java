package com.sun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	 @GetMapping("/loginSuccess")
	 public String loginSucess() {       
	     return "登录成功";
	 }
}
