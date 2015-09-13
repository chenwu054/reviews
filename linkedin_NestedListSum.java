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
