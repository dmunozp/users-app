/**
 * 
 */
package com.devs4j.users.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dmunpalo
 *
 */
@RestController
@RequestMapping("/hello")
public class Devs4jController {

	@GetMapping
	public String helloWorld() {
		return "Hello World!";
	}
}
