
public class ListNode {
	private String data;
	private ListNode nextNode;
	
	public ListNode (String c){
		data = c;
		nextNode = null;
	}

	public String getData(){
		return data;
	}
	
	public void setNext(ListNode T){
		nextNode = T;
	}
	
	public ListNode getNext(){
		return nextNode;
	}
}
