package com;

import model.Order;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/orders")
public class OrderService 
{
	Order orderObj = new Order();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOrders() {
		
		return orderObj.readOrders();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOrder(
	 @FormParam("name") String name,
	 @FormParam("email") String email,
	 @FormParam("address") String address,
	 @FormParam("phone_num") String phone_num,
	 @FormParam("Item_id") String Item_id)
	{
	 String output = orderObj.insertOrder(name, email, address,phone_num,Item_id);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
		
	//Convert the input string to a JSON object
	 JsonObject orderObject = new JsonParser().parse(itemData).getAsJsonObject();
	 
	//Read the values from the JSON object
	 String order_id = orderObject.get("order_id").getAsString();
	 String name = orderObject.get("name").getAsString();
	 String email = orderObject.get("email").getAsString();
	 String address = orderObject.get("address").getAsString();
	 String phone_num = orderObject.get("phone_num").getAsString();
	 String Item_id = orderObject.get("Item_id").getAsString();
	 
	 String output = orderObj.updateItem(order_id,name, email, address, phone_num, Item_id);
	 
	return output;
	
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String order_id = doc.select("order_id").text();
	 String output = orderObj.deleteItem(order_id);
	return output;
	}

}

