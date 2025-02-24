import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTest extends Parameters {

	@BeforeTest
	public void setup() {
		GeneralSetup();
	}

	@Test(priority = 1, enabled = false)
	public void signUpTest() {
		WebElement createAccountPage = driver.findElement(By.linkText("Create an Account"));
		createAccountPage.click();

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		firstNameInput.sendKeys(firstName[randomFirstName]);

		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		lastNameInput.sendKeys(lastName[randomLastName]);

		WebElement emailInput = driver.findElement(By.id("email_address"));
		emailInput.sendKeys(email);

		WebElement passwordInput = driver.findElement(By.id("password"));
		passwordInput.sendKeys(password);

		WebElement confirmPasswordInput = driver.findElement(By.id("password-confirmation"));
		confirmPasswordInput.sendKeys(password);

		WebElement createAccountBtn = driver.findElement(By.cssSelector("button[title='Create an Account']"));
		createAccountBtn.click();
	}

	@Test(priority = 2, enabled = false)
	public void logoutTest() {
		driver.get("https://magento.softwaretestingboard.com/customer/account/logout/");
	}

	@Test(priority = 3, enabled = false)
	public void loginTest() throws InterruptedException {
		Thread.sleep(10);
		WebElement signInPage = driver.findElement(By.cssSelector("div[class='panel header'] li[data-label='or'] a"));
		signInPage.click();

		WebElement emailInput = driver.findElement(By.id("email"));
		emailInput.sendKeys(email);

		WebElement passwordInput = driver.findElement(By.id("pass"));
		passwordInput.sendKeys(password);

		WebElement signInBtn = driver.findElement(By.id("send2"));
		signInBtn.click();
	}

	@Test(priority = 4, enabled = false)
	public void womenItems() throws InterruptedException {
		driver.get("https://magento.softwaretestingboard.com/women/tops-women/tees-women.html");

		for (int i = 0; i < 3; i++) {
			WebElement itemsContainor = driver.findElement(By.cssSelector(".products.list.items.product-items"));
			List<WebElement> itemList = itemsContainor.findElements(By.tagName("li"));
			itemList.get(i).findElement(By.className("product-item-info")).click();
			List<WebElement> sizeList = driver.findElements(By.cssSelector(".swatch-option.text"));
			List<WebElement> colorList = driver.findElements(By.cssSelector(".swatch-option.color"));
			int randomColor1 = rand.nextInt(colorList.size());
			int randomSize1 = rand.nextInt(sizeList.size());

			sizeList.get(randomSize1).click();
			colorList.get(randomColor1).click();

			int randomQuantity = rand.nextInt(9) + 1;
			globalQuantityCount += randomQuantity;
			WebElement quantityInput = driver.findElement(By.id("qty"));
			quantityInput.clear();
			quantityInput.sendKeys(toString().valueOf(randomQuantity));
			WebElement productContainor = driver.findElement(By.className("product-info-main"));
			driver.findElement(By.id("product-addtocart-button")).click();
			Thread.sleep(3000);

			driver.get("https://magento.softwaretestingboard.com/women/tops-women/tees-women.html");
		}

		Thread.sleep(3000);
		int totalActualCount = Integer.parseInt(driver.findElement(By.className("counter-number")).getText());

		WebElement cartContainor = driver.findElement(By.id("ui-id-1"));
		Assert.assertEquals(totalActualCount, globalQuantityCount);

	}

	@Test(priority = 5, enabled = false)
	public void BagSection() throws InterruptedException {
		driver.get("https://magento.softwaretestingboard.com/gear/bags.html");
		WebElement bagsContainor = driver.findElement(By.cssSelector(".products.list.items.product-items"));
		List<WebElement> bagList = bagsContainor.findElements(By.tagName("li"));
		for (int i = 0; i < bagList.size(); i += 2) {
			bagsContainor = driver.findElement(By.cssSelector(".products.list.items.product-items"));
			bagList = bagsContainor.findElements(By.tagName("li"));
			bagList.get(i).click();
			globalQuantityCount += 1;
			WebElement quantityInput = driver.findElement(By.id("qty"));
			quantityInput.clear();
			quantityInput.sendKeys("1");
			driver.findElement(By.id("product-addtocart-button")).click();
			Thread.sleep(2000);
			driver.navigate().back();
		}

		int totalActualCount = Integer.parseInt(driver.findElement(By.className("counter-number")).getText());

		Assert.assertEquals(totalActualCount, globalQuantityCount);

	}

	@Test(priority = 6, enabled = false)
	public void checkout() throws InterruptedException {
		WebElement checkoutTap = driver.findElement(By.cssSelector(".action.showcart"));
		checkoutTap.click();
		WebElement checkoutButton = driver.findElement(By.id("top-cart-btn-checkout"));
		checkoutButton.click();

		WebElement addressInput = driver.findElement(By.xpath("//input[@name='street[0]']"));
		WebElement cityInput = driver.findElement(By.xpath("//input[@name='city']"));
		WebElement stateInput = driver.findElement(By.xpath("//select[@name='region_id']"));
		WebElement ZIPInput = driver.findElement(By.xpath("//input[@name='postcode']"));
		WebElement countryInput = driver.findElement(By.xpath("//select[@name='country_id']"));
		WebElement phoneInput = driver.findElement(By.xpath("//input[@name='telephone']"));
		WebElement shippingMethod = driver.findElement(By.tagName("tbody"));
		List<WebElement> methods = shippingMethod.findElements(By.tagName("tr"));
		WebElement nextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));

		Select selectState = new Select(stateInput);
		Select selectCountry = new Select(countryInput);
		addressInput.sendKeys("aaa");
		cityInput.sendKeys("ccc");
		selectState.selectByIndex(1);
		ZIPInput.sendKeys("12345");
		selectCountry.selectByIndex(1);
		phoneInput.sendKeys("12345678");
		methods.get(1).click();
		nextButton.click();
		Thread.sleep(3000);
		WebElement placeOrderButton = driver.findElement(By.cssSelector(".action.primary.checkout"));
		placeOrderButton.click();
		Thread.sleep(2000);
		boolean confirmPurchaseMsg = driver.findElement(By.className("checkout-success")).getText()
				.contains("Your order number");

		Assert.assertEquals(confirmPurchaseMsg, true);

	}

	@Test(priority = 7, enabled = false)
	public void subscribeTest() {
		String originalWindowHandle = driver.getWindowHandle();

		WebElement subscribePage = driver.findElement(By.linkText("Subscribe"));
		subscribePage.click();

		Set<String> windowHandles = driver.getWindowHandles();

		windowHandles.remove(originalWindowHandle);
		String newWindowHandle = windowHandles.iterator().next();

		driver.switchTo().window(newWindowHandle);

		WebElement emailAddress = driver.findElement(By.id("mce-EMAIL"));
		emailAddress.sendKeys(email);

		WebElement subscribeBtn = driver.findElement(By.id("mc-embedded-subscribe"));
		subscribeBtn.click();

		driver.switchTo().window(originalWindowHandle);
	}

	@Test(priority = 8)
	public void gearSection() {
		driver.get("https://magento.softwaretestingboard.com/gear/fitness-equipment.html");
		int randomItems = rand.nextInt(3);

		WebElement productItems = driver.findElement(By.cssSelector(".products.list.items.product-items"));
		List<WebElement> productList = productItems.findElements(By.tagName("li"));

		for (int i = 0; i < 3; i++) {
			productItems = driver.findElement(By.cssSelector(".products.list.items.product-items"));
			productList = productItems.findElements(By.tagName("li"));
			productList.get(i).click();
			try {
				WebElement addToCartButton = driver.findElement(By.id("product-addtocart-button"));
				if (addToCartButton.isDisplayed()) {
					List<WebElement> quantity = driver.findElements(By.xpath("//input[@title='Qty']"));
					quantity.get(0).clear();
					quantity.get(0).sendKeys("1");
					addToCartButton.click();
					Thread.sleep(1000);
					driver.navigate().back();
					continue;
				}
				WebElement customAddToCartButton = driver.findElement(By.cssSelector("#bundle-slide"));
				if (customAddToCartButton.isDisplayed()) {
					customAddToCartButton.click();
					Thread.sleep(1000);
					WebElement addButton = driver.findElement(By.id("product-addtocart-button"));
					addButton.click();
					Thread.sleep(1000);
					driver.navigate().back();
					continue;
				}

			} catch (Exception e) {

			}

		}
		

		WebElement sortBtn=driver.findElement(By.id("sorter"));
		Select sortSelect=new Select(sortBtn);
		sortSelect.selectByValue("price");
	
		WebElement productContainor=driver.findElement(By.cssSelector(".products.list.items.product-items"));
		List<WebElement> allProducts=productContainor.findElements(By.tagName("li"));
		WebElement firstProduct=allProducts.get(0);
		double firstPrice=Double.parseDouble(firstProduct.findElement(By.className("price")).getText().substring(1));
		WebElement lastProduct=allProducts.get(allProducts.size()-1);
		double lastPrice=Double.parseDouble(lastProduct.findElement(By.className("price")).getText().substring(1));

		Assert.assertTrue(firstPrice<=lastPrice);
	}

}
