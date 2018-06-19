package com.web.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class TestMailUtil {

	@Test
	public void testSendEmail() throws IOException {
		String html = ReadModel.readModel("mailModel/findPass.html");
		MailUtil.sendEmil("1451446750@qq.com", "账号激活",html);
	}
	
	@Test
	public void testMd5() {
		System.out.println(MD5Utils.md5("1235789xy"));
	}
}
