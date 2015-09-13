/*

*/
public class TwoSum {
	Map<Integer,Integer> map;
	public TwoSum() {
		map = new HashMap<Integer, Integer>();
	}

	public void store(int num) {
		if(!map.containsKey(num)) {
			map.put(num, 1);
		}
		else {
			map.put(num, map.get(num) + 1);
		}
	}
	public boolean test(int target) {
		for(Integer k : map.keySet()) {
			if(map.containsKey(target - k)){
				if(2 * k == target) {
					return map.get(k) >= 2;
				}
				return true;
			}
		}
		return false;
	}
}
