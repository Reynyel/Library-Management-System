import java.util.HashMap;

public class DeweyMap {
	private HashMap<String, Double> booksDeweyMap;
	
	public DeweyMap() {
		booksDeweyMap = new HashMap<>();
		initializeDeweyMap();
	}
	
	public Double getDeweyForSubject(String subject) {
		if(booksDeweyMap.containsKey(subject)) {
			return booksDeweyMap.get(subject);			
		}
		else {
			//if there is no subject found
			return 999.0;
		}
		
	}
	
	private void initializeDeweyMap() {
		booksDeweyMap.put("General Information", 0.0);
		booksDeweyMap.put("Philosophy & Psychology", 100.0);
		booksDeweyMap.put("Religion", 200.0);
		booksDeweyMap.put("Social Sciences", 300.0);
		booksDeweyMap.put("Language", 400.0);
		booksDeweyMap.put("Science", 500.0);
		booksDeweyMap.put("Technology", 600.0);
		booksDeweyMap.put("Arts & Recreation", 700.0);
		booksDeweyMap.put("Literature", 800.0);
		booksDeweyMap.put("History & Geography", 900.0);
		
	}

}
