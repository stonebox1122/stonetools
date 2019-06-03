package com.stone.tools;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author stone
 * @date 2019/5/29 13:43
 * description 文件处理工具类
 */
public class FileUtils {

    /**
     * 获取当前resources目录下指定文件的输入流
     * @param fileName
     * @return
     */
    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    /**
     * 获取指定文件的输入流
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream getFileInputStream(String fileName) throws FileNotFoundException {
        InputStream input = null;
        File file = new File(fileName);
        if (file.exists()) {
            input = new FileInputStream(file);;
            return input;
        }
        return null;
    }

    /**
     * 复制文件
     * @param source
     * @param copy
     * @return
     * @throws Exception
     */
    public static boolean copyFile(String source, String copy) throws Exception {
        source = source.replace("\\", "/");
        copy = copy.replace("\\", "/");

        File source_file = new File(source);
        File copy_file = new File(copy);

        // BufferedStream缓冲字节流

        if (!source_file.exists()) {
            throw new IOException("文件复制失败：源文件（" + source_file + "） 不存在");
        }
        if (copy_file.isDirectory()) {
            throw new IOException("文件复制失败：复制路径（" + copy_file + "） 错误");
        }
        File parent = copy_file.getParentFile();
        // 创建复制路径
        if (!parent.exists()) {
            parent.mkdirs();
        }
        // 创建复制文件
        if (!copy_file.exists()) {
            copy_file.createNewFile();
        }

        FileInputStream fis = new FileInputStream(source_file);
        FileOutputStream fos = new FileOutputStream(copy_file);

        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] KB = new byte[1024];
        int index;
        while ((index = bis.read(KB)) != -1) {
            bos.write(KB, 0, index);
        }

        bos.close();
        bis.close();
        fos.close();
        fis.close();

        if (!copy_file.exists()) {
            return false;
        } else if (source_file.length() != copy_file.length()) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 重命名文件
     * @param url
     * @param new_name
     * @return
     * @throws Exception
     */
    public static boolean renameFile(String url, String new_name) throws Exception {
        String old_url = url;
        old_url = old_url.replace("\\", "/");
        File old_file = new File(old_url);
        if (!old_file.exists()) {
            throw new IOException("文件重命名失败，文件（" + old_file + "）不存在");
        }
        System.out.println(old_file.exists());

        String old_name = old_file.getName();
        // 获得父路径
        String parent = old_file.getParent();
        // 重命名
        String new_url = parent + "/" + new_name;
        File new_file = new File(new_url);
        old_file.renameTo(new_file);

        System.out.println("原文件：" + old_file.getName());
        System.out.println("新文件：" + new_file.getName());
        new_name = new_file.getName();
        old_name = old_file.getName();
        if (new_name.equals(old_name)) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * 删除文件
     * @param url
     * @return
     * @throws Exception
     */
    public static boolean deleteFile(String url) throws Exception {
        url = url.replace("\\", "/");
        File file = new File(url);

        if (file.isFile()) {
            if (file.exists()) {
                file.delete();
            }
        } else {
            throw new IOException("文件删除失败：（" + file + "）错误");
        }
        if (file.exists()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 创建文件夹
     * @param url
     * @return
     * @throws Exception
     */
    public static boolean createDirectory(String url) throws Exception {
        url = url.replace("\\", "/");
        File folder = new File(url);
        if (!folder.isDirectory()) {
            throw new IOException("创建文件夹失败：（" + folder + "）不是文件夹路径");
        }

        if (!folder.isFile()) {
            if (!folder.exists()) {
                folder.mkdirs();
            }
        }
        // 检测是否创建成功
        if (folder.isDirectory() && folder.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 创建文件
     * @param url
     * @return
     * @throws Exception
     */
    public static boolean createFile(String url) throws Exception {
        url = url.replace("\\", "/");
        File file = new File(url);
        if (file.isDirectory()) {
            throw new IOException("创建文件失败：（" + file + "）不是文件路径");
        }
        if (file.exists()) {
            throw new IOException("创建文件失败：（" + file + "）已经存在");
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        return true;
    }

    /**
     * 获取指定目录下的文件
     * @param directory
     * @return
     */
    public static List<String> getFiles(String directory){
        List<String> list = new ArrayList<>();
        File file = new File(directory);
        if (!file.exists()){
            return null;
        }
        if(file.isFile()){
            return null;
        }
        File[] files = file.listFiles();
        for (File file1 : files) {
            list.add(file1.toString());
        }
        return list;
    }

    /**
     * 移动文件到目录
     * @param srcFilePath
     * @param targetDir
     */
    public static void moveFileToDir(String srcFilePath, String targetDir){
        String targetFileName = new File(srcFilePath).getName();
        String targetFilePath = targetDir + File.separator + targetFileName;
        try {
            FileUtils.copyFile(srcFilePath,targetFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FileUtils.deleteFile(srcFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 监控目录
     * @param targetDir
     * （1）当文件修改时，会被调用两次，即输出两个相同的修改。
     * （2）不能对其子文件夹进行监控，只能提示目录被修改。
     * （3）无法对文件类型进行过滤。
     */
    public static void monitorDir(String targetDir){
        Path path = Paths.get(targetDir);
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);
            while (true) {
                final WatchKey key = watchService.take();
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    final WatchEvent.Kind<?> kind = watchEvent.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        continue;
                    }
                    //创建事件
                    if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                        System.out.println("[新建]");
                    }
                    //修改事件
                    if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                        System.out.println("修改]");
                    }
                    //删除事件
                    if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("[删除]");
                    }
                    // get the filename for the event
                    final WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                    final Path filename = watchEventPath.context();
                    // print it out
                    System.out.println(kind + " -> " + filename);

                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }

        } catch (IOException | InterruptedException ex) {
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
//        System.out.println(FileUtils.getFiles("D:\\temp\\report"));
//        FileUtils.moveFileToDir("D:\\temp\\report\\SPC000EK-001-Data.xls","D:\\temp\\report\\success");
//        System.out.println(FileUtils.getFiles("D:\\temp\\report"));
        FileUtils.monitorDir("D:\\temp\\report");
    }

}
