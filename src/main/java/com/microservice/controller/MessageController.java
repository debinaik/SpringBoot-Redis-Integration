package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.cache.CacheService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="microservice/user")
public class MessageController {
	
    @Autowired
    private CacheService cacheService;
    
    
    @ApiOperation(value = "getGreeting",notes = "This api is used to get all the greeting message", httpMethod = "GET")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}) 
    
    @RequestMapping(value = "/getMessage/{user}",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<String> greeting(@PathVariable String user) {
        List<String> messages = cacheService.listMessages(user);
        return messages;
    }
    
    
    @ApiOperation(value = "saveGreeting",notes = "This api is used to save greeeting message for a user", httpMethod = "POST")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Server Error")}) 
    
    @RequestMapping(value = "/saveMessage",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String saveGreeting(@RequestBody String user) {
        cacheService.addMessage(user,"Hello World");
        return "OK";
    }
}
