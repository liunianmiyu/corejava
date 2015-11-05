package work.problem;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.*;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * @TODO
 * @author yicha
 * @date 2015年9月23日
 */
public class HttpClientDownload {

	public static void download(String url, String filepath) {
		HttpClient httpclient = new HttpClient();
		GetMethod get = new GetMethod(url);
		try {
			httpclient.executeMethod(get);

			File file = new File(filepath);

			OutputStream out = new FileOutputStream(file);

			InputStream is = get.getResponseBodyAsStream();
			byte[] b = new byte[2048];
			int i = 0;
			int len = 0;
			while ((i = is.read(b)) != -1) {
				len += i;
				out.write(b, 0, i);
			}
			System.out.println("读取大小：" + len);
			System.out.println("文件大小：" + file.length());
			is.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void downloadB(String urlStr, String filename)
			throws Exception {
		// 构造URL
		URL url = new URL(urlStr);
		// 打开连接
		URLConnection con = url.openConnection();
		con.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)");
		con.connect();
		Map<String, List<String>> headMap = con.getHeaderFields();
		parseMap(headMap);

		// 输入流
		InputStream is = con.getInputStream();

		String encodingType = con.getHeaderField("Content-Encoding");
		if (null != encodingType && encodingType.equals("gzip")) {
			GZIPInputStream gis = new GZIPInputStream(is);
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			OutputStream os = new FileOutputStream(filename);
			// 开始读取
			while ((len = gis.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			gis.close();
			os.close();
			is.close();
			
		} else {

			File file = new File(filename);
			byte[] b_byte = new byte[1024];
			int byteRead = 0;
			int len = 0;
			FileOutputStream fout = new FileOutputStream(file);
			while ((byteRead = is.read(b_byte)) > 0) {
				len += byteRead;
				fout.write(b_byte, 0, byteRead);
			}
			System.out.println("下载的大小：" + len);
			System.out.println("文件的大小：" + file.length());
			fout.flush();
			fout.close();
			is.close();
		}
		// saveStream(is, filename);

	}

	/**
	 * 将流写入文件
	 * 
	 * @param inputStream
	 *            流数据
	 * @param fileName
	 *            文件路径
	 * @return 写入是否成功
	 */
	public static boolean saveStream(InputStream inputStream, String fileName) {

		boolean res = false;

		File file = new File(fileName);

		// 判断路径是否存在，并创建
		File path = new File(file.getParent());
		if (!path.exists())
			path.mkdirs();
		// 判断能否获取权限
		DataOutputStream dataoutputstream = null;
		try {
			dataoutputstream = new DataOutputStream(new FileOutputStream(file));
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			file.delete();
			return false;
		}

		int count = 0;
		long complete = 0;
		int everyK = 100;
		long wushik = everyK * 1024;
		long startTime = System.currentTimeMillis();
		long useTime;
		// 开始写文件
		int length = 0;
		try {
			byte abyte0[] = new byte[2048];

			// for (int i = 0; (i = inputStream.read(abyte0)) != -1;) {
			int i;
			while ((i = inputStream.read(abyte0)) != -1) {
				length += i;
				dataoutputstream.write(abyte0, 0, i);

				complete += i;
				if (complete >= wushik) {
					complete = 0;
					count++;
					useTime = (System.currentTimeMillis() - startTime) / 1000;
					if (useTime > 0) {
						long rate = everyK * count / useTime;
						System.out.println("已完成： " + everyK * count
								+ " k  平均速率:" + rate + "Kb/s");
					}
				}
			}

			System.out.println(fileName + "  读取大小为: " + length);
			System.out.println(fileName + "  文件大小为: " + file.length());
			System.out.println(fileName + "  保存成功！");
			res = true;
		} catch (Exception exception1) {
			System.out.println(exception1.getMessage());
			res = false;
		} finally {
			try {
				dataoutputstream.flush();
				dataoutputstream.close();
				inputStream.close();
			} catch (Exception exception2) {
				System.out.println(exception2.getMessage());
				file.delete();
				res = false;
			}
		}

		return res;
	}

	private static void parseMap(Map<String, List<String>> map) {
		Iterator<Entry<String, List<String>>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, List<String>> entry = it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	public static void main(String[] args) throws Exception {
		String url = "http://www.muu.com.cn//img/o/48274d4b33b28e11f0826483d9443e4cffcf761feaee13e3243a662241801074267b3c84be23326c.jpg";
		// download(url, "D:\\test\\a.jpg");
		downloadB(url, "D:\\test\\c.jpg");
	}
}
