package com.shiv.test.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shiv.test.Util;
import com.shiv.test.model.TrieNode;

@Service
public class CommanWordsInFiles implements ICommanWordService{
	
	
	private static final Logger log = LoggerFactory.getLogger(CommanWordsInFiles.class);


	@Override
	public Set<String> getCommanWords(Set<String> filePaths) {
		/**
		 * Get the smallest file from the input parameter so that it requires minimum space to create the trie
		 */
		String fileNameWithMinSize = Util.getSmallestFileBySize(filePaths); 
		
		if(fileNameWithMinSize ==null || "".equals(fileNameWithMinSize)) {
			log.error("Unable to get the minimum size file. Returning empty set");
			return new HashSet<>();
		}
		
		TrieNode root = Util.buildTrieFromFile(fileNameWithMinSize);
		if(root == null) {
			log.error("Unable to build the Trie. Returning empty set");
			return new HashSet<>();
		}
		
		markCommanWords(filePaths, fileNameWithMinSize, root);
		
		return getWords(root, filePaths.size());
	}
	
	/**
	 * It will update the counter of each word in trie which will be help us to find whether the word is present in each file or not
	 * @param filePaths
	 * @param fileNameWithMinSize
	 * @param root
	 */
	
	private void markCommanWords(Set<String> filePaths, String fileNameWithMinSize,  TrieNode root) {
		int countPresence =1;
		for (String filePath : filePaths) {
			if(filePath.equals(fileNameWithMinSize))
				continue;
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(filePath));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] words = line.split(" ");
					for (String word : words) {
						word = word.trim();
						TrieNode trieNode = root.searchWord(word);
						if (trieNode != null && trieNode.isLeaf() && trieNode.getCount() == countPresence ) {
							trieNode.setCount(trieNode.getCount()+1);
						}
					}
				}
				
			}catch(IOException ex) {
				log.error("Unable to read the file :"+filePath);
				ex.printStackTrace();
			}finally {
				if (reader != null)
					try {
						reader.close();
					} catch (IOException e) {
						log.error("Unable to close the BufferedReader ");
						e.printStackTrace();
					}
			}
			countPresence ++;
		}
		
	}
	
	private Set<String> getWords(TrieNode root, int count){
		Set<String> set = new HashSet<>();
		getWordsFromTrie(root,"", count,set);
		
		return set;
		
		
	}

	private void getWordsFromTrie(TrieNode node, String prefix, int count, Set<String> set) {
		
		if (node.isLeaf() && node.getCount() == count) {
			set.add(prefix);
		}
		for (char i = 'a', j = 'A'; i <= 'z' && j <= 'Z'; i++, j++) {
			TrieNode nextNode = node.getChildren()[i - 'a'];
			if (nextNode == null)
				nextNode = node.getChildren()[j - 'A'];
			if (nextNode != null) {
				getWordsFromTrie(nextNode, prefix + i, count,set);
			}
		}
	}
}
