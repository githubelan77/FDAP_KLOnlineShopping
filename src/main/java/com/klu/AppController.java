package com.klu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AppController {
	@Autowired
	Service service;
	
	@Autowired
	JWTManager jwt;
	
	@GetMapping("/")
	public String fun1() {
		return "welcome";
	}
	
	@PostMapping("/userin")
	public String userInsert(@RequestBody User user) {
		var encPwd = user.getUpassword();
		 user.setUpassword(jwt.encryptData(encPwd));
		return service.insertUser(user);
	}
///////////////////////////////////////
	@PostMapping("/resetpwd")
	public Map<String, String> userPwd(@RequestBody Map<String, Object> payload) {
	    String userid = (String) payload.get("uname");
	    String upwd = (String) payload.get("upassword");
	    String nPwd = (String) payload.get("npassword");
	    return service.updatepwd(userid,jwt.encryptData(upwd), jwt.encryptData(nPwd));
	}
	
	////////////////////////////////////////////////////////////
	
	@GetMapping("/user")
	public List<User> users(){
		return service.viewUser();
	}
	
	@PostMapping("/lapin")
	public String laptopInsert(@RequestBody Laptops laptop) {
		return service.insertLaptops(laptop);		
	}
	
	@GetMapping("/laptops")
	public List<Laptops> laptopView(@RequestParam("token") String token){
		System.out.println("Role :"+jwt.validateToken(token));
		if (jwt.validateToken(token).get("username").equals("admin") || jwt.validateToken(token).get("username").equals("user")) {
			return service.viewLaptops();
		}else {
			return  new ArrayList<Laptops>();
		}		
	}
	@PostMapping("/login")
	public Map<String, String> loginView(@RequestBody User user){
		user.setUpassword(jwt.encryptData(user.getUpassword()));		
		return service.userAuth(user);
	}
	
	@GetMapping("/laptops/{pid}")
	public Laptops oneLaptopView(@PathVariable("pid") int pid) {
		return service.viewOneLaptop(pid);
	}
	@DeleteMapping("/dellaptops/{pid}")
	public Laptops oneLaptopDelete(@PathVariable("pid") int pid) {
		return service.deleteLaptop(pid);
	}
		
	@PostMapping("/mobin")
	public String mobileInsert(@RequestBody Mobiles mobile) {
		
		return service.insertMobiles(mobile);
		
	}
		@GetMapping("/mobiles")
	public List<Mobiles> mobileView(){
		return service.viewMobiles();
	}
		@GetMapping("/mobiles/{pid}")
		public Mobiles oneMobileView(@PathVariable("pid") int pid) {
			return service.viewOneMobile(pid);
		}		
	
		
		
		@PostMapping("/headin")
		public String headInsert(@RequestBody Headphones headphone) {
			
			return service.insertHeadphones(headphone);
			
		}
			@GetMapping("/headphones")
		public List<Headphones> headphoneView(){
			return service.viewHeadphones();
		}
			@GetMapping("/headphones/{pid}")
			public Headphones oneHeadphoneView(@PathVariable("pid") int pid) {
				return service.viewOneHeadphone(pid);
			}		
			
// update -start
		@DeleteMapping("/deldairy/{pid}")
		public Mobiles oneDairyDelete(@PathVariable("pid") int pid) {
			return service.deleteDairy(pid);
		}
			@DeleteMapping("/delcosmetics/{pid}")
			public Headphones oneCosmeticsDelete(@PathVariable("pid") int pid) {
				return service.deleteCosmetics(pid);
			}
		@DeleteMapping("/delgrain/{pid}")
			public Laptops oneGrainDelete(@PathVariable("pid") int pid) {
				return service.deleteGrain1(pid);
			}
// update -end

			
		

}
