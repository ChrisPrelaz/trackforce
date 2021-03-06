package com.revature.test.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.revature.test.pom.Login;
import com.revature.utils.EnvManager;
import com.revature.utils.EnvManager.OsCheck.OSType;

public class WebDriverUtil {

	private static WebDriver chromeDriver = null;
	private static WebDriver firefoxDriver = null;
	private static Properties prop = new Properties();
	static {
		InputStream locProps = Login.class.getClassLoader().getResourceAsStream("tests.properties");
		try {
			prop.load(locProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WebDriverUtil() {

	}

	public static WebDriver getChromeDriver() {
		// doesnt work, for some reason i have to put driver in
		// \sts-3.9.1-RELEASE\src\main\resources
		// some path like that
		// like the actual installation of sts, if you get a testNG error, check the
		// stack trace
		// for the directory this code is looking into
		// if you want to see what happening on the ec2-54-210-36-233.compute-1 instance
		// you can check the logs folder in the apache installation
		// currently there is a chromedriver in the bin/src/main/resources of the Apache
		// folder
		File f1;
		if (EnvManager.getOperatingSystemType() == OSType.Windows) {
			f1 = new File(prop.getProperty("chromeDriverPath"));
		} else {
			f1 = new File(prop.getProperty("chromeDriverMacPath"));
		}
		System.setProperty("webdriver.chrome.driver", f1.getAbsolutePath());
		chromeDriver = new ChromeDriver();
		return chromeDriver;
	}
	
	public static WebDriver getFirefoxDriver() {
		File f1;
		if (EnvManager.getOperatingSystemType() == OSType.Windows) {
			f1 = new File(prop.getProperty("firefoxDriverPath"));
		} else {
			//Currently don't have a Firefox for Mac driver, so fetches the chrome Mac path
			f1 = new File(prop.getProperty("chromeDriverMacPath"));
			System.setProperty("webdriver.chrome.driver", f1.getAbsolutePath());
			chromeDriver = new ChromeDriver();
			return chromeDriver;
		}
		System.setProperty("webdriver.gecko.driver", f1.getAbsolutePath());
		firefoxDriver = new FirefoxDriver();
		return firefoxDriver;
	}

}