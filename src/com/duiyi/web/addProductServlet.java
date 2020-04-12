package com.duiyi.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.duiyi.domain.Product;
import com.duiyi.domain.ResultCodeData;
import com.duiyi.factory.BasicFactory;
import com.duiyi.service.ProductService;
import com.duiyi.utils.Constants;
import com.duiyi.utils.IOUtil;
import com.duiyi.utils.JSONUtil;
import com.duiyi.utils.PictureUtil;

public class addProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultCodeData result;
		String encode = this.getServletContext().getInitParameter("encode");
		// 保存普通字段
		Map<String, String> paramMap = new HashMap<String, String>();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(100*1024);
		factory.setRepository(new File(this.getServletContext().getRealPath("WEB-INF/temp")));
		
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		fileUpload.setFileSizeMax(2*1024*1024);
		if (!ServletFileUpload.isMultipartContent(request)) {
			result = new ResultCodeData(Constants.FAIL, Constants.FROM_FORMAT_ERROR);
			response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
			return;
		}
		List<FileItem> list = null;
		try {
			list = fileUpload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
			result = new ResultCodeData(Constants.FAIL, Constants.PARAM_FORMAT_ERROR);
			response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
			return;
		}
		if (list == null) {
			result = new ResultCodeData(Constants.FAIL, Constants.PARAM_FORMAT_ERROR);
			response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
			return;
		}
		for (FileItem item : list) {
			if (item.isFormField()) {
				// 普通字段
				paramMap.put(item.getFieldName(), item.getString(encode));
			} else {
				// 文件上传
				String realName = item.getName();
				System.out.println(realName + ":" + item.getFieldName());
				String uuidName = UUID.randomUUID().toString() + "_" + realName;
				String hash = Integer.toHexString(uuidName.hashCode());
				String upload = this.getServletContext().getRealPath("WEB-INF/prodPicture");
				String imgurl = "/WEB-INF/prodPicture";
				for (char c : hash.toCharArray()) {
					upload += "/" + c;
					imgurl += "/" + c;
				}
				imgurl += "/" + uuidName;
				paramMap.put("imgurl", imgurl);
				File uploadFile = new File(upload);
				if (!uploadFile.exists()) {
					uploadFile.mkdirs();
				}
				InputStream in = item.getInputStream();
				OutputStream out = new FileOutputStream(new File(upload, uuidName));
				IOUtil.inToOut(in, out);
				IOUtil.close(in, out);
				item.delete();
				
				// 生成缩略图
				PictureUtil util = new PictureUtil(this.getServletContext().getRealPath(imgurl));
				util.resizeByHeight(140);
			}
		}
		// 在数据库中添加商品
		Product product = new Product(paramMap);
		ProductService service = BasicFactory.getFactory().getInstance(ProductService.class);
		service.addProduct(product);

		result = new ResultCodeData(Constants.SUCCESS, Constants.RESULT_SUCCESS);
		response.getWriter().write(JSONUtil.buildJsonString(result.toString()));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
