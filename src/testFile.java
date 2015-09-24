
public class testFile{
	private static StringBuffer buf = new StringBuffer();
	public static void main(String args[]){
		buf.append(1);
		buf.append(2);
		buf.append(3);
		buf.reverse();
		
		//System.out.println(buf);
		//System.out.println(buf.length());
		String str = ")";
		System.out.println(str.contentEquals("'"));
	}
}
