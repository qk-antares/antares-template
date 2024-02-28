package com.antares.generator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

public class StaticGenerator {
    public static void main(String[] args) {
        // 获取整个项目的根路径
        String projectPath = System.getProperty("user.dir");
        // 输入路径：ACM 示例代码模板目录
        String inputPath = projectPath + File.separator + "/antares-template-demo/acm-template";
        // 输出路径：直接输出到项目的根目
        String outputPath = projectPath;
        copyFilesByHutool(inputPath, outputPath);
    }

    /**
     * 拷贝文件（Hutool 实现，会将输入目录完整拷贝到输出目录下）
     *
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 拷贝文件（自己实现）
     */
    public static void myCopyFile(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        //首先判断inputFile是文件还是目录
        if (inputFile.isDirectory()) {
            File outputTarget = new File(outputFile, inputFile.getName());
            if (!outputTarget.exists()) {
                outputTarget.mkdirs();
            }

            //获取目录下的所有文件和子目录
            File[] files = outputTarget.listFiles();
            //该目录下没有文件了
            if(ArrayUtil.isEmpty(files)) {
                return;
            }
            for (File file : files) {
                //递归拷贝下一层
                copyFileByRecursive(file, outputTarget);
            }
        } else {
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            Files.copy(inputFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
