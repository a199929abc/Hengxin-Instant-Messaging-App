package com.fengxin.controller;

import com.fengxin.pojo.Users;
import com.fengxin.pojo.bo.UsersBO;
import com.fengxin.service.FastDFSService;
import com.fengxin.service.UserService;
import com.fengxin.utils.IMoocJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class FileController {

    @Autowired
    private UserService userService;

    @Autowired
    private FastDFSService fastDFSService;

    @PostMapping(value="/upload",headers="content-type=multipart/form-data")
    public IMoocJSONResult uploadFace(MultipartFile file,
                                      String userId,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        // 使用fastdfs上传文件
        System.out.println("User ID : "+userId);
        String path = fastDFSService.upload(file);
        String fdfsServer = "http://18.220.84.169:88/";
        System.out.println(fdfsServer + path);
        String url = fdfsServer + path;
        //renew user head
        Users user = new Users();
        user.setId(userId);
      //  user.setFaceImage(thumpImgUrl);
        user.setFaceImageBig(url);
        user.setFaceImage(url);
        System.out.println("Uploading...");
        Users result = userService.updateUserInfo(user);

        return IMoocJSONResult.ok(result);
        //return fdfsServer + path;

    }

}
