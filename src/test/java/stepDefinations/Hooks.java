package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	
		@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
			StepDefination sd=new StepDefination();
		if(StepDefination.place_id==null)
		{
		
		sd.add_place_payload("Rahul", "Memphis", "French");
		sd.user_calls_api_with_http_request("AddPlace", "POST");
		sd.verify_place_id_created_maps_to_using_api("Rahul", "GetPlace");
		}
		
	}
	
	

}
