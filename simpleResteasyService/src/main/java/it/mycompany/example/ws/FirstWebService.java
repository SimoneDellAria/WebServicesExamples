package it.mycompany.example.ws;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import it.mycompany.example.res.WebhookResponse;
import it.mycompany.example.res.WebhookTextMessage;

@Path("/example")
public class FirstWebService {

	/**
	 * This GET Service returns a welcome message
	 * 
	 * @return A simple text message
	 */
	@GET
	@Path("/hello")
	public String sayHello() {
		return "Hello From Your First RestEasy Service! \nCiao dal tuo primo servizio RestEasy!";
	}

	/**
	 * This GET Service returns a welcome message taking as input param the user
	 * text
	 * 
	 * @param name
	 * @return A simple text message including the name param
	 */
	@GET
	@Path("/hello/{name}")
	public String sayHelloWithName(@PathParam("name") String name) {
		return "Hello " + name + " From Your First RestEasy Service! \n Ciao " + name
				+ " dal tuo primo servizio RestEasy!";
	}

	/**
	 * Test method for webhook in DialogFlow with a Json Object
	 * 
	 * @return Json Object
	 */
	@POST
	@Path("/webhook")
	@Produces(MediaType.APPLICATION_JSON)
	public String webhookTest() {
		WebhookResponse obj = new WebhookResponse();
		obj.setSpeech("Here is a message from REST Service");
		obj.setDisplayText(obj.getSpeech());
		obj.setSource("something");
		ObjectMapper objMap = new ObjectMapper();
		String jsonObj = "";
		try {
			jsonObj = objMap.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}

	/**
	 * This method returns a simple text message as a response to the user question
	 * 
	 * @return Json Object
	 */
	@POST
	@Path("/webhookTextMessage")
	@Produces(MediaType.APPLICATION_JSON)
	public String webhookTestSimpleText() {
		WebhookTextMessage obj = new WebhookTextMessage();
		obj.setFulfillmentText("Hi! This message comes directly from my REST Service!");
		ObjectMapper objMap = new ObjectMapper();
		String jsonObj = "";
		try {
			jsonObj = objMap.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonObj;
	}

	/**
	 * This method returns a CARD Message as a response to the user question
	 * 
	 * @return Json Object
	 */
	@POST
	@Path("/webhookCardMessage")
	@Produces(MediaType.APPLICATION_JSON)
	public String webhookCardMessage() {

		JsonNodeFactory nodeFactory = JsonNodeFactory.instance;

		ObjectNode pushContent = nodeFactory.objectNode();
		
		ObjectNode card = nodeFactory.objectNode();
		card.put("title", "My first Card");
		card.put("subtitle","This is the first Card test");
		card.put("imageUri", "https://images.wallpaperscraft.com/image/paint_water_liquid_85058_1920x1080.jpg");
		
		
		ArrayNode fulfillmentText = nodeFactory.arrayNode();
		ArrayNode buttons = nodeFactory.arrayNode();
		ObjectNode buttonsObj = nodeFactory.objectNode();
		buttonsObj.put("text", "Text OF The Button");
		buttonsObj.put("postback", "http://www.google.com");
		buttons.add(buttonsObj);
		card.set("buttons", buttons);
		
		ObjectNode fulfillmentTextObj = nodeFactory.objectNode();
		fulfillmentTextObj.set("card", card);
		fulfillmentText.add(fulfillmentTextObj);
		pushContent.set("fulfillmentMessages", fulfillmentText);
		
		return pushContent.toString();

	}

}
