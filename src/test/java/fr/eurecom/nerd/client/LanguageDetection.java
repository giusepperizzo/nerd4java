package fr.eurecom.nerd.client;

import fr.eurecom.nerd.client.schema.Document;
import fr.eurecom.nerd.client.type.DocumentType;
import junit.framework.TestCase;

public class LanguageDetection extends TestCase {
	public void test1() 
	{
		NERD nerd = new NERD("TEST");
		Document d = nerd.getDocument(DocumentType.PLAINTEXT, "Barack Obama is the President of US");
		
		assertEquals("en", d.getLanguage());
	}
}
