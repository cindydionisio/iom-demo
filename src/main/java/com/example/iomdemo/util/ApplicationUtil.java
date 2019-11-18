package com.example.iomdemo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationUtil {

	private ApplicationUtil() {}

	public static File getUserCredFile(String path) {
		File file = new File(path + "\\users.csv");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		return file;
	}
	public static Map<String, String> getUsernameAndPasswords(String path) {
		File file = getUserCredFile(path);
		StringBuilder contentBuilder = new StringBuilder();
		Map<String, String> userPasswordPairs = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine);
            }
            String content = contentBuilder.toString();
            if(content != null && content.length() > 0) {
            	 userPasswordPairs = Arrays.stream((content.substring(0, content.length() - 1)).split(","))
                         .map(s -> s.split(":"))
                         .collect(Collectors.toMap(s -> s[0], s -> s[1]));
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
		return userPasswordPairs;
	}
}
