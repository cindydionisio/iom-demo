package com.example.iomdemo.util;

import java.util.HashMap;
import java.util.Map;

public enum FileExtensionEnum {
	csv("text/csv"),
	txt("text/plain"),
	xml("text/xml"),
	json("application/json"),
	html("text/html");

	private String contentHeader;
	private static final Map<String, FileExtensionEnum> valueKey = new HashMap<>();

	static
    {
        for(FileExtensionEnum fe : FileExtensionEnum.values())
        {
        	valueKey.put(fe.geContentHeader(), fe);
        }
    }

	FileExtensionEnum(String ext) {
		this.contentHeader = ext;
	}

	 public String geContentHeader() {
	    return this.contentHeader;
	 }

	 public static FileExtensionEnum get(String ext)
	    {
	        return valueKey.get(ext);
	    }
}
