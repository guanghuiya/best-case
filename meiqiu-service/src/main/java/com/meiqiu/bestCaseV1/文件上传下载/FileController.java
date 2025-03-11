package com.meiqiu.bestCaseV1.文件上传下载;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description 文件上传下载
 * @Author 胖辉
 * @Date 2025/3/5
 * @Time 10:50
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return "请选择文件";
        }
        try {
            //获取文件的字节数组
            byte[] bytes = file.getBytes();
            //定义文件存储路径
            String filePath = "/Users/songgh/Desktop/temp/";
            Path path = Paths.get(filePath + file.getOriginalFilename());
            //创建存储目录
            File directory = new File(filePath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            //将字节数组写入文件
            Files.write(path, bytes);
            System.out.println("文件路径：" + path.toString());
            //返回链接
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public ResponseEntity download(@RequestBody List<Integer> fileIds) {
        //模拟数据库查询文件路径
        Map<Integer, String> filePathMap = new HashMap<>();
        filePathMap.put(1, "/Users/songgh/Desktop/temp/2022.pdf");
        filePathMap.put(2, "/Users/songgh/Desktop/temp/面试题.pdf");

        try {
            //创建 临时zip 文件
            File zipFile = File.createTempFile("batch-download", ".zip");
            try (FileOutputStream fos = new FileOutputStream(zipFile);
                 ZipOutputStream zos = new ZipOutputStream(fos)
            ) {
                //压缩文件到 zip 中
                for (Integer fileId : fileIds) {
                    //获取文件路径
                    String filePath = filePathMap.get(fileId);
                    //判断文件是否存在
                    if (filePath == null) {
                        continue;
                    }
                    Path path = Paths.get(filePath);
                    if (!Files.exists(path)) {
                        continue;
                    }
                    //创建 zip 条目
                    ZipEntry zipEntry = new ZipEntry(path.getFileName().toString());
                    zos.putNextEntry(zipEntry);
                    // 将文件内容写入 ZIP 条目
                    try (InputStream fis = Files.newInputStream(path)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, length);
                        }
                    }
                    zos.closeEntry();
                }
                //读取 zip文件内容
                byte[] zipBytes = Files.readAllBytes(zipFile.toPath());
                // 设置响应头
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", "batch-download.zip");
                headers.setContentLength(zipBytes.length);
                //删除临时文件
                zipFile.delete();
                return new ResponseEntity<>(zipBytes, headers, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
