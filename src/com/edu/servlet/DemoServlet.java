package com.edu.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DemoServlet extends HttpServlet {

	public void init() throws ServletException {
		String configPath = this.getInitParameter("configPath");
		String configXmlPath = this.getInitParameter("configXmlPath");
		Properties prop = new Properties();
		SAXReader reader = new SAXReader();
		try {
			prop.load(new FileInputStream(this.getServletContext().getRealPath(".")+configPath));
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			System.out.println("从Properties中获取的用户名是："+username+"，密码是： "+password+".");
			
			Document doc = reader.read(this.getServletContext().getRealPath(".")+configXmlPath);
			//得到根元素
			Element ele = (Element)doc.selectSingleNode("//user[@username='"+"admin"+"']");
			
			System.out.println("从XML中获取的用户名是："+ele.attributeValue("username")+".");
			System.out.println("从XML中获取的密码是："+ele.attributeValue("password")+".");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
