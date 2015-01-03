package util;

import java.io.File;

public class Html {
		
	private StringBuffer html;
	
	public Html(File output) {
		
		html = new StringBuffer();
		
		init();
				
	}
	
	private void init() {
		
		File baseHtml = new File(getClass().getResource("/resources/html/base.html").getFile());
		File css = new File(getClass().getResource("/resources/html/style.css").getFile());
		File logo = new File(getClass().getResource("/resources/logo.png").getFile());
		
		String s = HandlesFile.readFile(baseHtml);
		s = s.replace("<STYLE>", css.getAbsolutePath());
		s = s.replace("<LOGO>", logo.getAbsolutePath());
				
		html.append(s);
	}
	
	public String createFile(String title, String content) {
		
		String s = html.toString();
		
		s = s.replace("<TITLE>", title);
		s = s.replace("<CONTENT>", content);
		
		html.delete(0, html.length());
		html.append(s);
		
		return html.toString();
		
	}
	
	public String getHtml() {
		return html.toString();
	}

}
