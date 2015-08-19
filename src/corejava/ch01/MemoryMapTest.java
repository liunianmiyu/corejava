package corejava.ch01;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/** 
 * @TODO   4中方式计算一个文件的CRC校验和
 * @author yicha
 * @date   2015年8月19日 
 */
public class MemoryMapTest {

	public static long checksumInputStream(Path filename) throws IOException{
		try(InputStream in = Files.newInputStream(filename)){
			CRC32 crc = new CRC32() ;
			int c ;
			while((c = in.read()) != -1){
				crc.update(c);
			}
			return crc.getValue();
		}
	}
	
	public static long checksumBufferedInputStream(Path filename) throws IOException{
		try(InputStream in = new BufferedInputStream(Files.newInputStream(filename))){
			CRC32 crc = new CRC32() ;
			int c ;
			while((c = in.read()) != -1){
				crc.update(c);
			}
			return crc.getValue() ;
		}
	}
	
	public static long checksumRandomAccessFile(Path filename) throws FileNotFoundException, IOException{
		try(RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")){
			long length = file.length() ;
			CRC32 crc = new CRC32() ;
			
			for(long p = 0 ; p < length ; p ++){
				file.seek(p);
				int c = file.readByte() ;
				crc.update(c);
			}
			return crc.getValue() ;
		}
	}
	
	public static long checksumMappedFile(Path filename) throws IOException{
		try(FileChannel channel = FileChannel.open(filename)){
			int length = (int) channel.size() ;
			CRC32 crc = new CRC32() ;
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length) ;
			
			for(int p = 0 ; p < length ; p ++){
				int c = buffer.get(p) ;
				crc.update(c);
			}
			return crc.getValue() ;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Path filename = Paths.get("employee.dat") ;
		long checksumInputStream;
		long checksumBufferedInputStream ;
		long checksumRandomAccessFile ;
		long checksumMappedFile ;
		
		long start1 = System.currentTimeMillis() ;
		checksumInputStream = checksumInputStream(filename) ;
		long end1 = System.currentTimeMillis() ;
		System.out.println(Long.toHexString(checksumInputStream));
		System.out.println(end1-start1);
		System.out.println();
		
		long start2 = System.currentTimeMillis() ;
		checksumBufferedInputStream = checksumBufferedInputStream(filename) ;
		long end2 = System.currentTimeMillis() ;
		System.out.println(Long.toHexString(checksumBufferedInputStream));
		System.out.println(end2-start2);
		System.out.println();
		
		long start3 = System.currentTimeMillis() ;
		checksumRandomAccessFile = checksumRandomAccessFile(filename) ;
		long end3 = System.currentTimeMillis() ;
		System.out.println(Long.toHexString(checksumRandomAccessFile));
		System.out.println(end3-start3);
		System.out.println();
		
		long start4 = System.currentTimeMillis() ;
		checksumMappedFile = checksumMappedFile(filename) ;
		long end4 = System.currentTimeMillis() ;
		System.out.println(Long.toHexString(checksumMappedFile));
		System.out.println(end4-start4);
	}

}
