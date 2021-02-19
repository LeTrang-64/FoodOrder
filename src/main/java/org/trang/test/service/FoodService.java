package org.trang.test.service;
 
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.trang.test.dao.DBFood;
import org.trang.test.model.Food;

 

 
@Path("/Foods")
public class FoodService {

//	private HttpSession session;
//	Food food=(Food) session.getAttribute("food");
//	
 
    // URI:
    // /contextPath/servletPath/Foods
	@Path("/list")
    @GET
    @Produces({ MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML  })
    public List<Food> getFoods_JSON() throws ClassNotFoundException {
        List<Food> listOfCountries=DBFood.listAllFoods();	
        return listOfCountries;
    }
 
    // URI:
    // /contextPath/servletPath/Foods/{empNo}
    @GET
    @Path("/{empNo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Food getFood(@PathParam("empNo") String empNo) throws ClassNotFoundException{
    	int id=Integer.parseInt(empNo);
    	Food food=DBFood.getFoodbyID(id);
        return food;
    }
 
    // URI:
    // /contextPath/servletPath/Foods
    @POST
    @Path("/create")
    @Produces("text/plain")
    public String addFood(Food food) throws  ClassNotFoundException {
        boolean rs= DBFood.addNewFood(food);
         if(rs) {
 			
 	        return "true";
 		}
 		return "False";
    }
 
    // URI:
    // /contextPath/servletPath/Foods
    @PUT
    @Path("/update")
    @Produces("text/plain")
    public String updateFood(Food food) throws  ClassNotFoundException {
        boolean rs= DBFood.updateFood(food);
        if(rs) {
 			
 	        return "true";
 		}
 		return "false";
    }
 
    @DELETE
    @Path("delete/{empNo}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public void deleteFood(@PathParam("empNo") String empNo) throws ClassNotFoundException, SQLException {
    	int id=Integer.parseInt(empNo);
        DBFood.deleteFood(id);
    }
 
}