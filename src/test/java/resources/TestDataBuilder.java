package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleplaceAPI;
import pojo.Location;

public class TestDataBuilder {

	public GoogleplaceAPI AddPlacePayload(String name, String address, String language)
	{
	
	GoogleplaceAPI ap=new GoogleplaceAPI();
	Location loc=new Location();
	
	loc.setLng(33.427362);
	loc.setLat(-38.383494);
	
	List<String> typeslist= new ArrayList<String>();
	typeslist.add("shoe park");
	typeslist.add("shop");
	
	ap.setAccuracy(50);
	ap.setAddress(address);
	ap.setLanguage(language);
	ap.setName(name);
	ap.setPhone_number("(+91) 983 893 3937");
	ap.setWebsite("http://google.com");
	
	ap.setTypes(typeslist);		
	ap.setLocation(loc);
	return ap;
	
	
	}
	
	public String DeletePlacePayload(String place_id)
	{
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}\r\n" + 
				"";
		
	}
}
