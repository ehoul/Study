package com.tbt.smartgarments.nestingEngine.svg;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class LuanchChrom {
	static boolean handless = false;
	static String webdriver ="webdriver.chrome.driver";
	static String webdriverpath="C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe";
	static String weburl = "http://localhost:63342/SVGnest-master/index.html";
	static int layouttime = 12000;
	static int closetime = 1000;
	static String downloadPath = "C:\\PData\\NestingHints\\";
	static String uploadpath = "C:\\PData\\pieceSVGImages\\";

	public void genHintBySVG(String filename) throws AWTException, InterruptedException{
		
		System.setProperty(getWebdriver(), getWebdriverpath());
		DesiredCapabilities capabilities = implDownloadsPath();
		//判断界面模式
		if (handless) {
			implHandless(capabilities);
		}
		//初始化一个chrome浏览器实例，实例名称叫driver  
        WebDriver driver = new ChromeDriver(capabilities);
        //最大化窗口  
        driver.manage().window().maximize();  
        //设置隐性等待时间  
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        // get()打开一个站点  
        driver.get(getWeburl());
        //设置SVG文件路径
        driver.findElement(By.id("fileinput")).sendKeys(uploadpath+filename);
        
	   	 driver.findElement(By.id("cutter_bin")).click();
	   	 driver.findElement(By.id("start")).click();
	   	 Thread.sleep(getLayouttime());
	   	 driver.findElement(By.id("start")).click();
	   	 driver.findElement(By.id("save")).click();
	   	 String hint = driver.findElement(By.id("hint")).getText();
	   	 StringBuffer stringBuffer = new StringBuffer();
	   	 String savefile = downloadPath+filename.replaceAll(".svg", ".hint");
	   	 String hintout = hint.replaceAll("\\|", "\r\n");
	   	 System.out.println(hintout);
	   	 stringBuffer.append(hintout);
	   	 File file = new File(savefile);
	   	 FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   	 try {
	   		out.write(stringBuffer.toString().getBytes("UTF-8"));
	   		out.close();
	        //关闭并退出浏览器  
	        driver.quit(); 
		} catch (IOException e) {
	        //关闭并退出浏览器  
	        driver.quit(); 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	* Title: getWebdriver
	* Description:获取生成svg排料提示需要使用的浏览器，推荐使用chrome。默认浏览器为"webdriver.chrome.driver"
	* @return webdriver，浏览器驱动软件名称。
	* @author hong
	*2018年8月15日
	*/
	public static String getWebdriver() {
		
		return webdriver;
	}

	/**
	* Title: setWebdriver
	* Description:设置生成svg排料提示需要使用的浏览器，推荐使用chrome。默认浏览器为"webdriver.chrome.driver"
	* @param webdriver 浏览器驱动软件名称
	* @author hong
	*2018年8月15日
	*/
	public static void setWebdriver(String webdriver) {
		LuanchChrom.webdriver = webdriver;
	}

	/**
	* Title: getWebdriverpath
	* Description:获取浏览器安装路径，默认安装路径为："C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe"
	* @return webdriverpath，浏览器安装路径
	* @author hong
	*2018年8月15日
	*/
	public static String getWebdriverpath() {
//		String path = LuanchChrom.class.getProtectionDomain().getCodeSource().getLocation().getFile();
//		try {
//			path = java.net.URLDecoder.decode(path, "UTF-8");
//			webdriverpath = new File(path).getParent()+"\\chromedriver.exe";
//			System.out.println(webdriverpath);
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return webdriverpath;
	}

	/**
	* Title: setWebdriverpath
	* Description:设置浏览器安装路径，默认安装路径为："C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe"
	* @param webdriverpath 浏览器安装路径
	* @author hong
	*2018年8月15日
	*/
	public static void setWebdriverpath(String webdriverpath) {
		LuanchChrom.webdriverpath = webdriverpath;
	}

	/**
	* Title: getWeburl
	* Description:获取svg网页运行url
	* @return weburl svg网页运行url
	* @author hong
	*2018年8月15日
	*/
	public static String getWeburl() {
		return weburl;
	}

	/**
	* Title: setWeburl
	* Description:设置svg网页运行url
	* @param weburl svg网页运行url
	* @author hong
	*2018年8月15日
	*/
	public static void setWeburl(String weburl) {
		LuanchChrom.weburl = weburl;
	}

	/**
	* Title: getLayouttime
	* Description:获取排料时长，默认12000ms。
	* @return layouttime，排料时长，单位毫秒。
	* @author hong
	*2018年8月15日
	*/
	public static int getLayouttime() {
		return layouttime;
	}

	/**
	* Title: setLayouttime
	* Description:获取排料时长，默认12000ms。
	* @param layouttime 排料时长，单位毫秒。
	* @author hong
	*2018年8月15日
	*/
	public static void setLayouttime(int layouttime) {
		LuanchChrom.layouttime = layouttime;
	}
	
	/**
	* Title: getClosetime
	* Description: 获取网页停留时间。默认时长1000ms。
	* @return closetime，网页停留时间，单位ms。
	* @author hong
	*2018年8月16日
	*/
	public static int getClosetime() {
		return closetime;
	}

	/**
	* Title: setClosetime
	* Description: 设置网页停留时间。默认时长1000ms。
	* @param closetime，网页停留时间，单位ms。
	* @author hong
	*2018年8月16日
	*/
	public static void setClosetime(int closetime) {
		LuanchChrom.closetime = closetime;
	}

	/**
	* Title: getDownloadPath
	* Description: 获取SVG Hint文件保存路径，无默认路径，必须设置。
	* @return download Path，SVG Hint文件保存路径
	* @author hong
	*2018年8月16日
	*/
	public static String getDownloadPath() {
		return downloadPath;
	}

	/**
	* Title: setDownloadPath
	* Description:设置SVG Hint文件保存路径，无默认路径，必须设置。
	* @param downloadPath，Path，SVG Hint文件保存路径
	* @author hong
	*2018年8月16日
	*/
	public static void setDownloadPath(String downloadPath) {
		LuanchChrom.downloadPath = downloadPath;
	}

	/**
	 * 获取SVG文件上传路径
	 * @return
	 */
	public static String getUploadpath() {
		return uploadpath;
	}

	/**
	 * 设置SVG文件上传路径
	 * @param uploadpath
	 */
	public static void setUploadpath(String uploadpath) {
		LuanchChrom.uploadpath = uploadpath;
	}
	
	/**
	 * 获取浏览器界面模式状态 true:无界面 false:有界面
	 * @return
	 */
	public static boolean isHandless() {
		return handless;
	}

	/**
	 * 设置浏览器界面模式 true:无界面  false:有界面
	 * @param handless
	 */
	public static void setHandless(boolean handless) {
		LuanchChrom.handless = handless;
	}

    /**
     * 实施无界面模式
     * @param cps
     */
    public static void implHandless(DesiredCapabilities cps) {
    	ChromeOptions options = new ChromeOptions();
		//浏览器无界面模式开启
		options.addArguments("headless");
	    options.addArguments("no-sandbox");
	    cps.setCapability(ChromeOptions.CAPABILITY, options);
	}
	/**
	 * 设置浏览器下载路径
	 * @return
	 */
	public DesiredCapabilities implDownloadsPath() {

				String downloadsPath = getDownloadPath();

				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

				chromePrefs.put("download.default_directory", downloadsPath);

				ChromeOptions options = new ChromeOptions();

				options.setExperimentalOption("prefs", chromePrefs);

				DesiredCapabilities caps = new DesiredCapabilities();

				caps.setCapability(ChromeOptions.CAPABILITY, options);

				return caps;

			}

	public static void main(String[] args) throws InterruptedException, AWTException {  
//		  System.setProperty("webdriver.chrom.driver", "E:\\Driver\\chrom.exe");
//		  final String url = "http://www.baidu.com";
//		  WebDriver driver = new ChromeDriver();
//		  driver.get(url);
//		  driver.findElement(By.id("kw")).sendKeys("nihao");
//		  Actions action = new Actions(driver);
//		  action.sendKeys(Keys.ENTER).perform();
		  
		LuanchChrom method = new LuanchChrom();
		method.setHandless(true);
		method.genHintBySVG("1535003551576_c.svg");
		
}
}