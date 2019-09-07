package com.licence.emaillicence.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.licence.emaillicence.util.AES;

@RestController
@RequestMapping("/licence")
public class LicenceController {
	
	private Map< String, Integer> listofCurrentuser = new HashMap<>();
	
	 @Autowired
	 private Environment env;
	
	@RequestMapping(value="/activate",method= RequestMethod.POST)
	public int getActiveAccount(HttpServletRequest handlerServlet) 
	{
		 String key =handlerServlet.getParameter("key");
		 String keyValue = env.getProperty("licence.product.key");
		 String secret = env.getProperty("aes.secretKey");
		 String decpkey = AES.decrypt(key, secret);
		 if(keyValue.contains(decpkey)) {
			 listofCurrentuser.put(decpkey, 1);
			 return 1;
		 }
		return 0;
		
	}
	
	@RequestMapping(value="/deactivate",method= RequestMethod.POST)
	public int getdeactivate(HttpServletRequest handlerServlet) throws IOException 
	{//http://localhost:9090/licence/deactivate?key=DEGL-NNNW-V0BN-TEBT
		
		 String key =handlerServlet.getParameter("key");
		 String keyValue = env.getProperty("licence.product.key");
		 String secret = env.getProperty("aes.secretKey");
		 String decpkey = AES.decrypt(key, secret);
		 if(keyValue.contains(decpkey)) {
			 listofCurrentuser.remove(decpkey);
		 }
		 System.out.println(listofCurrentuser.get(decpkey));
		return 0;
		
	}
	
	@RequestMapping("/helo")
	public String hello(@RequestParam("key") String key) 
	{
		 //http://localhost:9090/licence/helo?key=
		return "hello";
	}
	
	

}
