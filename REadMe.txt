Set Database in 
src/main/resource
  > application.properties
    set the database url and db name, userid, password

Run the code, It will generate the tables in the database

======================================
create a user file under src/main/java
com.klu
write click the project and create a new java class
1) create a fields
2) right click > source > generate getter ,setter
                       generate constructor with initialization
3) 
create UserRepo interface and add external lib
search Jparepo
include this > public User findByUId(String uid); 

4)in service.java

@Autowired
	UserRepo repo5;
	
	public String insertUser(User user) {
		repo5.save(user);
		return "Inserted";
	}
	
	public List<User> viewUser() {
		return repo5.findAll();	
	}
	
User Authentication Flow
========================
From UI - update the server url with field and values 
1) AppController -> service.java





