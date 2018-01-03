package com.xdhuxc.fastdfs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * @author Administrator
 *
 */
public class FastDFSClient {
	/**
     * 上传文件
     */
    public static String[] uploadFile(File file, String uploadFileName, long fileLength) throws IOException {
        byte[] fileBuff = getFileBuffer(new FileInputStream(file), fileLength);
        String[] files = null;
        String fileExtName = "";
        if (uploadFileName.contains(".")) {
            fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        } else {
            System.out.println("Fail to upload file, because the format of filename is illegal.");
            return null;
        }

        // 建立连接
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        
        System.out.println(trackerServer.getInetSocketAddress().getHostName() + ": " + trackerServer.getInetSocketAddress().getPort());
        
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        
        System.out.println(storageServer.getInetSocketAddress().getHostName() + ": " + storageServer.getInetSocketAddress().getPort());

        // 设置元信息
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", uploadFileName);
        metaList[1] = new NameValuePair("fileExtName", fileExtName);
        metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));

        // 上传文件
        try {
            files = storageClient.upload_file(fileBuff, fileExtName, metaList);
        } catch (Exception e) {
            System.out.println("Upload file \"" + uploadFileName + "\"fails");
        }
        trackerServer.close();
        return files;
    }
    private static byte[] getFileBuffer(InputStream inStream, long fileLength) throws IOException {

        byte[] buffer = new byte[256 * 1024];
        byte[] fileBuffer = new byte[(int) fileLength];

        int count = 0;
        int length = 0;

        while ((length = inStream.read(buffer)) != -1) {
            for (int i = 0; i < length; ++i) {
                fileBuffer[count + i] = buffer[i];
            }
            count += length;
        }
        return fileBuffer;
    }
    
    public static void main(String[] args) {
    	//加载配置文件的方式
        String configFileName = "D:\\EclipseProject\\integration\\src\\main\\resource\\fdfs_client.conf";
        try {
            ClientGlobal.init(configFileName);
        }catch(Exception e){
            e.printStackTrace();
        }
        File file = new File("C:\\Users\\Administrator\\Desktop\\abc.txt");
        //返回储存路径:group1 M00/00/00/wKhuW1Vmj6KAZ09pAAC9przUxEk788.jpg
        String[] files;
		try {
			files = uploadFile(file, "abc.txt", file.length());
			System.out.println(Arrays.asList(files));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

