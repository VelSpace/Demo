package controller;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String cdr = "~CDR_3600000_User Hung Up (exB-1)_null_ERROR: header not found";
//		String cdrarray[] = cdr.split("_");
//		for(int i=0;i<cdrarray.length;i++)
//		{
//			System.out.println("index: "+i+" : "+cdrarray[i]);
//		}
		HashMap<Integer, Integer> freqMap = new HashMap<>(); 
			Integer [] arr = {1,2,3,4,3,3,3,2,4,5,2,3,2,4,5};
		for(int i:arr) {
			freqMap.put(i, freqMap.getOrDefault(i, 0)+1);
		}
		List<Integer> list = Arrays.asList(arr);
		//Collections.sort(list,new sortComparator(freqMap) {});
		
		Collections.sort(list,new Comparator<Integer>() {

			@Override
			public int compare(Integer k1, Integer k2) {
				int freqcompare = freqMap.get(k1).compareTo(freqMap.get(k2));
				int valuecompare = k1.compareTo(k2);
				if(freqcompare == 0) {
					return valuecompare;
				}
				return freqcompare;
			}
			
		});
		for(int i:list) {
			System.out.print(i+" ");
		}
	}
	
	// 30 40 9 500 3 => 500 40 30 9 3
	

}

class sortComparator implements Comparator<	Integer>{
	
	
	
	private HashMap<Integer, Integer> freqMap;
	
	public sortComparator(HashMap<Integer, Integer> freqMap) {
		this.freqMap = freqMap;
	}
	@Override
	public int compare(Integer k1, Integer k2) {
		System.out.println();
		int freqCompare = freqMap.get(k1).compareTo(freqMap.get(k2));
		int valueCompare = k1.compareTo(k2);
		if(freqCompare == 0) {
			return valueCompare;
		}
		return freqCompare;
	}
	
}
