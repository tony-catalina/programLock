package com.example.programlock.baiduFace.controller;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;
import com.example.programlock.baiduFace.entity.BaiduCloudAuthKey;
import com.example.programlock.baiduFace.util.ImageAndBase64Change;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestFaceContrast {

    public static void main(String[] args) {

        String image1 = ImageAndBase64Change.imageToBase64Str("D:/NDXT/temp/002.jpg");
        //String image2 = ImageAndBase64Change.imageToBase64Str("D:/NDXT/temp/001.jpg");//idCard
        String image2 = ImageAndBase64Change.imageToBase64Str("D:/NDXT/temp/003.jpg");

        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
        MatchRequest req1 = new MatchRequest(image1, "BASE64");
        //MatchRequest req2 = new MatchRequest(image2, "BASE64","IDCARD","NONE","NONE");
        MatchRequest req2 = new MatchRequest(image2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);

        AipFace client=new AipFace(BaiduCloudAuthKey.APP_ID,BaiduCloudAuthKey.API_KEY,BaiduCloudAuthKey.SECRET_KEY);
        JSONObject res = client.match(requests);
        System.out.println("======"+res.toString(2));
        System.out.println("===333==="+ JSON.toJSONString(res));
    }


    //人脸对比
    public static void faceContrast(AipFace client) {
        String image1 = "base64_1";
        String image2 = "base64_2";

        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
        MatchRequest req1 = new MatchRequest(image1, "BASE64");
        MatchRequest req2 = new MatchRequest(image2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);

        JSONObject res = client.match(requests);
        System.out.println(res.toString(2));

    }

}
