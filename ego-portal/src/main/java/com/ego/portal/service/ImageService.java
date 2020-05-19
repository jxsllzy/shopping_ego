package com.ego.portal.service;

import com.ego.common.result.ImageResult;

import java.io.InputStream;

public interface ImageService {
    ImageResult upload(InputStream inputStream, String fileName);
}
