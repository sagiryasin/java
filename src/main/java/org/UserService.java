
package org;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    
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
			Query createQuery = session.createQuery("Select user From User user ");
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
			result = 1;
		} catch (Exception e) {
			result = 0;
			logger.error(e.getMessage());
		}
        if(result !=0)
            return "index.xhtml?faces-redirect=true";
        else return "create.xhtml?faces-redirect=true";
    }
    // Used to fetch record to update
    public String edit(int id){
    	
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			User user = session.find(User.class, id);
			sessionMap.put("editUser", user);
		} catch (Exception e) {
			logger.error(e);
		}      
        return "/edit.xhtml?faces-redirect=true";
    }
    // Used to update user record
    public String update(User u){
    	
        int result = 0;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Transaction tx2 = session.beginTransaction();
			session.update(u);
			tx2.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
        return "/index.xhtml?faces-redirect=true";      
    }
    // Used to delete user record
    public void delete(int id){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try (Session session = sessionFactory.openSession()) {
			Transaction tx2 = session.beginTransaction();
			User user = session.find(User.class, id);
			session.delete(user);
			tx2.commit();
		} catch (Exception e) {
			logger.error(e);
		}
    }
    // Used to set user gender
    public String getGenderName(char gender){
        if(gender == 'M'){
            return "Male";
        }else return "Female";
    }
}
