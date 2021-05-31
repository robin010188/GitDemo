package resources;

public enum ApiResources {

	AddPlace("/maps/api/place/add/json"),
	DeletePlace("/maps/api/place/delete/json"),
	GetPlace("/maps/api/place/get/json");
	
	private String resource;
	
	ApiResources (String resource)
	{
		this.resource=resource;
	}
	
	
	public String getResource()
	{
		return resource;
	}
	
	
	
}
