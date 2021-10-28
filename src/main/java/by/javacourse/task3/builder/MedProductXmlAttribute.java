package by.javacourse.task3.builder;

public enum MedProductXmlAttribute {
	
	ID,
	OUT_OF_PRODACTION;
	
	private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';
    
    @Override
    public String toString() {
    	return this.name().toLowerCase().replace(UNDERSCORE, HYPHEN);
    }
    
}
