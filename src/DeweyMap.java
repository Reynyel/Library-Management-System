import java.util.HashMap;

public class DeweyMap {
	private HashMap<String, String> booksDeweyMap;
	
	public DeweyMap() {
		booksDeweyMap = new HashMap<>();
		initializeDeweyMap();
	}
	
	public String getDeweyForSubject(String subject) {
        if (booksDeweyMap.containsKey(subject)) {
            // Convert double value to String before returning
            return String.valueOf(booksDeweyMap.get(subject));
        } else {
            // If there is no subject found, return a default value as a String
            return "999.0";
        }
    }
	
	private void initializeDeweyMap() {
		booksDeweyMap.put("General Information", "0.0");
		booksDeweyMap.put("Philosophy & Psychology", "100.0");
		booksDeweyMap.put("Religion", "200.0");
		booksDeweyMap.put("Social Sciences", "300.0");
		booksDeweyMap.put("Language", "400.0");
		booksDeweyMap.put("Science", "500.0");
		booksDeweyMap.put("Technology", "600.0");
		booksDeweyMap.put("Arts & Recreation", "700.0");
		booksDeweyMap.put("Literature", "800.0");
		booksDeweyMap.put("History & Geography", "900.0");
		booksDeweyMap.put("Others", "N/A");
	}

}
