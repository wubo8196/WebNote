package com.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {

	private static final long serialVersionUID = 1669922471465487746L;

	private File file; // 上传的文件
	private String fileFileName; // 文件名称
	private String fileContentType; // 文件类型

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String upload() throws IOException {
		System.out.println("文件上传");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String realpath = ServletActionContext.getServletContext().getRealPath("/fileUpload");
		if (file != null) {
			String srcName = "file-" + UUID.randomUUID() + fileFileName.substring(fileFileName.lastIndexOf("."));
			File savefile = new File(new File(realpath), srcName);
			if (savefile.getParentFile().exists()) {
				try {
					savefile.getParentFile().mkdirs();
					FileUtils.copyFile(file, savefile);
					System.out.println("{'fileName':'" + srcName + "'}");
					out.write("{\"fileName\":\"" + srcName + "\"}");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		/**
		 * 若要存入数据库 fileName是在entity实体类中声明存放文件名称的变量 yu.setFileName(fileFileName)
		 * 这样将文件名称存入数据库 文件路径为：savefile
		 */
		return NONE;
	}

	public String uploadOneNoteImg() throws IOException {
		System.out.println("文件上传");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String realpath = ServletActionContext.getServletContext().getRealPath("/oneNoteImg");
		if (file != null) {
			String srcName = "file-" + UUID.randomUUID() + fileFileName.substring(fileFileName.lastIndexOf("."));
			File savefile = new File(new File(realpath), srcName);
			if (savefile.getParentFile().exists()) {
				try {
					savefile.getParentFile().mkdirs();
					FileUtils.copyFile(file, savefile);
					System.out.println("{'fileName':'" + srcName + "'}");
					out.write("{\r\n" + 
							"  \"code\": 0\r\n" + 
							"  ,\"msg\": \"\" \r\n" + 
							"  ,\"data\": {\r\n" + 
							"    \"src\": \""+ServletActionContext.getRequest().getContextPath()+"/oneNoteImg/"+srcName+"\"\r\n" + 
							"  }\r\n" + 
							"}");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return NONE;
	}

}
