nerd4java
=========

It is a java library which provides a programmable interface to NERD http://nerd.eurecom.fr.

#### Library in action

Include the library in your project and then: 
  
    String text = read_your_text_file();
    NERD nerd = new NERD(YOUR_API_KEY);
    String json = nerd.annotateJSON(ExtractorType.NERDML, 
                                    DocumentType.PLAINTEXT,
                                    text);
    System.out.println(json);
