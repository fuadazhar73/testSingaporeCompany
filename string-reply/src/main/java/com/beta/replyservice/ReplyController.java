package com.beta.replyservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.*;
import org.apache.commons.codec.digest.DigestUtils;
import java.lang.*;
import java.io.*;
import java.util.*;


@RestController
public class ReplyController {

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	@ResponseBody
	public ResponseEntity<Object> replying(@PathVariable String message) {
		StringBuilder input1 = new StringBuilder();
		if(message.contains("-")){
		String[] messageData = message.split("-");
		String cek1 = messageData[0];
		String cek2 = messageData[1];
		if(cek1.equals("1")){
			input1.append(cek2);
			return new ResponseEntity<>(new ReplyMessage(input1.reverse().toString()),HttpStatus.OK);
		}else if(cek1.equals("2")){
			    String md5Hex = DigestUtils.md5Hex(cek2);
			return new ResponseEntity<>(new ReplyMessage(md5Hex),HttpStatus.OK);
		}else if(cek1.equals("11")){
			return new ResponseEntity<>(new ReplyMessage(cek1),HttpStatus.OK);
		}else if(cek1.equals("12")){
			input1.append(cek2);
			String reverse =input1.reverse().toString();
			String md5Hex = DigestUtils.md5Hex(reverse);
			return new ResponseEntity<>(new ReplyMessage(md5Hex),HttpStatus.OK);
		}else if(cek1.equals("22")){
			String md5Hex = DigestUtils.md5Hex(cek2);
			String md5Hex2x = DigestUtils.md5Hex(md5Hex);
			return new ResponseEntity<>(new ReplyMessage(md5Hex2x),HttpStatus.OK);
		}
	}else{
			return new ResponseEntity<>(new ReplyMessage(message),HttpStatus.OK);
	}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ReplyMessage("invalid Input"));
	}
}