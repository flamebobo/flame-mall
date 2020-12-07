package com.flame.mall.admin.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.File;

/**
 * 测试上传文件
 *
 * <p>Title:</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/7 10:28
 */
public class AliyunOssUntil {

    public static final String endpoint = "oss-cn-beijing.aliyuncs.com";
    public static final String accessKeyId = "LTAI4G8NRovxHydVvEFuomFE";
    public static final String accessKeySecret = "QOlmj3kvIfhodczLaulGDDOhKm8KB8";
    public static final String bucketName = "flame-oss";

    public static OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    public static void main(String[] args) {
        ossClient.putObject(bucketName, "1.png", new File("C:\\Users\\admin\\Desktop\\1.png"));
        ossClient.shutdown();
        System.out.println("上传图片完成！");
    }


}
