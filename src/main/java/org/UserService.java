
package org;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@ManagedBean(name = "userService")
@RequestScoped
public class UserService {
	
	private static Logger logger = LogManager.getLogger(UserService.class);
	
    int id;
    String name;
    String email;
    String password;
    String gender;
    String address;
    ArrayList usersList ;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    } 
    // Used to fetch all records
    public ArrayList usersList(){
    	
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			String simpleName = "user";
			String lowerCaseSimpleName = simpleName.toLowerCase();
			Query createQuery = session.createQuery("Select " + lowerCaseSimpleName + " From " + simpleName + " " + lowerCaseSimpleName);
			return (ArrayList<User>) createQuery.list();
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
    }
    // Used to save user record
    public String save(){
        int result = 0;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Transaction tx2 = session.beginTransaction();
			User save = new User();
			save.setname(name);
			save.setEmail(email);
			save.setPassword(password);
			save.setGender(gender);
			save.setAddress(address);
			User savedEntity = (User) session.merge((String) null, save);
			tx2.commit();
			return "";
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
    }
    // Used to fetch record to update
    public String edit(int id){
//        User user = null;
//        System.out.println(id);
//        try{
//            connection = getConnection();
//            Statement stmt=getConnection().createStatement();  
//            ResultSet rs=stmt.executeQuery("select * from users where id = "+(id));
//            rs.next();
//            user = new User();
//            user.setid(rs.getInt("id"));
//            user.setname(rs.getString("name"));
//            user.setEmail(rs.getString("email"));
//            user.setGender(rs.getString("gender"));
//            user.setAddress(rs.getString("address"));
//            user.setPassword(rs.getString("password"));  
//            System.out.println(rs.getString("password"));
//            //sessionMap.put("editUser", user);
//            connection.close();
//        }catch(Exception e){
//            System.out.println(e);
//        }       
        return "/edit.xhtml?faces-redirect=true";
    }
    // Used to update user record
    public String update(User u){
//        //int result = 0;
//        try{
//            connection = getConnection();  
//            PreparedStatement stmt=connection.prepareStatement("update users set name=?,email=?,password=?,gender=?,address=? where id=?");  
//            stmt.setString(1,u.getname());  
//            stmt.setString(2,u.getEmail());  
//            stmt.setString(3,u.getPassword());  
//            stmt.setString(4,u.getGender());  
//            stmt.setString(5,u.getAddress());  
//            stmt.setInt(6,u.getid());  
//            stmt.executeUpdate();
//            connection.close();
//        }catch(Exception e){
//            System.out.println();
//        }
        return "/index.xhtml?faces-redirect=true";      
    }
    // Used to delete user record
    public void delete(int id){
//        try{
//            connection = getConnection();  
//            PreparedStatement stmt = connection.prepareStatement("delete from users where id = "+id);  
//            stmt.executeUpdate();  
//        }catch(Exception e){
//            System.out.println(e);
//        }
    }
    // Used to set user gender
    public String getGenderName(char gender){
        if(gender == 'M'){
            return "Male";
        }else return "Female";
    }
}
