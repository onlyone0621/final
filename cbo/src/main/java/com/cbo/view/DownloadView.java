package com.cbo.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component("downloadView")
public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		File file=(File)model.get("fileDown");		
		response.setCharacterEncoding("UTF-8");
		String filename=URLEncoder.encode(file.getName(),"UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=\""+filename+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		FileInputStream fis=new FileInputStream(file);
		OutputStream os=response.getOutputStream();
		FileCopyUtils.copy(fis, os);
		os.close();
		fis.close();
		
		
	}
}