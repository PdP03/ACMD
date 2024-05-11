package com.ACMD.app.Salva;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.DirectoryDownload;
import software.amazon.awssdk.transfer.s3.model.DownloadDirectoryRequest;

import java.util.List;
import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class awsClient {

    final Region region = Region.US_EAST_1;
    final String bucketName = "macdtest";

    public void Upload(File file) {

        
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
