package com.spring.comm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class Message {
	public void alert(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
	    out.println("<script>");
	    out.println(message);
	    out.println("</script>");
	    out.flush();
	    out.close();
	}
}
