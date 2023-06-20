package ExcelDes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV操作工具类
 * @author zd05.chnet
 *
 */
public class Csvop {
	/**
	 * 导入vsc
	 * @param inputStream
	 * @return
	 */
	public static List<String> importCsv(InputStream inputStream) {
		List<String> dataList = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(inputStream));
			String line = "";
			while ((line = br.readLine()) != null) {
				dataList.add(line);
			}
		} catch (Exception e) {
		} finally {
			if (br != null) {
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return dataList;
	}
	
	/**
	 * 导出csv
	 * @param file
	 * @param dataList
	 * @return
	 */
	public static boolean exportCsv(File file, List<String> dataList) {
		boolean isSucess = false;
		FileOutputStream out = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			out = new FileOutputStream(file);
			osw = new OutputStreamWriter(out);
			bw = new BufferedWriter(osw);
			if (dataList != null && !dataList.isEmpty()) {
				for (String data : dataList) {
					bw.append(data).append("\r");
				}
			}
			isSucess = true;
		} catch (Exception e) {
			isSucess = false;
		} finally {
			if (bw != null) {
				try {
					bw.close();
					bw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (osw != null) {
				try {
					osw.close();
					osw = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return isSucess;
	}
	
	
	
}
