package com.klu;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {
	
	@Autowired
	LaptopsRepo repo1;
	
	@Autowired
	LoginRepo repo2;
	
	
	@Autowired
	MobilesRepo repo3;
	
	@Autowired
	HeadphonesRepo repo4;
	
	@Autowired
	UserRepo repo5;
	public Service() {}
	
	public String insertUser(User user) {
		repo5.save(user);
		return "Inserted";
	}
	
	public List<User> viewUser() {
		return repo5.findAll();	
	}
	
	public String insertLaptops(Laptops laptop) {
		repo1.save(laptop);
		return "Inserted";
	}
	
	public List<Laptops> viewLaptops() {
		return repo1.findAll();	
	}
	public Map<String, String> updatepwd(String userid, String upwd, String nPwd) {
		User user2=repo5.findByuname(userid);
		Map<String, String> res=new HashMap<>();
		if(user2==null) {
		res.put("status","fail");
		}
		else if(user2.getUpassword().equals(upwd))
		{
			user2.setUpassword(nPwd);
			repo5.save(user2);
			res.put("status", "success");
		}
		else {
			res.put("login", "fail");
		}
		return res;		
	}
	
	@Autowired
	JWTManager jwt;
	
	public Map<String, String> userAuth(User user) {
		User user2=repo5.findByuname(user.getUname());
		Map<String, String> res=new HashMap<>();
		if(user2==null) {
		res.put("login","fail");
		}
		else if(user2.getUpassword().equals(user.getUpassword()))
		{
			res.put("login", "success");
			res.put("role", user2.getRole());
			res.put("token",jwt.generateToken(user2.getRole()));
			
		}
		else {
			res.put("login", "fail");
		}
		return res;
	}
	
	public Map<String, String> viewUser(Login login) {
		Login login2=repo2.findByUsername(login.getUsername());
		Map<String, String> res=new HashMap<>();
		if(login2==null) {
		res.put("login","fail");
		}
		else if(login2.getPassword().equals(login.getPassword()))
		{
			res.put("login", "success");
		}
		else {
			res.put("login", "fail");
		}
		return res;
	}

	public Laptops viewOneLaptop(int pid) {
		return repo1.findByPid(pid);
	}
	
	public Laptops deleteLaptop(int pid) {
		repo1.deleteById((long)pid);	
		return null;
	}
//	Update - start
	public Laptops deleteGrain1(int pid) {
		repo1.deleteById((long)pid);	
		return null;
	}
	
	public Mobiles deleteDairy(int pid) {
		repo3.deleteById((long)pid);	
		return null;
	}
	
	public Headphones deleteCosmetics(int pid) {
		repo4.deleteById((long)pid);	
		return null;
	}	
	
//	update end
	public String insertMobiles(Mobiles mobile) {
		repo3.save(mobile);
		return "Inserted";
	}
	
	public List<Mobiles> viewMobiles() {
		return repo3.findAll();	
	}
	public Mobiles viewOneMobile(int pid) {
		return repo3.findByPid(pid);
	}
	
	
	public String insertHeadphones(Headphones headphone) {
		repo4.save(headphone);
		return "Inserted";
	}
	
	public List<Headphones> viewHeadphones() {
		return repo4.findAll();	
	}
	public Headphones viewOneHeadphone(int pid) {
		return repo4.findByPid(pid);
	}
	

	public String updateField(String userid, String fieldName, String fieldValue) {
		 User user = repo5.findByuname(userid);//.orElse(null);

	        if (user == null) {
	            return "User not found";
	        }

	        // Update the specified field dynamically
	        try {
	            Field field = User.class.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            field.set(user, fieldValue);
	        } catch (NoSuchFieldException | IllegalAccessException e) {
	            return "Invalid field name";
	        }

	        // Save the updated user
	        repo5.save(user);
	        return "Field updated successfully";
	    }
	}
	
	
	
	
	

