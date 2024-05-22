package com.ACMD.app.Save_Layer;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
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

    final static Region region = Region.US_EAST_1;
    final static String bucketName = "macdtest";
    final static String accessKeyId = "AKIAZJ42G7ZVBUEAUGE3";
    final static String secretAcessKey = "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";

    static AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAcessKey);

    public static void Upload(File file) {

        
        S3Client client = S3Client.builder()
        .region(region)
        .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
        .build();
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
        S3Client client = S3Client.builder()
        .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
        .region(region)
        .build();
        try {
            GetObjectRequest request =  GetObjectRequest.builder().bucket(bucketName).key(fileName).build();
            client.getObject(request);
            
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            client.close();
        }
    }

 

}
