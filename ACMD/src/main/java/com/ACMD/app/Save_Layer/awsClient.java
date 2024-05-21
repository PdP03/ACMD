package com.ACMD.app.Save_Layer;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class awsClient {

    public awsClient() {}

    static final Region region = Region.US_EAST_1;
    static final String bucketName = "macdtest";

    public static void Upload(File file) {

        
        S3Client client = S3Client.builder().region(region).build();
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(dt.format(LocalDateTime.now()))
                    .build();
            client.putObject(request, RequestBody.fromFile(file));
            System.out.println("Game file Uploaded"); //Da modificare per stampare sul "terminale" di gioco secondo i metodi della GUI
            client.close();
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            client.close();
        }
    }

    public void Download(String fileName){
        S3Client client = S3Client.builder().region(region).build();
        try {
            GetObjectRequest request =  GetObjectRequest.builder().bucket(bucketName).key(fileName).build();
            client.getObject(request);
            
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            client.close();
        }
    }

 

}
