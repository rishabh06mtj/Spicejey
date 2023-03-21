package com.spicejet.genericUtility;

public enum PropertyFileKeys {
	URL("url"),TIMEOUT("time"),BROWSER("browser"),LIMIT("limit"),CUSTOMWAIT("customWait");

	private String keys;

	//setter
	private PropertyFileKeys(String keys)
	{
		this.keys=keys;
	}

	//getter
	public String convertToString()
	{
		return keys.toString();
	}

}
