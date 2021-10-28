package by.javacourse.task3.entity;

public enum Pack {
	
	PILL,
	CAPSULE,
	POWDER,
	OINTMENT,
	GEL,
	SOLUTION_FOR_INJECTION,
	MIXTURE,
	AEROSOL,
	DROP;

	private static final char UNDERSCORE = '_';
    private static final char HYPHEN = '-';
    
    public static Pack valueOfXmlContent(String content) {
    	return Pack.valueOf(content.toUpperCase().replace(HYPHEN, UNDERSCORE));
    }
	
}
