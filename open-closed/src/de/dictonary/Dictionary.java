package de.dictonary;

import java.awt.HeadlessException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;



public class Dictionary {
	
	
	private static Dictionary instance = new Dictionary();
	
	private Map<String, String> wordpairs = new HashMap<>();
	
	
	
	private Dictionary() {
		
	}
	
	
	
	public static Dictionary getInstance() {
		return instance;
	}



	public void insert(String deutsch, String english) {
		wordpairs.put(deutsch, english);
	}
	
	public void translate(String deutsch) {
		System.out.println(wordpairs.getOrDefault(deutsch, "Keine Übersetzung gefunden"));
	}


	public void list() {
		wordpairs.forEach((k,v)->System.out.printf("%s = %s\n",k,v));
		
	}



	public DictionaryMemento getMemento() {
		return new MyMemento(wordpairs);
	}



	public void setMemento(DictionaryMemento memento) {
		wordpairs = ((MyMemento) memento).getWordpairs();
	}

	private static class MyMemento implements DictionaryMemento {
		
		final Map<String , String> wordpairs;

		public MyMemento(Map<String, String> wordpairs) {
			try {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
				objectOutputStream.writeObject(wordpairs); 
				objectOutputStream.close();
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
				ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
				this.wordpairs = (Map<String , String>) objectInputStream.readObject();
				objectInputStream.close();
			} catch (Exception e) {
				throw new RuntimeException("upps", e);
			} 
			
		}

		public Map<String, String> getWordpairs() {
			try {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
				objectOutputStream.writeObject(this.wordpairs); 
				objectOutputStream.close();
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
				ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
				Map<String , String> result = (Map<String , String>) objectInputStream.readObject();
				objectInputStream.close();
				return result;
			} catch (Exception e) {
				throw new RuntimeException("upps", e);
			} 
			
		}
		
		
		
	}
}
