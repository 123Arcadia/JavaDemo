package FileStream.CreateFile;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class file {
    /**
     * 创建文件的4种方法分享public boolean createNewFile(): 用于检测文件是否存在，如不存在时，则创建文件
     *
     * getName()：获取文件名字。
     * getAbsolutePath()：获取文件的绝对路径。
     * getParent()：获取文件的父级目录。
     * length()：获取文件的大小（以字节为单位）。
     * exists()：判断文件是否存在。
     * isFile()：判断是否为一个文件。
     * isDirectory()：判断是否为一个目录。
     * delete()：删除文件
     * mkdirs()：创建多级目录
     * mkdir()：创建父目录(一级目录)
     */
    @Test
    public void createFile() {
        try {
            File dir = new File("dir");    // 获取目录“dir”对应的File对象
            File file1 = new File(dir, "testFile1.txt");
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        在“dir”目录(相对路径)下新建文件“testFile1.txt”。

        try {
            File file2 = new File("dir", "testFile2.txt");
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        在“dir”目录(相对路径)下新建文件“testFile2.txt”。

        try {
            File file3 = new File("/test/test3.txt");   //linux创建文件
            //File file3 = new File("D:/dir/test3.txt");   windows创建文件
            file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        新建文件“/test/test3.txt”(绝对路径)
//                在windows下可以通过以下代码新建文件"D:/dir/test3.txt"。

        try {
            URI uri = new URI("file:/test/test3.txt");
            File file4 = new File(uri);
            file4.createNewFile();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
