package fr.eurecom.nerd.client;

import fr.eurecom.nerd.client.schema.Document;
import fr.eurecom.nerd.client.type.DocumentType;
import junit.framework.TestCase;

public class LanguageDetection extends TestCase {
	public void test1() 
	{
		//add your NERD API key
		String apiKey = "";
		
		NERD nerd = new NERD(apiKey);
		Document d = nerd.getDocument(DocumentType.PLAINTEXT, "Barack Obama is the President of US");
		assertEquals("en", d.getLanguage());
	}
}
