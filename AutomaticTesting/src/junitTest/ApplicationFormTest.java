package junitTest;

import static org.junit.Assert.fail;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;

import com.thoughtworks.selenium.webdriven.commands.KeyEvent;

import util.DateFormatter;
import util.Properties;
import util.ScreenShotOnFailure;
import util.Util;
import util.WebDriverManager;

public class ApplicationFormTest {

	private Logger logger = LoggerFactory.getLogger(SendMessageTest.class);
	
//	private WebDriver driver;
	
	WebDriver driver = WebDriverManager.getDriverInstance();
	
	@Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(driver);
	
	
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private Properties properties = new Properties(CreateEligibilityChecklist.class.getSimpleName());
	private Util util;
	
	@Before
	public void setUp() throws Exception {
//		driver = new FirefoxDriver();
		
		WebDriverManager.startDriver();
		WebDriver driver = WebDriverManager.getDriverInstance();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		util = new Util(properties);
		
		driver.get(util.getLoginUrl());
		driver.manage().window().maximize();
		driver.findElement(By.id("mainForm:userName")).clear();
		driver.findElement(By.id("mainForm:userName")).sendKeys("schaffm01");
		driver.findElement(By.id("mainForm:pass")).clear();
		driver.findElement(By.id("mainForm:pass")).sendKeys("Start123");
		driver.findElement(By.id("mainForm:submitButton")).click();
	}

	@Test
	public void test() throws Exception {
		
		WebDriver driver = WebDriverManager.getDriverInstance();

		
		//Create new application
		driver.findElement(By.xpath("//h3[text() = 'eMS Management']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button/span[text()='Calls']")));
		
		driver.findElement(By.xpath("//button/span[text()='Calls']")).click();
		driver.findElement(By.xpath("//td[text()='Max Call 5']/following-sibling::td//button[@title='Apply Project']")).click();
//		driver.findElement(By.xpath("//td[text()='Max Call, automatically generated at <2016.09.05 09:18:59>']/following-sibling::td//button[@title='Apply Project']")).click();
		
		//Project acronym, name, and summary
		String date = DateFormatter.getCurrentDate();
		driver.findElement(By.id("mainForm:a1_tif_s_projectAcronym")).sendKeys("P<"+ date +">");
		driver.findElement(By.id("mainForm:a1_tif_s_projectTitle")).sendKeys("Test Project");
//		driver.findElement(By.id("mainForm:a1_tif_s_projectTitle")).sendKeys("Test Project, automatically generated at <"+ date +">");
		fillEditor("Rich Text Editor, mainForm:SummaryEditorEN", "Project summary");
		
//		//Date input (currently not working)
//		driver.findElement(By.id("mainForm:a1_cal_StartDate_input")).clear();
//		driver.findElement(By.id("mainForm:a1_cal_StartDate_input")).sendKeys("01.09.2016");
//		driver.findElement(By.id("mainForm:a1_cal_EndDate_input")).clear();
//		driver.findElement(By.id("mainForm:a1_cal_EndDate_input")).sendKeys("31.12.2016");

		//Create project
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		
		Thread.sleep(4000);
		
		//Open existing project
//		driver.findElement(By.xpath("//td[text()='Test Project, automatically generated at <2016.08.24 11:04:45>']/following-sibling::td//button[@title='View']")).click();
		
		driver.findElement(By.xpath("//button/span[text()='Toggle Tree']")).click();
		
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//a[text()='Partner']")).click();
		
		//Lead Partner
		driver.findElement(By.id("mainForm:addPartner")).click();
		
		driver.findElement(By.id("mainForm:b1_tif_s_nameOrgOrig")).sendKeys("Lead Partner");
		driver.findElement(By.id("mainForm:b1_tif_s_nameOrgEng")).sendKeys("Lead Partner");
		driver.findElement(By.id("mainForm:EN_department")).sendKeys("Department 1");
		driver.findElement(By.id("mainForm:b1_tif_s_nameAbbrevOrg")).sendKeys("LP");
		
		driver.findElement(By.id("mainForm:nutsBlock_nuts0")).click();
		
		driver.findElement(By.xpath("//li[@data-label='ÖSTERREICH (AT)']")).click();
		
		driver.findElement(By.id("mainForm:b1_tif_s_Street")).sendKeys("Kirchberggasse");
		driver.findElement(By.id("mainForm:housenumber")).sendKeys("33-35");
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("mainForm:b1_tif_s_Pcode")).sendKeys("1070");
		driver.findElement(By.id("mainForm:input_city")).sendKeys("Vienna");
		driver.findElement(By.id("mainForm:b1_tif_s_homePage")).sendKeys("www.website.com");
		driver.findElement(By.id("mainForm:b1_tif_s_vatNumber")).sendKeys("000000");
		driver.findElement(By.id("mainForm:b1_tif_s_repName")).sendKeys("John");
		driver.findElement(By.id("mainForm:b1_tif_s_repLName")).sendKeys("Doe");
		driver.findElement(By.id("mainForm:b1_tif_s_repEmail")).sendKeys("john@doe.com");
		driver.findElement(By.id("mainForm:b1_tif_s_repTel")).sendKeys("06640000000");
		driver.findElement(By.id("mainForm:b1_tif_s_contactName")).sendKeys("Joe");
		driver.findElement(By.id("mainForm:b1_tif_s_contactLName")).sendKeys("Doe");
		driver.findElement(By.id("mainForm:b1_tif_s_contactEmail")).sendKeys("joe@doe.com");
		driver.findElement(By.id("mainForm:b1_tif_s_contactTel")).sendKeys("06640000000");
		driver.findElement(By.id("mainForm:ExperienceEN")).sendKeys("Experience");
		driver.findElement(By.id("mainForm:BenefitEN")).sendKeys("Benefits");
		driver.findElement(By.id("mainForm:OtherIntProjectsEN")).sendKeys("Other Projects");

		driver.findElement(By.xpath("//button/span[text()='Save']")).click();
		
		//Project Partner
		Thread.sleep(4000);

		driver.findElement(By.xpath("//a[text()='Partner']")).click();
		driver.findElement(By.id("mainForm:addPartner")).click();
		
		Thread.sleep(4000);
		
		driver.findElement(By.id("mainForm:b1_tif_s_nameOrgOrig")).sendKeys("Project Partner");
		driver.findElement(By.id("mainForm:b1_tif_s_nameOrgEng")).sendKeys("Project Partner");
		driver.findElement(By.id("mainForm:EN_department")).sendKeys("Department 1");
		driver.findElement(By.id("mainForm:b1_tif_s_nameAbbrevOrg")).sendKeys("PP");
		
		driver.findElement(By.id("mainForm:nutsBlock_nuts0")).click();
		
		driver.findElement(By.xpath("//li[@data-label='ÖSTERREICH (AT)']")).click();
		
		driver.findElement(By.id("mainForm:b1_tif_s_Street")).sendKeys("Kirchberggasse");
		driver.findElement(By.id("mainForm:housenumber")).sendKeys("33-35");
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("mainForm:b1_tif_s_Pcode")).sendKeys("1070");
		driver.findElement(By.id("mainForm:input_city")).sendKeys("Vienna");
		driver.findElement(By.id("mainForm:b1_tif_s_homePage")).sendKeys("www.website.com");
		driver.findElement(By.id("mainForm:b1_tif_s_vatNumber")).sendKeys("000000");
		driver.findElement(By.id("mainForm:b1_tif_s_repName")).sendKeys("John");
		driver.findElement(By.id("mainForm:b1_tif_s_repLName")).sendKeys("Doe");
		driver.findElement(By.id("mainForm:b1_tif_s_repEmail")).sendKeys("john@doe.com");
		driver.findElement(By.id("mainForm:b1_tif_s_repTel")).sendKeys("06640000000");
		driver.findElement(By.id("mainForm:b1_tif_s_contactName")).sendKeys("Joe");
		driver.findElement(By.id("mainForm:b1_tif_s_contactLName")).sendKeys("Doe");
		driver.findElement(By.id("mainForm:b1_tif_s_contactEmail")).sendKeys("joe@doe.com");
		driver.findElement(By.id("mainForm:b1_tif_s_contactTel")).sendKeys("06640000000");
		driver.findElement(By.id("mainForm:ExperienceEN")).sendKeys("Experience");
		driver.findElement(By.id("mainForm:BenefitEN")).sendKeys("Benefits");
		driver.findElement(By.id("mainForm:OtherIntProjectsEN")).sendKeys("Other Projects");

		driver.findElement(By.xpath("//button/span[text()='Save']")).click();
		
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//a[text()='Partner']")).click();
		
		driver.findElement(By.id("mainForm:partnershipConcept_0_EN")).sendKeys("Concept");
		driver.findElement(By.id("mainForm:strategicPartnership_0_EN")).sendKeys("Strategic Partnership");
		driver.findElement(By.id("mainForm:partnershipAssociation_0_EN")).sendKeys("Association");
		
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		
		//Project Relevance
		driver.findElement(By.xpath("//a[text()='Project Relevance']")).click();
	
		fillEditor("Rich Text Editor, mainForm:TerChallangeEditorEN", "Challenge");
		fillEditor("Rich Text Editor, mainForm:ProApproachEditorEN", "Approach");
		fillEditor("Rich Text Editor, mainForm:CoopReasonEditorEN", "Reason");
		
		driver.findElement(By.id("mainForm:c1_4_lr_developOperati_SO")).click();
		driver.findElement(By.id("mainForm:c1_4_lr_implementOperati_SO")).click();
		driver.findElement(By.id("mainForm:c1_4_lr_staffing_SO")).click();
		driver.findElement(By.id("mainForm:c1_4_lr_finances_SO")).click();
		driver.findElement(By.id("mainForm:c1_4_lr_communication_SO")).click();
		driver.findElement(By.id("mainForm:c1_4_lr_Decisionmaking_SO")).click();
		driver.findElement(By.id("mainForm:c1_4_lr_Longtermeffects_SO")).click();
		driver.findElement(By.id("mainForm:c1_4_lr_Knowledge_SO")).click();
		
		driver.findElement(By.id("mainForm:coopcritDevelopmentDesc_EN")).sendKeys("Criterion");
		driver.findElement(By.id("mainForm:coopcritImplementationDesc_EN")).sendKeys("Criterion");
		driver.findElement(By.id("mainForm:coopcritStaffingDesc_EN")).sendKeys("Criterion");
		driver.findElement(By.id("mainForm:coopcritFinancesDesc_EN")).sendKeys("Criterion");
		driver.findElement(By.id("mainForm:coopcritCommunicationDesc_EN")).sendKeys("Criterion");
		driver.findElement(By.id("mainForm:coopcritDecisionmakingDesc_EN")).sendKeys("Criterion");
		driver.findElement(By.id("mainForm:coopcritLongtermeffectsDesc_EN")).sendKeys("Criterion");
		driver.findElement(By.id("mainForm:coopcritKnowledgeDesc_EN")).sendKeys("Criterion");

		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		
		//Project Focus
		driver.findElement(By.xpath("//a[text()='Project Focus']")).click();
		driver.findElement(By.id("mainForm:OverallObjectiveEditorEN")).sendKeys("Overall Objective");
		driver.findElement(By.id("mainForm:ProjectResultEditorEN")).sendKeys("Project Result");
		driver.findElement(By.id("mainForm:DurabilityEditorEN")).sendKeys("Durability");
		driver.findElement(By.id("mainForm:TransferabilityEditorEN")).sendKeys("Transferability");
		driver.findElement(By.xpath("//button/span[text()='Add Project Specific Objective']")).click();
		
		driver.findElement(By.xpath("//div[@id='mainForm:c2_project_specific_objectives_content']/div/span/div/div/div/span/input")).sendKeys("Specific Objective 1");
		driver.findElement(By.id("mainForm:projectOverallObjectives_1_EN")).sendKeys("Specific Objective Explanation");
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		
		//Project Context
		driver.findElement(By.xpath("//a[text()='Project Context']")).click();
		fillEditor("Rich Text Editor, mainForm:StrategiesinvolvementEditorEN", "Project Context");
		
		driver.findElement(By.xpath("//div[@id='mainForm:indicateHeader_content']/span/span[2]/div[1]/div[1]/div/div[2]")).click();
		driver.findElement(By.id("mainForm:indicateDescription_2_EN")).sendKeys("Strategy");
		driver.findElement(By.xpath("//div[@id='mainForm:indicateHeader_content']/span/span[2]/div[2]/div[1]/div/div[2]")).click();
		driver.findElement(By.id("mainForm:indicateDescription_4_EN")).sendKeys("Strategy");
		driver.findElement(By.xpath("//div[@id='mainForm:indicateHeader_content']/span/span[2]/div[3]/div[1]/div/div[2]")).click();
		driver.findElement(By.id("mainForm:indicateDescription_1_EN")).sendKeys("Strategy");
		driver.findElement(By.xpath("//div[@id='mainForm:indicateHeader_content']/span/span[2]/div[4]/div[1]/div/div[2]")).click();

		driver.findElement(By.id("mainForm:indicateDescription_3_EN")).sendKeys("Strategy");
		
		fillEditor("Rich Text Editor, mainForm:SynergiesEditorEN", "Synergies");
		fillEditor("Rich Text Editor, mainForm:KnowUseageEditorEN", "Knowledge");
		
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		
//		//Horizontal Principles
//		driver.findElement(By.xpath("//a[text()='Horizontal Principles']")).click();
//		driver.findElement(By.id("mainForm:horizontalPrinciples_description_0_EN")).sendKeys("Sustainability");
//		driver.findElement(By.id("mainForm:horizontalPrinciples_description_1_EN")).sendKeys("Equal opportunity");
//		driver.findElement(By.id("mainForm:horizontalPrinciples_description_2_EN")).sendKeys("Equality");
//		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		
		//Work Package List
		driver.findElement(By.xpath("//a[text()='Work Package List']")).click();
		driver.findElement(By.xpath("//span[text()='Create Preparation']/following-sibling::button")).click();
//		driver.findElement(By.xpath("//span[text()='Preparation']/following-sibling::button")).click();
		
//		driver.findElement(By.id("mainForm:cal_end1_month")).click();
//		driver.findElement(By.xpath("//li[text()='November']")).click();
		
		driver.findElement(By.xpath("//tbody[@id='mainForm:involvedPartnersTable_data']/tr[1]/td[1]/div/div[2]")).click();
		driver.findElement(By.xpath("//tbody[@id='mainForm:involvedPartnersTable_data']/tr[2]/td[1]/div/div[2]")).click();
		fillEditor("Rich Text Editor, mainForm:ActContributeEditorEN", "Summary");
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		Thread.sleep(2000);
		
			//Management
		driver.findElement(By.xpath("//a[text()='Work Package List']")).click();
		driver.findElement(By.xpath("//span[text()='Management']/following-sibling::button")).click();
		driver.findElement(By.id("mainForm:ActContributEditorEN")).sendKeys("Description");
		driver.findElement(By.id("mainForm:act0TitleMlc0")).sendKeys("Activity");
		driver.findElement(By.id("mainForm:act0Budget_input")).clear();
		driver.findElement(By.id("mainForm:act0Budget_input")).sendKeys("1000");
		driver.findElement(By.id("mainForm:A0DescriptionEditorEN")).sendKeys("Description");
		driver.findElement(By.xpath("//button/span[text()='Add Deliverable']")).click();
		driver.findElement(By.id("mainForm:A0DescriptionEditorMain_0_Mlc0")).sendKeys("Deliverable");
		driver.findElement(By.id("mainForm:act0del0TargetVal_input")).clear();
		driver.findElement(By.id("mainForm:act0del0TargetVal_input")).sendKeys("1000");
		driver.findElement(By.id("mainForm:deliverable_descr_0_0_EN")).sendKeys("Description");
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		Thread.sleep(2000);
		
			//Implementation
		driver.findElement(By.xpath("//a[text()='Work Package List']")).click();
		driver.findElement(By.id("mainForm:addImplementationOverview")).click();
		
		driver.findElement(By.id("mainForm:wpTitel0")).sendKeys("Implementation");
		fillEditor("Rich Text Editor, mainForm:ActContributeEditorEN", "Summary");
		driver.findElement(By.id("mainForm:O0utput_Title0")).sendKeys("Output");
		driver.findElement(By.id("mainForm:outputs_description_0_EN")).sendKeys("Output");
		driver.findElement(By.id("mainForm:O0utputUnit0_input")).clear();
		driver.findElement(By.id("mainForm:O0utputUnit0_input")).sendKeys("100");
//		driver.findElement(By.id("mainForm:selTargetgroups")).click();
//		driver.findElement(By.xpath("//label[text()='business support organisation']")).click();
		driver.findElement(By.id("mainForm:TargetGroupInvEditorEN")).sendKeys("Involvement");
		driver.findElement(By.id("mainForm:ExternalReuseEditorEN")).sendKeys("Transfer");
		driver.findElement(By.id("mainForm:ReuseEditorEN")).sendKeys("Transfer");
		driver.findElement(By.id("mainForm:act0TitleMlc0")).sendKeys("Activity");
		driver.findElement(By.id("mainForm:act0Budget_input")).clear();
		driver.findElement(By.id("mainForm:act0Budget_input")).sendKeys("1000");
		driver.findElement(By.id("mainForm:A0DescriptionEditorMain_0_Mlc0")).sendKeys("Deliverable");
		driver.findElement(By.id("mainForm:act0del0TargetVal_input")).clear();
		driver.findElement(By.id("mainForm:act0del0TargetVal_input")).sendKeys("100");
		driver.findElement(By.id("mainForm:deliverable_descr_0_0_EN")).sendKeys("Deliverable");
		driver.findElement(By.id("mainForm:A0DescriptionEditorEN")).sendKeys("Activity");
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		Thread.sleep(2000);
		
			//Investment
		driver.findElement(By.xpath("//a[text()='Work Package List']")).click();
		driver.findElement(By.id("mainForm:addInvestmentOverview")).click();
		driver.findElement(By.id("mainForm:wpTitel0")).sendKeys("Investment");
		fillEditor("Rich Text Editor, mainForm:ActContributeEditorEN", "Summary");
		driver.findElement(By.id("mainForm:JustificationEditorEN")).sendKeys("Justification");
		driver.findElement(By.id("mainForm:LocationEditorEN")).sendKeys("Location");
		driver.findElement(By.id("mainForm:RiskEditorEN")).sendKeys("Risk");
		driver.findElement(By.id("mainForm:DocumentationEditorEN")).sendKeys("Documentation");
		driver.findElement(By.id("mainForm:OwnershipEditorEN")).sendKeys("Ownership");
		driver.findElement(By.id("mainForm:O0utput_Title0")).sendKeys("Output");
		driver.findElement(By.id("mainForm:outputs_description_0_EN")).sendKeys("Output");
		driver.findElement(By.id("mainForm:O0utputUnit0_input")).clear();
		driver.findElement(By.id("mainForm:O0utputUnit0_input")).sendKeys("100");
		driver.findElement(By.id("mainForm:TargetGroupInvEditorEN")).sendKeys("Involvement");
		driver.findElement(By.id("mainForm:ExternalReuseEditorEN")).sendKeys("Transfer");
		driver.findElement(By.id("mainForm:ReuseEditorEN")).sendKeys("Transfer");
		driver.findElement(By.id("mainForm:act0TitleMlc0")).sendKeys("Activity");
		driver.findElement(By.id("mainForm:act0Budget_input")).clear();
		driver.findElement(By.id("mainForm:act0Budget_input")).sendKeys("1000");
		driver.findElement(By.id("mainForm:A0DescriptionEditorMain_0_Mlc0")).sendKeys("Deliverable");
		driver.findElement(By.id("mainForm:act0del0TargetVal_input")).clear();
		driver.findElement(By.id("mainForm:act0del0TargetVal_input")).sendKeys("100");
		driver.findElement(By.id("mainForm:deliverable_descr_0_0_EN")).sendKeys("Deliverable");
		driver.findElement(By.id("mainForm:A0DescriptionEditorEN")).sendKeys("Activity");
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		Thread.sleep(2000);
		
			//Communication
		driver.findElement(By.xpath("//a[text()='Work Package List']")).click();
		driver.findElement(By.xpath("//span[text()='Create Communication']/following-sibling::button")).click();
		fillEditor("Rich Text Editor, mainForm:ActContributeEditorEN", "Summary");
		driver.findElement(By.xpath("//button/span[text()='Add Communication Objective']")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("mainForm:j_idt339:0:ActContributEditor")).sendKeys("Approach");
		driver.findElement(By.id("mainForm:act0Budget_input")).clear();
		driver.findElement(By.id("mainForm:act0Budget_input")).sendKeys("1000");
		driver.findElement(By.id("mainForm:A0DescriptionEditorMain_0_Mlc0")).sendKeys("Deliverable");
		driver.findElement(By.id("mainForm:act0del0TargetVal_input")).clear();
		driver.findElement(By.id("mainForm:act0del0TargetVal_input")).sendKeys("100");
		driver.findElement(By.id("mainForm:deliverable_descr_0_0_EN")).sendKeys("Deliverable");
		driver.findElement(By.id("mainForm:A0DescriptionEditorEN")).sendKeys("Activity");
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		Thread.sleep(2000);
		
		//Define Periods
		driver.findElement(By.xpath("//a[text()='Define Periods']")).click();
		driver.findElement(By.xpath("//button/span[text()='Add']")).click();
		
		//Partner Budget
		driver.findElement(By.xpath("//a[text()='Partner Budget']")).click();
		driver.findElement(By.xpath("//tbody[@id='mainForm:b0_dl_partners_data']/tr[1]/td[7]/button[1]")).click();
		driver.findElement(By.id("mainForm:bflatBoth")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button/span[text()='Ok']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("mainForm:flatCheckBoxVal")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button/span[text()='Ok']")).click();
		Thread.sleep(3000);
		
		fillBudget(4);
		fillBudget(5);
		fillBudget(6);
		fillBudget(7);
		fillBudget(8);
		
		//Define Contribution
		driver.findElement(By.xpath("//a[text()='Partner Budget']")).click();
		driver.findElement(By.xpath("//tbody[@id='mainForm:b0_dl_partners_data']/tr[1]/td[7]/button[2]")).click();
		
		String contribution = driver.findElement(By.xpath("//div[@id='mainForm:partnerContributionRate']/div[2]/table/tbody/tr[2]/td[2]/span")).getText();
		
		driver.findElement(By.xpath("//tbody/tr/td[4]/span/input")).clear();
		driver.findElement(By.xpath("//tbody/tr/td[4]/span/input")).sendKeys(contribution);
		
		driver.findElement(By.xpath("//div[@class='ems-save-project-button']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button/span[text()='Check Saved Project']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//button/span[text()='Submit Checked Project']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button/span[text()='Yes']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button/span[2 and text()='Logout']")).click();
		Thread.sleep(4000);
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		WebDriver driver = WebDriverManager.getDriverInstance();
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
	private void fillBudget(int row) throws Exception{
		WebDriver driver = WebDriverManager.getDriverInstance();
		for (int i=2; i < 7; i++) {
			driver.findElement(By.xpath("//div/div[2]/table/tbody/tr[" + row + "]/td[" + i + "]/button/span[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//tr/td[3]/span/input")).clear();
			driver.findElement(By.xpath("//tr/td[3]/span/input")).sendKeys("50");
			driver.findElement(By.xpath("//button/span[text()='Save']")).click();
			Thread.sleep(3000);
		}
		
	}
	
	private void fillEditor(String field, String text) {
		WebDriver driver = WebDriverManager.getDriverInstance();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='" + field + "']")));
		driver.findElement(By.xpath("/html/body")).click();
		WebElement editableYes = driver.switchTo().activeElement(); 
		editableYes.clear();
		editableYes.sendKeys(text); 
		driver.switchTo().defaultContent();
	}
	
}
