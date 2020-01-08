import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class sortGoogleQuery {
	public static ArrayList<WebTree> lst = new ArrayList<WebTree>();
	public static ArrayList<WebTree> sort(ArrayList<WebTree> webTrees) throws IOException {
		
		//Tree Construction
		
		
		for (WebTree result : webTrees) {
			
			ArrayList<Keyword> keywords = new ArrayList<>();
			keywords.add(new Keyword("¶Â", 2));
			keywords.add(new Keyword("ª¯", 5));
			keywords.add(new Keyword("1", 5));
			
			lst.add(result);
			result.setPostOrderScore(keywords);
			result.eularPrintTree();
			
		}
		sort(lst);
		
		return lst;
	}

	
	public void sort(){
		quickSort(0, lst.size()-1);
	}
	
	
	private void quickSort(int leftbound, int rightbound){
		//implement quickSort algorithm
		int pivot = rightbound;
		int i = leftbound;
		int j = rightbound;
		
		
		if(leftbound != rightbound) {
		while(true) {
			while(lst.get(i).Tscore <= lst.get(pivot).Tscore && i < j) {
				i++;
			}
			while(lst.get(j).Tscore >= lst.get(pivot).Tscore && i < j) {
				j--;
			}
			if( i == j) {
				break;				
			}
			else {
				swap(i , j);
			}
		}
		swap(j , pivot);
		if(leftbound != i) {
			quickSort(leftbound , i-1);
		}
		if(rightbound != j) {
			quickSort(j+1 , rightbound);
		}
		}
		
		
		
		
	}
	private void swap(int aIndex, int bIndex){
		WebTree temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}
	public void output(){
		//TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<lst.size();i++){
			WebTree k = lst.get(i);
			if(i>0)sb.append(" ");
			sb.append(k.toString());
		}
		
		System.out.println(sb.toString());	
	}

	
}