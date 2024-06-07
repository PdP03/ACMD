package com.ACMD.app.Save_Layer;

import java.io.IOException;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectAttributesRequest;
import software.amazon.awssdk.services.s3.model.GetObjectAttributesResponse;
import software.amazon.awssdk.services.s3.model.ObjectAttributes;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.digest.DigestUtils;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

public class awsClient {

    private final static Region region = Region.US_EAST_1;
    private final static String bucketName = "macdtest";
    private final static String accessKeyId = "AKIAZJ42G7ZVBUEAUGE3";
    private final static String secretAcessKey = "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";
    private final static AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKeyId, secretAcessKey);
    private final static S3Client client = S3Client.builder().region(region)
            .credentialsProvider(StaticCredentialsProvider.create(awsCreds)).build();

        /**
     * Uploads a file to the configured S3 bucket.
     *
     * @param file The file to upload.
     */
    public static void Upload(File file) {

        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH-mm");

        try {
            PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(dt.format(LocalDateTime.now()))
                    .build();
            client.putObject(request, RequestBody.fromFile(file));
            client.close();
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            client.close();
        }
    }
        /**
     * Downloads a file from the configured S3 bucket, only if the checksums do not match. 
     *
     * @param index The index of the file to download. Passed from Graphics 
     */

    public static void Download(int index) {
        List<String> saveFiles = GetUploadedFiles();
        if (!CheckSum(index)) {
            try {
                GetObjectRequest request = GetObjectRequest.builder().bucket(bucketName).key(saveFiles.get(index))
                        .build();
                client.getObject(request);

            } catch (S3Exception e) {
                System.err.println(e.awsErrorDetails().errorMessage());
                client.close();
            }
        }
    }
        /**
     * Retrieves the list of the names of the uploaded files from the configured S3 bucket.
     *
     * @return A list of file names.
     */

    public static List<String> GetUploadedFiles() {
        ListObjectsV2Request listRequest = ListObjectsV2Request.builder().bucket(bucketName).build();

        ListObjectsV2Response listResponse = client.listObjectsV2(listRequest);

        return listResponse.contents().stream().map(S3Object::key).collect(Collectors.toList());
    }

        /**
     * Compares the checksum of the local and the remote file.
     *
     * @param index The index of the file to check.
     * @return True if the checksum matches, otherwise false.
     */

    private static boolean CheckSum(int index) {
        List<String> saveFiles = GetUploadedFiles();

        GetObjectAttributesRequest attrReq = GetObjectAttributesRequest.builder().bucket(bucketName)
                .key(saveFiles.get(index)).objectAttributes(ObjectAttributes.E_TAG).build();

        GetObjectAttributesResponse attrRes = client.getObjectAttributes(attrReq);

        String remoteMD5 = attrRes.eTag().replace("\"", "");
        try (InputStream is = Files.newInputStream(Paths.get("Savefile.json"))) {
            String localMD5 = DigestUtils.md5Hex(is);
            return localMD5.equals(remoteMD5);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
