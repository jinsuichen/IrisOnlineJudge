package fun.icpc.iris.irisonlinejudge.service.domainservice;

import fun.icpc.iris.irisonlinejudge.commons.util.IrisMessage;

import java.io.InputStream;

public interface OSSService {

    /**
     * Upload file to OSS.
     *
     * @param filePath  The path name of the file.
     * @param fileBytes The input stream of the file.
     * @return If upload successfully, return true, else return false.
     */
    IrisMessage<Boolean> uploadFile(String filePath, InputStream fileBytes);

    /**
     * Download file from OSS.
     *
     * @param filePath The path name of the file.
     * @return The input stream of the file.
     */
    IrisMessage<InputStream> downloadFile(String filePath);

    /**
     * Download file from OSS as avatar.
     *
     * @param filePath The path name of the file.
     * @param style    The style of the avatar. Such as "image/resize,m_fixed,w_100,h_100"
     * @return The input stream of the file.
     */
    IrisMessage<InputStream> downloadFileAsAvatar(String filePath, String style);

    /**
     * Download file from OSS as avatar.
     * The avatar is 100px * 100px as default.
     *
     * @param filePath The path name of the file.
     * @return The input stream of the file.
     */
    IrisMessage<InputStream> downloadFileAsAvatar(String filePath);

    /**
     * Delete file from OSS.
     *
     * @param filePath The path name of the file.
     * @return If delete successfully, return true, else return false.
     */
    IrisMessage<Boolean> deleteFile(String filePath);

    /**
     * Check whether the file exists.
     *
     * @param filePath The path name of the file.
     * @return If the file exists, return true, else return false.
     */
    IrisMessage<Boolean> checkFile(String filePath);

}
