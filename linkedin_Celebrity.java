/*
celebrity
find the celebrity. If does not exist, return -1;
otherwise return the index of the celebrity
solution: i is index of potential celebrity, j is index of the next person to be tested.
test person i and person j:
if i knows j and j knows i -> neither of them is celebrity
if i knows j and j does not know i -> i is replaced by j since j might be the celebrity
if i does not know j and j knows i -> j increases since i might be the celebrity
if i does not know j and j does not know i -> neither j or i is celebrity, both get new indices.
last round to test the potential i, the celebrity index.
*/
public static int findCelebrity(boolean[][] know){
		if(know==null){
			throw new RuntimeException("invalid input");
		}
		int n = know.length;
		if(n<=1){
			return -1;
		}
		int i=0,j=1;
		boolean iKnowsJ, jKnowsI;
		while(i<n && j<n){
			iKnowsJ=know[i][j];
			jKnowsI=know[j][i];
			if(iKnowsJ){
				if(jKnowsI){
					i=Math.max(i,j)+1;
					j=i+1;
				}
				else{
					int temp = Math.max(i,j);
					i=j;
					j=temp+1;
				}
			}
			else{
				if(jKnowsI){
					j=Math.max(i,j)+1;
				}
				else{
					i=Math.max(i,j)+1;
					j=i+1;
				}
			}
		}
		if(i<n){
			//verify
			for(int k=0;k<n;k++){
				if(k==i){
					continue;
				}
				if(!know[k][i] || know[i][k]){
					return -1;
				}
			}
			return i;
		}
		return -1;
	}
