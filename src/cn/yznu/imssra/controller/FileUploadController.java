package cn.yznu.imssra.controller;

import cn.yznu.imssra.util.MyUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.UUID;

@Controller
public class FileUploadController {

    /**
     * 上传单个文件到
     * @param request
     * @param file
     * @param model
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public void upload(HttpServletRequest request,HttpServletResponse response,@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        String[] strs = fileName.split("\\.");
//        System.out.println(path);
//        System.out.println(fileName);
//        System.out.println(Arrays.toString(fileName.split(".")));
        UUID uuid = UUID.randomUUID();
        fileName = uuid.toString().replace("-","")+"."+strs[strs.length-1];
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        // 保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=UTF-8");
        //通过response 获取pw
        PrintWriter pw = response.getWriter();
//        pw.print(request.getContextPath() + "/upload/" + fileName);//返回文件的路劲
        pw.print(fileName);//返回文件的路劲
        pw.flush();
        pw.close();
    }

}
