package uploadexcel;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import parse.ParseExcel;

@MultipartConfig
@WebServlet("/upload")
public class UploadExcel extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Part part = req.getPart("file");
		InputStream in = part.getInputStream();
		String header = part.getHeader("content-disposition");
		String fileName = getFileName(header);
		System.out.println(ParseExcel.readExcel(in, fileName));
	}

	public String getFileName(String header) {
		/**
		 * String[] tempArr1 = header.split(";");����ִ����֮���ڲ�ͬ��������£�tempArr1���������������������
		 * �������google������£�tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE������£�tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/**
		 * �������google������£�tempArr2={filename,"snmp4j--api.zip"}
		 * IE������£�tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// ��ȡ�ļ��������ݸ����������д��
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

}
