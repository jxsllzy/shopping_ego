package com.ego.portal.service.impl;

import com.ego.common.result.ImageResult;
import com.ego.common.result.SrcResult;
import com.ego.portal.service.ImageService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 图片上传
 */
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public ImageResult upload(InputStream inputStream, String fileName) {
        ImageResult imageResult = new ImageResult();
        SrcResult srcResult = new SrcResult();
        //构造一个带指定 Region 对象的配置类
        com.qiniu.storage.Configuration cfg = new Configuration(Region.region0());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "wXGF1wW1jq_aFhrksbQveEtJknaDKFbfjK2pTrpz";
        String secretKey = "SvnwMYg-rlM4eyjHexf0WLF56lVEBzwCHyRTHMAy";
        String bucket = "ego-ly";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream,key,upToken,null, null);
                //解析上传成功的结果
               /* DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);*/
                if (response.statusCode ==200){
                    imageResult.setCode(0);
                    imageResult.setMsg("上传成功");
                    srcResult.setSrc("http://q949kegs6.bkt.clouddn.com/"+fileName);
                    imageResult.setData(srcResult);
                    return imageResult;
                }else {
                    imageResult.setCode(1);
                    imageResult.setMsg("上传失败");
                    return imageResult;
                }
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                imageResult.setCode(1);
                imageResult.setMsg("上传失败");
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception ex) {
            //ignore
        }
        return imageResult;
    }
}
