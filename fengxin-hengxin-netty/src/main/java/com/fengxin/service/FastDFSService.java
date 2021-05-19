package com.fengxin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FastDFSService {
    public String upload(MultipartFile file) throws Exception;
}
