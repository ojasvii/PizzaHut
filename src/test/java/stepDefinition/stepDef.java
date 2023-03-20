package stepDefinition;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import PageObjectModel.POM;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class stepDef {

	private ChromeDriver driver;
	private POM page = new POM();
	private WebElement CloseButton;
	private float TotalBasketPrice;
	private ExtentSparkReporter spark;
	private ExtentReports extent;
	private ExtentTest logger;

	@Before
	public void begin() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Given("User launch Pizzahut application with {string}")
	public void user_launch_pizzahut_application_with(String string) throws InterruptedException {
		driver.get(string);
		Thread.sleep(2000);
	}

	@When("User wait for auto location black pop up screen")
	public void user_wait_for_auto_location_black_pop_up_screen() {
	//CloseButton = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(page.BlackScreenCloseBTN()));
	}

	@Then("User close the pop up screen")
	public void user_close_the_pop_up_screen() {
		
//		 try { CloseButton.click();
//		 //logger.pass("User wait for auto location black pop up screen , closed it");
//		 } catch(Exception e){ //logger.fail("black pop up screen not displayed");  }
//		 }
//		 
	}

	@Then("User see pop up for delivery asking for enter location")
	public void user_see_pop_up_for_delivery_asking_for_enter_location() {
		try {
			Assert.assertTrue(driver.findElement(page.DeliveryLocationTextBox()).isDisplayed());
			// logger.pass("Enter Location For Delivery Text box is present");
		} catch (Exception e) {
			// logger.fail("Enter Location For Delivery Text box is missing");
		}
	}

	@Then("User type address as {string}")
	public void user_type_address_as(String string) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(page.DeliveryLocationTextBox()).sendKeys(string);
		Thread.sleep(3000);
	}

	@Then("User select first auto populate drop down option")
	public void user_select_first_auto_populate_drop_down_option() {
		try {
			Thread.sleep(5000);
			driver.findElement(page.DeliveryLocationText()).click();
			Thread.sleep(5000);
			driver.findElement(page.DeliveryLocationSelect()).click();
			Thread.sleep(5000);
			// try{
			// driver.findElement(page.StartYourOrderWithTime()).click();}catch(Exception
			// e){}
			// logger.pass("Delivery Location Added Successfully");
			// Thread.sleep(10000);
		} catch (Exception e) {
			// logger.fail("Delivery Location not Added.... Issue while selecting");
		}
	}

	@When("User navigate to deails page")
	public void user_navigate_to_deails_page() {
		try {
			Assert.assertTrue(driver.findElement(page.DealsBTN()).isDisplayed());
			Assert.assertTrue(driver.getCurrentUrl().contains("deals"));
			// logger.pass("User landed to Deals page successfully");
		} catch (Exception e) {
			// logger.fail("User is not into Deals Page");
		}
	}

	@Then("User validate vegetarian radio button flag is off")
	public void user_validate_vegetarian_radio_button_flag_is_off() {
		try {
			Assert.assertTrue(driver.findElement(page.VegetarianSwitchBTN()).isDisplayed());
			// logger.pass("vegetarian radio button flag is off as expected");
		} catch (Exception e) {
			// logger.fail("vegetarian radio button flag is On");
		}
	}

	@Then("User clicks on Pizzas menu bar option")
	public void user_clicks_on_pizzas_menu_bar_option() {
		try {
			driver.findElement(page.PizzaMenu()).click();
			Thread.sleep(5000);
			// logger.pass("User navigated to Pizza menu page");
		} catch (Exception e) {
			// logger.fail("Pizza menu is not available");
		}
	}

	@When("User select add button of any pizza from Recommended")
	public void user_select_add_button_of_any_pizza_from_recommended() {
		try {
			driver.findElement(page.margherita_Pizza()).click();
			Thread.sleep(5000);
			// logger.pass("User added Pizza to Your Basket");
		} catch (Exception e) {
			// logger.fail("Pizza not added to Your Basket");
		}
	}

	@Then("User see that the pizza is getting added under Your Basket")
	public void user_see_that_the_pizza_is_getting_added_under_your_basket() {
		/*
		 * try { driver.findElement(page.AddedPizzaAtBasket()).isDisplayed();
		 * logger.pass("Added Pizza Successfully displayed At Your Basket"); }
		 * catch(Exception e){ logger.fail("Added Pizza not displayed At Your Basket");
		 * }
		 */
	}

	@Then("User validate pizza price plus Tax is checkout price")
	public void user_validate_pizza_price_plus_tax_is_checkout_price() {
		float onlyPizzaPrice = Float.valueOf(driver.findElement(page.PizzaPriceAmount()).getText().replaceAll("₹", ""));
		// float restaurentCharges =Float.valueOf(driver.findElement(CUBR_Obj.RestaurentCharges()).getText().replaceAll("₹", ""));
		float TAXPrice = Float.valueOf(driver.findElement(page.TaxPriceAmount()).getText().replaceAll("₹", ""));
		TotalBasketPrice = Float.valueOf(driver.findElement(page.TotalPriceAmount()).getText().replaceAll("₹", ""));
		// if(onlyPizzaPrice+restaurentCharges+TAXPrice==TotalBasketPrice)
		if (onlyPizzaPrice + TAXPrice == TotalBasketPrice) {
			// logger.pass("Pizza Price+ RestaurentCharges + Tax = Total Basket Item Price
			// as Expected");
		} else {
			// logger.fail("Pizza Price + RestaurentCharges + Tax != Total Basket Item
			// Price..........Fail");
		}
	}

	@Then("User validate checkout button contains Item count")
	public void user_validate_checkout_button_contains_item_count() {
		try {
			driver.findElement(page.ItemOptionUnderCheckout()).isDisplayed();
			// logger.pass("checkout button contains Item count as expected");
		} catch (Exception e) {
			// logger.fail("checkout button not contains Item count");
		}
	}

	@Then("User validate checkout button contains total price count")
	public void user_validate_checkout_button_contains_total_price_count() {
		try {
			driver.findElement(page.ItemOptionUnderCheckout()).isDisplayed();
			// logger.pass("checkout button contains Item count as expected");
		} catch (Exception e) {
			// logger.fail("checkout button not contains Item count");
		}
	}

	@Then("User clicks on Drinks option")
	public void user_clicks_on_drinks_option() {
		driver.findElement(page.DrinksMenu()).click();
	}

	@Then("User select Pepsi option to add into the Basket")
	public void user_select_pepsi_option_to_add_into_the_basket() {
		try {
			Thread.sleep(5000);
			driver.findElement(page.Pepsi_Drinks()).click();
			Thread.sleep(5000);
			// logger.pass("User added Pepsi to Your Basket");
		} catch (Exception e) {
			// logger.fail("Pepsi not added to Your Basket");
		}
	}

	@Then("User see {int} items are showing under checkout button")
	public void user_see_items_are_showing_under_checkout_button(Integer int1) {
	Assert.assertTrue(driver.findElement(page.ItemOptionUnderCheckout()).getText().contains(String.valueOf(int1)));
	 //logger.pass("After adding Pepsi , total Item count is 2");
	}

	@Then("User see total price is now more than before")
	public void user_see_total_price_is_now_more_than_before() {
		float PriceBeforePepsi = TotalBasketPrice;
		TotalBasketPrice = Float.valueOf(driver.findElement(page.TotalPriceAmount()).getText().replaceAll("₹", ""));
		if (TotalBasketPrice > PriceBeforePepsi) {
			// logger.pass("After adding Pepsi , total price got increased as
			// ==>"+TotalBasketPrice);
		} else {
			// logger.fail("After adding Pepsi , total price did not increase");
		}
	}

	@Then("User remove the Pizza item from Basket")
	public void user_remove_the_pizza_item_from_basket() {
		try {
			driver.findElement(page.RemovePizzaBTN()).click();
			Thread.sleep(3000);
			// logger.pass("User Removed Pizza from the Basket");
		} catch (Exception e) {
			// logger.fail("Pizza not removed from the Basket");
		}
	}

	@Then("see Price tag got removed from the checkout button")
	public void see_price_tag_got_removed_from_the_checkout_button() {
		/*
		 * try {
		 * 
		 * Assert.assertFalse(driver.findElement(page.PriceOptionUnderCheckout(driver.
		 * findElement(CUBR_Obj .TotalPriceAmount()).getText().trim())).isDisplayed());
		 * logger.pass("Checkout button not contains total price as expected"); }
		 * catch(Exception e){ logger.fail("Checkout button contains total price"); }
		 */
	}

	@Then("User see {int} item showing in checkout button")
	public void user_see_item_showing_in_checkout_button(Integer int1) {
	Assert.assertTrue(driver.findElement(page.ItemOptionUnderCheckout()).getText().contains(String.valueOf(int1)));
	 //logger.pass("After removing Pizza , total Item count is 1");
	}

	@Then("User Clicks on Checkout button")
	public void user_clicks_on_checkout_button() {
		try {
			driver.findElement(page.CheckoutBTN()).click();
			Thread.sleep(3000);
			// logger.pass("User Clicks on Checkout button");
		} catch (Exception e) {
			// logger.fail("Checkout button not present");
		}
	}

	@Then("User see minimum order required pop up is getting displayed")
	public void user_see_minimum_order_required_pop_up_is_getting_displayed() {
	//Assert.assertTrue(driver.findElement(page.MinOrderRequiredText()).isDisplayed());
	 //logger.pass("User see minimum order required pop up as =>"+driver.findElement(page.MinOrderRequiredText()).getText());
	}

	@After
	public void end() {
		driver.quit();
	}

}
