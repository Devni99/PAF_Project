package com;
import model.User;

//for Rest Services
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;
//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 


@Path("/User")
public class UserService {
	User userObject = new User();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String user() {

		return userObject.readuser(); 
	}

   // Add User//
       @POST
       @Path("/")
       @Consumes(MediaType.APPLICATION_JSON) 
       @Produces(MediaType.TEXT_PLAIN)
        public String insertuser(String UserData) {
	// Convert the input string to a JSON object
	
	JsonObject userDetailsObj = new JsonParser().parse(UserData).getAsJsonObject();

	
	String username = userDetailsObj.get("username").getAsString();
	String address = userDetailsObj.get("address").getAsString();
	String age = userDetailsObj.get("age").getAsString();
	String email = userDetailsObj.get("email").getAsString();
	String sex = userDetailsObj.get("sex").getAsString();
	
	
	// Read the values from the JSON object

	String output = userObject.insertUser(username, address, age, email, sex);
		
		return output;
	

}

//Update User

         @PUT
         @Path("/")
         @Consumes(MediaType.APPLICATION_JSON)
         @Produces(MediaType.TEXT_PLAIN)
            public String updateUser(String UserData) {
         //  Convert the input string to a JSON object
       	JsonObject userDetailsObj = new JsonParser().parse(UserData).getAsJsonObject();

       	String userId = userDetailsObj.get("userId").getAsString();
       	String username = userDetailsObj.get("username").getAsString();
       	String address = userDetailsObj.get("address").getAsString();
       	String age = userDetailsObj.get("age").getAsString();
       	String email = userDetailsObj.get("email").getAsString();
       	String sex = userDetailsObj.get("sex").getAsString();
	
	
	String output =userObject.updateUser(userId, username, address, age, sex, email) ;
	return output;
}


//Delete user

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)

public String deleteUser(String userData) {
	//convert the input string to an XML document
	Document doc = Jsoup.parse(userData,"",Parser.xmlParser());
	
	//Read the value from the element <Project>
	String userId = doc.select("userId").text();
	
	String output = userObject.removeuser(userId);
	
	return output;
}
 }       	 
        	

