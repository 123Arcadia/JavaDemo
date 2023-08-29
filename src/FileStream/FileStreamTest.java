package FileStream;

import org.junit.Test;

import java.io.*;
import java.util.*;

public class FileStreamTest {
    public static void main(String[] args) {

        File file = new File("hello.txt");
        System.out.println("file = " + file.getAbsolutePath());
        //file = D:\javaProject\javaTesting\hello.txt

        String userDir = System.getProperty("user.dir");
        System.out.println("userDir = " + userDir);
        //userDir = D:\javaProject\javaTesting

        String userHome = System.getProperty("user.home");
        System.out.println("userHome = " + userHome);
        // userHome = C:\Users\zhangchenwei

        String userName = System.getProperty("user.name");
        System.out.println("userName = " + userName);
        //userName = zhangchenwei

        String osName = System.getProperty("os.name");
        System.out.println("osName = " + osName);
        //osName = Windows 11

        String osArch = System.getProperty("os.arch");
        System.out.println("osArch = " + osArch);
        //操作系统的架构 osArch = amd64

        String osVersion = System.getProperty("os.version");
        System.out.println("osVersion = " + osVersion);
        //操作系统的版本 osVersion = 10.0

        String javaCompiler = System.getProperty("java.compiler");
        System.out.println("javaCompiler = " + javaCompiler);
        // null

        /**
         * 办理所有
         */
        Properties pros = System.getProperties();
        for (Map.Entry<Object, Object> entry : pros.entrySet()) {
            System.out.println("entryKey = " + entry.getKey() +", val  = " + entry.getValue());
        }
        //entryKey = java.specification.version, val  = 17
        //entryKey = sun.cpu.isalist, val  = amd64
        //entryKey = sun.jnu.encoding, val  = GBK
        //entryKey = java.class.path, val  = D:\javaProject\javaTesting\out\production\javaTesting;D:\javaProject\javaTesting\lib\junit-4.13.1.jar;D:\javaProject\javaTesting\lib\hamcrest-core-1.3.jar;D:\javaProject\javaTesting\lib\lombok-1.18.22.jar
        //entryKey = java.vm.vendor, val  = Eclipse Adoptium
        //entryKey = sun.arch.data.model, val  = 64
        //entryKey = user.variant, val  =
        //entryKey = java.vendor.url, val  = https://adoptium.net/
        //entryKey = java.vm.specification.version, val  = 17
        //entryKey = os.name, val  = Windows 11
        //entryKey = sun.java.launcher, val  = SUN_STANDARD
        //entryKey = user.country, val  = CN
        //entryKey = sun.boot.library.path, val  = D:\jdk-17.0.4+8\bin
        //entryKey = sun.java.command, val  = FileStream.FileStreamTest
        //entryKey = jdk.debug, val  = release
        //entryKey = sun.cpu.endian, val  = little
        //entryKey = user.home, val  = C:\Users\zhangchenwei
        //entryKey = user.language, val  = zh
        //entryKey = java.specification.vendor, val  = Oracle Corporation
        //entryKey = java.version.date, val  = 2022-07-19
        //entryKey = java.home, val  = D:\jdk-17.0.4+8
        //entryKey = file.separator, val  = \
        //entryKey = java.vm.compressedOopsMode, val  = Zero based
        //entryKey = line.separator, val  =
        //
        //entryKey = java.vm.specification.vendor, val  = Oracle Corporation
        //entryKey = java.specification.name, val  = Java Platform API Specification
        //entryKey = user.script, val  =
        //entryKey = sun.management.compiler, val  = HotSpot 64-Bit Tiered Compilers
        //entryKey = java.runtime.version, val  = 17.0.4+8
        //entryKey = user.name, val  = zhangchenwei
        //entryKey = path.separator, val  = ;
        //entryKey = os.version, val  = 10.0
        //entryKey = java.runtime.name, val  = OpenJDK Runtime Environment
        //entryKey = file.encoding, val  = UTF-8
        //entryKey = java.vm.name, val  = OpenJDK 64-Bit Server VM
        //entryKey = java.vendor.version, val  = Temurin-17.0.4+8
        //entryKey = java.vendor.url.bug, val  = https://github.com/adoptium/adoptium-support/issues
        //entryKey = java.io.tmpdir, val  = C:\Users\ZHANGC~1\AppData\Local\Temp\
        //entryKey = java.version, val  = 17.0.4
        //entryKey = user.dir, val  = D:\javaProject\javaTesting
        //entryKey = os.arch, val  = amd64
        //entryKey = java.vm.specification.name, val  = Java Virtual Machine Specification
        //entryKey = sun.os.patch.level, val  =
        //entryKey = native.encoding, val  = GBK
        //entryKey = java.library.path, val  = D:\jdk-17.0.4+8\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\Program Files (x86)\Common Files\Oracle\Java\javapath;C:\Python27\;C:\Python27\Scripts;E:\java\jdk_8u381\jdk1.8.0_152\lib;E:\java\jdk_8u381\jdk1.8.0_152\bin;D:\CSProject\apache-maven-3.8.6-bin\apache-maven-3.8.6\bin;E:\Anaconda;E:\Anaconda\Library\mingw-w64\bin;E:\Anaconda\Library\\usr\bin;E:\Anaconda\Library\bin;E:\Anaconda\Scripts;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;C:\Windows\System32\OpenSSH\;C:\Program Files (x86)\NVIDIA Corporation\PhysX\Common;C:\Program Files\NVIDIA Corporation\NVIDIA NvDLISR;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;D:\Microsoft VS Code\bin;C:\x86_64-8.1.0-release-win32-seh-rt_v6-rev0\mingw64\bin;D:\Git\Git\cmd;C:\Cmake\bin;D:\MysqlServer8.0.31.0\bin;D:\谷歌下载\pdftk_server\PDFtk\bin\;C:\Program Files\Microsoft SQL Server\Client SDK\ODBC\170\Tools\Binn\;C:\Program Files (x86)\Microsoft SQL Server\150\Tools\Binn\;C:\Program Files\Microsoft SQL Server\150\Tools\Binn\;C:\Program Files\Microsoft SQL Server\150\DTS\Binn\;C:\Program Files\Azure Data Studio\bin;C:\Program Files (x86)\Microsoft SQL Server\150\DTS\Binn\;E:\MatLab\MalabInstaller\runtime\win64;E:\MatLab\MalabInstaller\bin;C:\Program Files\Docker\Docker\resources\bin;C:\Program Files\dotnet\;C:\ProgramData\chocolatey\bin;D:\CSinfocomm\spring-cloud-HeiMa\node.js\;D:\GoLand\GoSDK\bin;C:\Users\zhangchenwei\AppData\Local\Microsoft\WindowsApps;C:\x86_64-8.1.0-release-win32-seh-rt_v6-rev0\mingw64\bin;C:\Program Files\Azure Data Studio\bin;D:\PyCharm\PyCharm 2023.1\bin;;E:\java\jdk_8u381\jdk1.8.0_152\bin;D:\CSinfocomm\spring-cloud-HeiMa\node.js\node_modules\npm;D:\CSinfocomm\spring-cloud-HeiMa\node.js\node_global;D:\GoLand\GoSDK\bin;;.
        //entryKey = java.vm.info, val  = mixed mode, sharing
        //entryKey = java.vendor, val  = Eclipse Adoptium
        //entryKey = java.vm.version, val  = 17.0.4+8
        //entryKey = sun.io.unicode.encoding, val  = UnicodeLittle
        //entryKey = java.class.version, val  = 61.0

    }

    @Test
    public void testFileWriterTest () throws IOException {

//        File file = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello.txt");
        File file = new File("src/FileStream/testPod.txt");
        FileReader fr = new FileReader(file);

        int data = fr.read();
        while (data != -1) {

            System.out.print((char)data);
            data = fr.read();
        }
        fr.close();

    }

    @Test
    public void testFileWriterTest01 () throws IOException {

        File file = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello.txt");
        FileReader fr = new FileReader(file);

        char[] cbuf = new char[5];
        int len;
        while ((len = fr.read(cbuf)) != -1) {
            for (int i = 0; i < len; i++) {
                System.out.print(cbuf[i] + " ");
            }
        }
        if (fr != null)
            fr.close();

    }

    /**
     * FileWrite(file, [boolean])
     *       true:追加
     *       false:覆盖
     * @throws IOException
     */
    @Test
    public void testFileWriterTest02 () throws IOException {

        File file = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello1.txt");
        FileWriter fr = new FileWriter(file,false);

        fr.write("My name is zcw!\n");
        fr.write("You must be stanger!\n");

        fr.close();
    }

    //read and write
    @Test
    public void testFileWriterTest03 () throws IOException {

        File file01 = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello3.txt");
        File file02 = new File("D:\\javaProject\\javaTesting\\src\\FileStream\\hello1.txt");

        FileWriter fw = new FileWriter(file01);
        FileReader fr = new FileReader(file02);

        char[] buf = new char[5];
        int len;
        while ((len = fr.read(buf)) != -1) {
            fw.write(buf, 0, len);
        }

        fw.flush();
        fw.write("hello, Arcadia\n");
        fw.close();
        fr.close();
    }

    @Test
    public void test_InputStrem() throws IOException {
        File file = new File("src/FileStream/hello.txt");
        FileInputStream fi = new FileInputStream(file);

        byte[] bytes = new byte[13];
        int len;
        while ((len = fi.read(bytes)) != -1) {
            System.out.println(new String(bytes, 0, len));
        }
    }


    @Test
    public void test_BUfferStream() throws Exception{
        FileInputStream fin = new FileInputStream("src/FileStream/hello1.txt");
        FileOutputStream fou = new FileOutputStream("src/FileStream/hello1_out.txt");
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bou = new BufferedOutputStream(fou);

        byte[] buffer = new byte[10];
        int len;
        while ((len=bin.read(buffer))!=-1){
            System.out.print(new String(buffer, 0, len) + "-");
            bou.write(buffer,0,len);

        }
        bou.flush();
//        if (fou != null || fin != null) {
//            fou.close();
//            fin.close();
//        }
        fou.close();
        fin.close();
    }

    @Test
    public void testPod() throws Exception{
        FileInputStream fin = new FileInputStream("src/FileStream/testPod.txt");
        FileOutputStream fou = new FileOutputStream("src/FileStream/testOutResPod.txt");
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bou = new BufferedOutputStream(fou);

        byte[] buffer = new byte[1024];
        int len;
        while ((len=bin.read(buffer))!=-1){
//            System.out.print(new String(buffer, 0, len) + "-");
            String str = new String(buffer, 0, len);
            if (str.contains("pod=")) {
                bou.write(buffer, 0, len);
            }

        }
        bou.flush();
//        if (fou != null || fin != null) {
//            fou.close();
//            fin.close();
//        }
        fou.close();
        fin.close();
    }

    @Test
    public void pod() {
        String fileName = "src/FileStream/podTest.txt"; // 替换为你的文件名
//        String podString = "pod"; // 替换为你要筛选的字符串

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

//            FileOutputStream fou = new FileOutputStream("src/FileStream/testOutResPod.txt");
//            BufferedOutputStream bou = new BufferedOutputStream(fou);
//            BufferedWriter bw = new BufferedWriter(new FileWriter("src/FileStream/testOutResPod.txt"));

            Set<String> set = new HashSet<>();
            String line;
            Map<String, Integer> res = new HashMap<>();
            while ((line = br.readLine()) != null) {
                if (line.equals("DBDT323115")) {
                    System.out.println("line = " + line);
                }
//                String[] str = line.split("=");
//                int num = 0;
//                if (str.length <= 1) {
//
//                } else {
//                    num = Integer.parseInt(str[1]);
//                }
//
//                if (res.containsKey(str[0])) {
//                    res.put(str[0], res.getOrDefault(str[0], 0) + 1);
//                } else {
//                    res.put(str[0], num);
//                }

                if (set.contains(line)) {
                    continue;
                }
                set.add(line);
            }
            System.out.println(set.size());
            System.out.println(set);
            System.out.println("----------");
            System.out.println(res.size());
            for (String pod : res.keySet()) {
                System.out.println(pod + ", " + res.get(pod));
            }

            br.close();
//            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // res: 39种pod
        // res: CHCXO089S 52种pod
        // res: CHCYG664E 52种pod
        // res: COSH812305135S 52种pod
        // res: SJJOL155452334E 52种pod

        //932个出口箱：54种pod

//        pod=IDJKT,

        //54
        //JPTOY, 4
        //JPSMZ, 6
        //CNNBO, 48
        //KRKPO, 87
        //CNNSA, 3
        //JPNGO, 67
        //CNNGB, 68NSA
        //CNDCB, 15
        //DEHAM, 22
        //JPHKT, 31
        //CNSHA, 30
        //CNNS2, 28
        //CNDC8, 2
        //IDJKT, 12
        //JPKIJ, 5
        //CNSHK, 70
        //GRPIR, 29
        //CNQZJ, 19
        //KRKUV, 3
        //NLRTM, 25
        //CNYIK, 64
        //ITTRS, 2
        //ILHFA, 6
        //EGPSE, 45
        //CNQZ8, 38
        //null, 30
        //IDPNJ, 4
        //KRKAN, 18
        //KRPU3, 2
        //KRPU2, 2
        //KRPU1, 3
        //JPYKK, 16
        //EGPSD, 3
        //PHMNS, 6
        //HRRJK, 11
        //SGSIN, 60
        //JPYOK, 37
        //HKHKG, 9
        //JPMOJ, 27
        //CNLYG, 2
        //PHMNN, 15
        //JPTYO, 24
        //VNVUT, 23
        //JPUKB, 20
        //BEANR, 28
        //KRPUS, 48
        //CNTAO, 52
        //SIKOP, 7
        //MYPKG, 11
        //CNSH8, 77
        //KRUSN, 8
        //JPOSA, 11
        //CNBAY, 6
        //THLCH, 22
        //
        //Process finished with exit code 0

    }

    @Test
    public void t() {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1,2,3, 4, 9 ,9 , 0, 8));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1,2,3, 4 ,5, 8));
        Map<String, List<Integer>> map = new LinkedHashMap<>();
        map.put("zcw1", list1);
        map.put("zcw2", list2);
        map.put("zcw3", list3);

        Set<Map.Entry<String, List<Integer>>> entrySet = map.entrySet();
        ArrayList<Map.Entry<String, List<Integer>>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<String, List<Integer>>>() {
            @Override
            public int compare(Map.Entry<String, List<Integer>> o1, Map.Entry<String, List<Integer>> o2) {
                if (o1.getValue().size() < o2.getValue().size()) {
                    return -1;
                } else if (o1.getValue().size() > o2.getValue().size()){
                    return 1;
                }

                return 0;
            }
        });
        System.out.println("map = " + map);
        System.out.println("list = " + list);
        for (Map.Entry<String, List<Integer>> entry : list) {
            String key = entry.getKey();
            List<Integer> value = entry.getValue();
            map.put(key, value);
        }
        System.out.println("map = " + map);

    }


    /**
     * 只针对Map<K, V></>, 不支持复合结构
     */
    public class MapUtil {
        public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
            List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
                public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                    return (o1.getValue()).compareTo(o2.getValue());
                }
            });

            Map<K, V> result = new LinkedHashMap<K, V>();
            for (Map.Entry<K, V> entry : list) {
                result.put(entry.getKey(), entry.getValue());
            }
            return result;
        }
    }

}
