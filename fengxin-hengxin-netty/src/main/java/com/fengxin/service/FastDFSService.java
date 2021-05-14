package com.fengxin.service;

import org.springframework.web.multipart.MultipartFile;

public interface FastDFSService {
    public String upload(MultipartFile file) throws Exception;
}
