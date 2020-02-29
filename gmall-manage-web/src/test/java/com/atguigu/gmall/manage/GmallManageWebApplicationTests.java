package com.atguigu.gmall.manage;

import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contextLoads() throws IOException, MyException {
        //配置FDFS的全局连接地址
        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();

        ClientGlobal.init(tracker);

        TrackerClient trackerClient = new TrackerClient();

        //获得一个trackerServer的实例
        TrackerServer trackerServer=trackerClient.getTrackerServer();

        //通过tracker获得一个Storage连接客户端
        StorageClient storageClient=new StorageClient(trackerServer,null);

        String[] uploadInfos = storageClient.upload_file("D:/a.JPG","JPG",null);

        String url = "http://192.168.8.126";
        for(String uploadInfo :uploadInfos){
            url += "/" +uploadInfo;

        }
        System.out.println(url);

}

}
