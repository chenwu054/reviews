/*
http://www.mitbbs.com/article_t/JobHunting/32903111.html

nested int reversed weighted sum

Compute the reverse depth sum of a nested list meaning the reverse depth of
each node (ie, 1 for leafs, 2 for parents of leafs, 3 for parents of parents
of leafs, etc.) times the value of that node.

{{1,2}, 1, {2, {2,1}}} = ?
{1,2}的weight应该是1 还是2?按照定义它应该是leaf吧 所以应该是
(1+2)*2 + 1 * 3 + (2*2 + (2+1) * 1) = 13?
*/
public static int getReverseNestedListSum(List<Object> list) {
	int ret = 0, temp = 0;
	List<Object> cur = new ArrayList<Object>(list);
	List<Object> next = new ArrayList<Object>();
	List<Object> tempList = null;
	List<Integer> levels = new ArrayList<Integer>();
	while(!cur.isEmpty()) {
		Iterator<Object> it = cur.iterator();
		temp = 0;
		while(it.hasNext()) {
			Object obj = it.next();
			if(obj instanceof List) {
				Iterator<Object> nextIt = ((List<Object>)obj).iterator();
				while(nextIt.hasNext()) {
					next.add(nextIt.next());
				}
			}
			else if(obj instanceof Integer) {
				temp += (Integer)obj;
			}
		}
		tempList = cur;
		cur = next;
		next = tempList;
		next.clear();
		levels.add(temp);
	}
	for(int i = 0; i < levels.size(); i++) {
		System.out.println("level " + (i + 1) + ": " + levels.get(i));
		ret += (i + 1) * levels.get(levels.size() - 1 - i);
	}
	return ret;
}
