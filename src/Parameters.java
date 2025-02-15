import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {
	WebDriver driver=new ChromeDriver();
	String URL="https://magento.softwaretestingboard.com/";
	String[] firstName = {
            "Alice", "Bob", "Charlie", "David", "Emma",
            "Frank", "Grace", "Hannah", "Ivy", "Jack",
            "Katie", "Liam", "Mia", "Nathan", "Olivia"};
           
	String[] lastName = {
            "Smith", "Johnson", "Brown", "Taylor", "Anderson",
            "Thomas", "Jackson", "White", "Harris", "Martin",
            "Lee", "Walker", "Hall", "Allen", "Young",
            "King", "Wright", "Scott", "Green", "Baker"
        };
    Random rand = new Random();
	int randomFirstName=rand.nextInt(firstName.length);	
	int randomLastName=rand.nextInt(lastName.length);
	String email=firstName[randomFirstName]+"_"+lastName[randomLastName]+"@gmail.com";
	String password=email+"123456789";
	int globalQuantityCount = 0;
	int globalPrice=0;
	public void GeneralSetup() {
		driver.manage().window().maximize();
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

}
