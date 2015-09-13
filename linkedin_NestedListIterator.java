/*
Implementa flatten iterator for NestedList
eg: {{1,2},3,{4,{5,6}}}
不断调用iterator的next()返回的序列是 1 2 3 4 5 6， 不过给的都是Generic Types的Interface

Solution: 2 stacks
*/
public class NestedListIterator<T> implements Iterator<T> {

	/**
	 * @param args
	 */
	static NestedListIterator<Integer> it;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		List<Object> list1 = new ArrayList<Object>();
		list1.add(1);list1.add(null);list1.add(2);
		List<Object> list2 = new ArrayList<Object>();
		list2.add(4);
		List<Object> list3 = new ArrayList<Object>();
		list3.add(5);list3.add(6);
		List<Object> list4 = new ArrayList<Object>();
		List<Object> list5 = new ArrayList<Object>();
		List<Object> list6 = new ArrayList<Object>();
		list1.add(list6);
		list.add(list1);
		list.add(3);
		list4.add(list5);
		list.add(list4);
		list2.add(list3);
		list.add(list2);
		
		it = new NestedListIterator<Integer>(list);
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		
	}
	Stack<Object> inStack;
	Stack<Object> outStack;
	public NestedListIterator(List<Object> list) {
		inStack = new Stack<Object>();
		outStack = new Stack<Object>();
		for(Object o : list) {
			if(o != null) {
				inStack.push(o);
			}
		}
		while(!inStack.empty()) {
			outStack.push(inStack.pop());
		}
	}
	@Override
	public boolean hasNext() {
		while(!outStack.empty() && (outStack.peek() instanceof List)) {	
			Object top = outStack.pop();
			if(top instanceof List) {
				List<Object> next = (List<Object>)top;
				for(Object o : next) {
					if(o != null) {
						inStack.push(o);
					}
				}
				while(!inStack.empty()) {
					outStack.push(inStack.pop());
				}
			}
		}
		return !outStack.empty();
	}
	@Override
	public T next() {
		if(hasNext()) {
			return (T)outStack.pop();
		}
		return null;
	}
	
	@Override
	public void remove() {
		next();
	}
}
