package com.trip.base.action;

import com.trip.base.aspect.NoResp;
import com.trip.base.util.DateUtil;
import com.trip.base.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yk on 2016/10/26.
 */
@Controller
@RequestMapping("/base")
public class UploadAction {

    @Value("${app.imagehost}")
    private String imagehost;

    @Value("${app.htmlpath}")
    private String htmlpath;

    @ResponseBody
    @RequestMapping("upload")
    public Object upload(HttpServletRequest request) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        Map<String,Object> fileMap = new HashMap<String,Object>();
        String year = DateUtil.getCurrentDate("yyyy");
        String month = DateUtil.getCurrentDate("MM");
        String date = DateUtil.getCurrentDate("dd");
        List<Map<String,Object>> urlList = new ArrayList<Map<String,Object>>();
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        int index = myFileName.lastIndexOf(".");
                        String suffix = myFileName.substring(index,myFileName.length());
                        //重命名上传后的文件名
                        String fileName = getRandomFileName(suffix);
                        String dir = File.separator+year+File.separator+month+File.separator+date;
                        String root = htmlpath+File.separator+"upload"+File.separator;
                        String path = root+File.separator+dir;
                        File dirFile = new File(path);
                        if(!dirFile.exists()){
                            dirFile.mkdirs();
                        }
                        //定义上传路径
                        path = path + File.separator + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                        fileMap.put("filename",myFileName);
                        fileMap.put("url",imagehost+"/upload/" +year+"/"+month+"/"+date+"/"+ fileName);
                    }
                }
            }
        }
        fileMap.put("error",0);
        return fileMap;
    }

    @ResponseBody
    @RequestMapping("multiupload")
    @NoResp
    public Object multiUpload(HttpServletRequest request) throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        Map<String,Object> resultMap = ResultUtil.getResultMap("0","操作成功");
        String year = DateUtil.getCurrentDate("yyyy");
        String month = DateUtil.getCurrentDate("MM");
        String date = DateUtil.getCurrentDate("dd");
        List<Map<String,Object>> urlList = new ArrayList<Map<String,Object>>();
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //取得request中的所有文件名
            List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("file");
           for(MultipartFile file :files){
                if (file != null) {
                    Map<String,Object> fileMap = new HashMap<String,Object>();
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        int index = myFileName.lastIndexOf(".");
                        String suffix = myFileName.substring(index,myFileName.length());
                        //重命名上传后的文件名
                        String fileName = getRandomFileName(suffix);
                        String dir = File.separator+year+File.separator+month+File.separator+date;
                        String root = htmlpath+File.separator+"upload"+File.separator;
                        String path = root+File.separator+dir;
                        File dirFile = new File(path);
                        if(!dirFile.exists()){
                            dirFile.mkdirs();
                        }
                        //定义上传路径
                        path = path + File.separator + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                        fileMap.put("filename",myFileName);
                        fileMap.put("url",imagehost+"/upload/" +year+"/"+month+"/"+date+"/"+ fileName);
                        urlList.add(fileMap);
                    }
                }
            }
        }
        resultMap.put("b",urlList);
        return resultMap;
    }


    /**
	 * 下载
	 */
	@RequestMapping(value="/downloadFile")
	@ResponseBody
	public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String path = request.getParameter("filePath");
		String attachname = request.getParameter("attachname");
	    //String filename = file.getName();
		String tempPath = request.getSession().getServletContext().getRealPath("/");
		File file = new File(tempPath+path);
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset();
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + new String(attachname.replaceAll(" ", "").getBytes("utf-8"),"iso8859-1"));
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
    /**
     * 获取随机文件名
     * @param suffix 后缀名
     * @return
     */
    private String getRandomFileName(String suffix){
        String fileName = new SimpleDateFormat("yyMMddHHmmssSSS").format(System.currentTimeMillis());
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            fileName += r.nextInt(10);
        }
        return fileName+suffix;
    }
}
