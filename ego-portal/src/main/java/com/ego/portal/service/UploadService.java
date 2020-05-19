package com.ego.portal.service;

import com.ego.common.result.FileResult;

import java.io.InputStream;

/**
 * 图片上传
 */
public interface UploadService {
    FileResult upload(InputStream inputStream, String fileName);
}
