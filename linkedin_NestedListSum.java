/*

*/
public class NestedListSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "{{1, 1 }, -2, {1,1}}";
		int ret = nestedListSum(str);
		System.out.println(ret);
	}
	public static int nestedListSum(String str) {
		if(str == null || str.length() == 0) {
			return 0;
		}
		int[] sum = {0};
		int[] start = {0};
		recursiveNestedListSum(str, start, sum, 0);
		return sum[0];
	}
	public static  void recursiveNestedListSum(String str, int[] start, int[] sum, int level) {
		if(start[0] >= str.length()) {
			return;
		}
		int idx = start[0];
		int num = 0;
		boolean neg = false;
		while(idx < str.length()) {
			char c = str.charAt(idx);
			if(c == ',') {
				sum[0] = sum[0] + level * num * (neg ? -1 : 1);
				num = 0;
			}
			else if(c == '{') {
				start[0] = idx + 1;
				recursiveNestedListSum(str, start, sum, level + 1);
				idx = start[0];
			}
			else if(c == '}') {
				sum[0] += level * num * (neg ? -1 : 1);
				num = 0;
				start[0] = idx;
				return;
			}
			else if(c == '+' || c == '-') {
				neg = c == '-';
			}
			else if(c >= '0' && c <= '9') {
				num = num * 10 + (c - '0');
			}
			
			idx++;
		}
		start[0] = idx;
	}
}

public static int getWeight(List<Object> list){
	if(list==null || list.size()==0){
		throw new RuntimeException("invalid input");
	}
	Queue<Object> q = new LinkedList<Object>(list);
	Queue<Object> next =  new LinkedList<Object>();
	Queue<Object> temp = null;
	Integer ret = 0;
	int weight = 0;
	Object cur = null;
	while(q.size()>0){
		weight++;
		while(q.size()>0){
			cur = q.poll();
			if(cur instanceof List){
				next.addAll((List)cur);
			}
			else{
				ret += weight*(Integer)cur;
			}
		}
		temp=next;
		next=q;
		q=temp;
	}
	return ret;
}

public int getReverse(List<Object> list) {
	if(list == null || list.size() == 0) {
		return 0;
	}
	Queue<Object> q = new LinkedList<Object>();
	Queue<Object> next = new LinkedList<Object>();
	Queue<Object> temp = null;
	ArrayList<Integer> levels = new ArrayList<Integer>();
	q.add(list);
	while(!q.isEmpty()) {
		int sum = 0;
		while(!q.isEmpty()) {
			Object cur = q.poll();
			if(cur instanceof Integer) {
				sum += (Integer)cur;
			}
			else if(cur instanceof List) {
				for(Object o : (List<Object>)cur) {
					next.add(o);
				}
			}
		}
		levels.add(sum);
		temp = q;
		q = next;
		next =temp;
	}
	int sum = 0;
	for(int i = 0; i < levels.size(); i++) {
		sum += levels.get(i) * (1 + i);
	}
	return sum;
}
