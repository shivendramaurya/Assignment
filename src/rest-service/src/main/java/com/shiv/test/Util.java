package com.shiv.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shiv.test.model.TrieNode;

/**
 * 
 * @author Shiv
 * Utility class
 */

public class Util {
	
	
	private static final Logger log = LoggerFactory.getLogger(Util.class);

	
	/**
	 * It will return the file name whose size is minimum in the given set
	 * 
	 * @param filePaths
	 * @return file name of from the set whose size  is minimum
	 * @throws IOException
	 */
	public static String getSmallestFileBySize(Set<String> filePaths)  {
		
		long minFileSize = Long.MAX_VALUE;
		String minFileName = null;
		
		for (String file : filePaths) {
			Path path = Paths.get(file);
			long fileSize =0;
			try {
				fileSize = Files.size(path);
			} catch (IOException e) {
				log.error("Unable to get the size of the file :"+file);
				e.printStackTrace();
				return null;
			}
			if (minFileSize > fileSize) {
				minFileSize = fileSize;
				minFileName = file;
			}
			
		}
		
		return minFileName;
		
	}
	
	/**
	 * Build the Tire datastructure from a given file
	 * @param fileName
	 * @return
	 */
	public static TrieNode buildTrieFromFile(String fileName) {
		
		TrieNode root = new TrieNode();
		BufferedReader reader =null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line;
			root = new TrieNode();
			while ((line = reader.readLine()) != null) {
				String[] words = line.split(" ");
				for (int i = 0; i < words.length; i++) {
					root.insertStringInTrie(words[i].trim());
				}
			}
		} catch (IOException e) {
			log.error("Unable to read the file :"+fileName);
			e.printStackTrace();
			return null;

		} finally {

			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					log.error("Unable to close the BufferedReader");
					e.printStackTrace();
				}

		}
		return root;
		
	}

}
