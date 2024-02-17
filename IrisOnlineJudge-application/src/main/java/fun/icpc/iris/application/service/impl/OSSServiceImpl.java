package fun.icpc.iris.application.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import fun.icpc.iris.application.service.domainservice.OSSService;
import fun.icpc.iris.sharedkernel.util.IrisMessage;
import fun.icpc.iris.sharedkernel.util.IrisMessageFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.concurrent.Callable;

@Slf4j
@Service
@RequiredArgsConstructor
public class OSSServiceImpl implements OSSService {

    @Value("${aliyun.oss.bucket-name}")
    private String bucketName;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.access-key-id}")
    private String accessKeyId;

    @Value("${aliyun.oss.access-key-secret}")
    private String accessKeySecret;

    private final String AVATAR_STYLE = "image/resize,m_fixed,w_100,h_100";

    private OSS ossClient;

    @PostConstruct
    public void init() {
        ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    @Override
    public IrisMessage<Boolean> uploadFile(String filePath, InputStream fileBytes) {
        return ossOperation(() -> {
            // Create a PutObjectRequest object.
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath, fileBytes);

            //  Upload file to OSS.
            PutObjectResult result = ossClient.putObject(putObjectRequest);

            return IrisMessageFactory.success(true);
        });
    }

    @Override
    public IrisMessage<InputStream> downloadFile(String filePath) {
        return ossOperation(() -> {
            OSSObject ossObject = ossClient.getObject(bucketName, filePath);
            return IrisMessageFactory.success(ossObject.getObjectContent());
        });
    }

    @Override
    public IrisMessage<InputStream> downloadFileAsAvatar(String filePath, String style) {
        return ossOperation(() -> {
            GetObjectRequest request = new GetObjectRequest(bucketName, filePath);
            request.setProcess(style);

            OSSObject ossObject = ossClient.getObject(request);

            return IrisMessageFactory.success(ossObject.getObjectContent());
        });
    }

    @Override
    public IrisMessage<InputStream> downloadFileAsAvatar(String filePath) {
        return downloadFileAsAvatar(filePath, AVATAR_STYLE);
    }

    @Override
    public IrisMessage<Boolean> deleteFile(String filePath) {
        return ossOperation(() -> {
            ossClient.deleteObject(bucketName, filePath);
            return IrisMessageFactory.success(true);
        });
    }

    @Override
    public IrisMessage<Boolean> checkFile(String filePath) {
        return ossOperation(() -> {
            boolean exists = ossClient.doesObjectExist(bucketName, filePath);
            return IrisMessageFactory.success(exists);
        });
    }

    private <T> IrisMessage<T> ossOperation(Callable<IrisMessage<T>> callable) {
        IrisMessage<T> result;
        try {
            result = callable.call();
        } catch (OSSException oe) {
            log.error("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            log.error("Error Message: {}", oe.getErrorMessage());
            log.error("Error Code: {}", oe.getErrorCode());
            log.error("Request ID: {}", oe.getRequestId());
            log.error("Host ID: {}", oe.getHostId());
            return IrisMessageFactory.fail(oe.getErrorMessage());
        } catch (ClientException ce) {
            log.error("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            log.error("Error Message: {}", ce.getMessage());
            return IrisMessageFactory.fail(ce.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
