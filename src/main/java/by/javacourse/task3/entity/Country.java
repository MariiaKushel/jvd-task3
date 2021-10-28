package by.javacourse.task3.entity;

public enum Country {
	
	GERMANY, 
	BELARUS, 
	BELGIUM, 
	RUSSIA, 
	CZECH_REPUBLIC, 
	INDIA, 
	ISRAEL, 
	POLAND, 
	CHINA;
	
	private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';
    
    public static Country valueOfXmlContent(String content) {
    	return Country.valueOf(content.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
	
}
