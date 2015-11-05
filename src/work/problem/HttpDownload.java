package work.problem;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 说明
 * 利用httpclient下载文件
 * maven依赖
 * <dependency>
*			<groupId>org.apache.httpcomponents</groupId>
*			<artifactId>httpclient</artifactId>
*			<version>4.0.1</version>
*		</dependency>
*  可下载http文件、图片、压缩文件
*  bug：获取response header中Content-Disposition中filename中文乱码问题
 * @author tanjundong
 *
 */
public class HttpDownload {

	public static final int cache = 10 * 1024;
	public static final boolean isWindows;
	public static final String splash;
	public static final String root;
	static {
		if (System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows")) {
			isWindows = true;
			splash = "\\";
			root="D:";
		} else {
			isWindows = false;
			splash = "/";
			root="/search";
		}
	}
	
	/**
	 * 根据url下载文件，文件名从response header头中获取
	 * @param url
	 * @return
	 */
	public static String download(String url) {
		return download(url, null);
	}

	public static void download2(String url, String filepath){
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		try {
			HttpResponse response = httpClient.execute(httpGet);
			
			int statusCode = response.getStatusLine().getStatusCode();
			
			if(statusCode == HttpStatus.SC_OK){
				System.out.println(filepath.replace("jpg", "tmp"));
				File storeFile = new File(filepath.replace("jpg", "tmp"));
				FileOutputStream outStream = new FileOutputStream(storeFile);
				InputStream is = response.getEntity().getContent();
				byte[] b = new byte[2048];
				int j = 0;
				while((j=is.read(b)) != -1){
					outStream.write(b, 0, j);
				}
				if(outStream != null){
					outStream.close();
				}
				storeFile.renameTo(new File(filepath));
				b = null;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据url下载文件，保存到filepath中
	 * @param url
	 * @param filepath
	 * @return
	 */
	public static String download(String url, String filepath) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = client.execute(httpget);

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			
			if (filepath == null)
				filepath = getFilePath(response);
			File file = new File(filepath);
			file.getParentFile().mkdirs();
			FileOutputStream fileout = new FileOutputStream(file);
			/**
			 * 根据实际运行效果 设置缓冲区大小
			 */
			byte[] buffer=new byte[cache];
			int ch = 0;
			int len = 0;
			while ((ch = is.read(buffer)) != -1) {
				len += ch;
				fileout.write(buffer,0,ch);
			}
			System.out.println("读取大小：" + len);
			System.out.println("文件大小：" + file.length());
			is.close();
			fileout.flush();
			fileout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取response要下载的文件的默认路径
	 * @param response
	 * @return
	 */
	public static String getFilePath(HttpResponse response) {
		String filepath = root + splash;
		String filename = getFileName(response);

		if (filename != null) {
			filepath += filename;
		} else {
			filepath += getRandomFileName();
		}
		return filepath;
	}
	/**
	 * 获取response header中Content-Disposition中的filename值
	 * @param response
	 * @return
	 */
	public static String getFileName(HttpResponse response) {
		Header contentHeader = response.getFirstHeader("Content-Disposition");
		String filename = null;
		if (contentHeader != null) {
			HeaderElement[] values = contentHeader.getElements();
			if (values.length == 1) {
				NameValuePair param = values[0].getParameterByName("filename");
				if (param != null) {
					try {
						//filename = new String(param.getValue().toString().getBytes(), "utf-8");
						//filename=URLDecoder.decode(param.getValue(),"utf-8");
						filename = param.getValue();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return filename;
	}
	/**
	 * 获取随机文件名
	 * @return
	 */
	public static String getRandomFileName() {
		return String.valueOf(System.currentTimeMillis());
	}
	public static void outHeaders(HttpResponse response) {
		Header[] headers = response.getAllHeaders();
		for (int i = 0; i < headers.length; i++) {
			System.out.println(headers[i]);
		}
	}
	public static void main(String[] args) {
//		String url = "http://bbs.btwuji.com/job.php?action=download&pid=tpc&tid=320678&aid=216617";
		String url1="http://www.dy1000.com/Uploads/vod/2015-08-03/55be96f3bae8b.jpg";
		String url2="http://www.muu.com.cn//img/o/063aa8db5c3c74a4820b342c12bf4fb39aaf2ddd9c300be6d3d08edc62c524e3140a702572942b78.jpg";
//		String filepath = "D:\\test\\a.torrent";
		String filepath = "D:\\test\\b.jpg";
		//HttpDownload.download(url2, filepath);
		HttpDownload.download2(url2, filepath);
	}
}
