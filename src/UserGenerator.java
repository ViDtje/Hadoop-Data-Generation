
public class UserGenerator {
	int mapperId;
	
	public UserGenerator(int mapperId) { this.mapperId = mapperId; } 
	
	public String generate(int recordNr) {
		return "" + (recordNr * recordNr); 
	}
}
