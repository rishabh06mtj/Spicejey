package com.spicejet.genericUtility;

public enum SheetName {
	FLIGHTS("Flights");
	private String sheetName;
	private SheetName(String sheetName)
	{
		this.sheetName=sheetName;
	}
	public String convertToString()
	{
		return sheetName.toString();
	}
}
