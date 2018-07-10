package com.hu.ssm.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @author hutiantian
 * @create 2018/6/18 14:37
 * @since 1.0.0
 */
@Service
@Slf4j
public class FtpUtil {

    private static String serverIp;                             //FTP服务器地址
    private static int port;                                    //FTP服务器端口
    private static String userName;                             //FTP服务器用户名
    private static String userPassword;                         //FTP服务器密码
    private static String encoding;                             //FTP默认文件名和目录编码为iso-8859-1
    private static String separator;                            //FTP服务器所在系统的分隔符
    private static int timeout;                                //连接超时时间
    private static final int bufferSize= 1024;
    private static final String utf = "utf-8";

    static {
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        Properties properties = new Properties();
        try {
            // 使用properties对象加载输入流
            properties.load(FtpUtil.class.getClassLoader().getResourceAsStream("ftp_server.properties"));
            //获取key对应的value值
            serverIp = properties.getProperty("serverIp");
            port = Integer.parseInt(properties.getProperty("port"));
            userName = properties.getProperty("userName");
            userPassword = properties.getProperty("userPassword");
            encoding = properties.getProperty("encoding");
            separator = properties.getProperty("separator");
            timeout= Integer.parseInt(properties.getProperty("timeout"));
        }catch (Exception e){
            log.error("初始化FtpUtil失败!",e);
        }
    }

    private FtpUtil(){};

    /**
     * 连接ftp服务器
     * @return 连接成功与否 true:成功， false:失败
     */
    public FTPClient connect() {
        FTPClient ftpClient = null;
        try {
            ftpClient = new FTPClient();
            // 连接
            ftpClient.connect(serverIp, port);
            ftpClient.login(userName, userPassword);
            // 检测连接是否成功
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                close(ftpClient);
                log.error("FTP server connect failed!");
                return null;
            }
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);     // 设置上传模式.binally  or ascii
            ftpClient.enterLocalPassiveMode();               // Use passive mode as default
            ftpClient.setControlEncoding(utf);               // 设置编码
            ftpClient.setBufferSize(bufferSize*bufferSize);
            ftpClient.setConnectTimeout(timeout);           //如果超过就判定超时
            log.debug("FTP server connect success!");
        } catch (Exception e) {
            close(ftpClient);
            log.error("FTP server connect failed!",e);
            ftpClient = null;
        }
        return ftpClient;
    }

    /**
     * 字符串转码
     */
    public static String changeEncoding(String name) throws Exception{
        return new String(name.getBytes(utf),encoding);
    }

    /**
     * 创建目录，并将工作空间切换至该目录下
     * @param directory 指定的目录
     * @return 目录是否创建成功
     */
    private static boolean mkdir(FTPClient ftpClient,String directory) throws IOException{
        boolean flag = false;
        String[] directoryList = directory.split(separator);
        if(directoryList.length>0){
            flag = true;
            for (int i = 0; i < directoryList.length; i++) {
                //"/"开头的先切换到根目录，解决ftp服务器没有指定工作目录的bug
                if(i==0&&directoryList[i].equals("")){
                    if(!ftpClient.changeWorkingDirectory(separator)){
                        flag = false;
                        break;
                    }
                }
                if(!directoryList[i].equals("")){
                    //如果目录不存在，则新创建，否则切换工作空间到此目录下
                    if(!ftpClient.changeWorkingDirectory(directoryList[i])){
                        if(!ftpClient.makeDirectory(directoryList[i])){
                            flag = false;
                            break;
                        }else{
                            if(!ftpClient.changeWorkingDirectory(directoryList[i])){
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            }
        }else{
            if(ftpClient.makeDirectory(directory)){
                flag = ftpClient.changeWorkingDirectory(directory);
            }
        }
        return flag;
    }

    /**
     * 上传文件到FTP服务器，目录不存在，则新建；文件已存在，则覆盖
     * @param inputStream                待上送的输入流
     * @param ftpDirectory               FTP目录
     * @param ftpFileName               上传到服务器的文件名
     * @return
     */
    public boolean upload(InputStream inputStream, String ftpDirectory, String ftpFileName) {
        boolean flag = false;
        FTPClient ftpClient = connect();
        if(ftpClient!=null){
            try {
                ftpDirectory = changeEncoding(ftpDirectory);        //转码
                ftpFileName = changeEncoding(ftpFileName);          //转码
                if(!mkdir(ftpClient,ftpDirectory)){                           // 创建目录
                    return false;
                }
                flag = ftpClient.storeFile(ftpFileName, inputStream);       // 上传
            } catch (Exception e) {
                log.error("FTPUtil upload exception!", e);
            } finally {
                try {
                    if(inputStream!=null){
                        inputStream.close();
                    }
                } catch (IOException e) {
                    log.error("FileInputStream close exception!", e);
                }
                close(ftpClient);
            }
        }
        return flag;
    }

    /**
     * 从FTP服务器下载文件
     * @param ftpDirectoryAndFileName   ftp服务器文件路径，以/dir形式开始
     * @param outputStream              输出文件流
     * @return
     */
    public boolean downLoad(String ftpDirectoryAndFileName, OutputStream outputStream) {
        boolean flag = false;
        FTPClient ftpClient = connect();
        if(ftpClient!=null){
            try {
                String filePath = "";
                String fileName = "";
                if(ftpDirectoryAndFileName.contains(separator)){
                    filePath = ftpDirectoryAndFileName.substring(0, ftpDirectoryAndFileName.lastIndexOf(separator));
                    fileName = ftpDirectoryAndFileName.substring(ftpDirectoryAndFileName.lastIndexOf(separator) + 1);
                }else{
                    fileName = ftpDirectoryAndFileName;
                }
                if(filePath.equals("")){
                    filePath=separator;
                }
                filePath = changeEncoding(filePath);
                fileName = changeEncoding(fileName);
                if(ftpClient.changeWorkingDirectory(filePath)){
                    if(ftpClient.retrieveFile(fileName, outputStream)){
                        flag = true;
                    }
                }
            } catch (Exception e) {
                log.error("ftpClient download exception!",e);
            }finally {
                close(ftpClient);
            }
        }
        return flag;
    }

    /**
     * 删除FTP上的文件
     * @param ftpDirAndFileName 路径开头不能加/，比如应该是test/filename1
     * @return
     */
    public boolean deleteFile(String ftpDirAndFileName) {
        FTPClient ftpClient = connect();
        if(ftpClient==null){
            return false;
        }
        try {
            return ftpClient.deleteFile(changeEncoding(ftpDirAndFileName));
        } catch (Exception e) {
            log.error("ftpClient deleteFile failed!", e);
            return false;
        }finally {
            close(ftpClient);
        }
    }

    /**
     * 关闭链接
     */
    public static void close(FTPClient ftpClient) {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
            log.info("close FTP connect success!");
        } catch (Exception e) {
            log.error("close FTP connect failed!", e);
        }
    }
}



