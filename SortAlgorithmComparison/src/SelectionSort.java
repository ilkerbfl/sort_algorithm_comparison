
public class SelectionSort {
	
	private long algorithmStartTime;
	private long algorithmFinishTime;
	
	public static void main(String[] args) {
		SelectionSort ss= new SelectionSort();
		int[] array = {7,3,4,2,6,8};
		ss.sort(array);
		for (int i : array) {
			System.out.println(i);
		}
	}
	
	public long sort(int[] array){
		algorithmStartTime = System.nanoTime();
		selectionSort(array);
		algorithmFinishTime = System.nanoTime();
		return  algorithmFinishTime-algorithmStartTime;
	}

	/**
	 * @param arrayWillBeSorted sıralanmak istenen array
	 * verilen arrayı selection sort algoritması kullanarak listeler
	 */
	public void selectionSort(int[] arrayWillBeSorted) {
		for (int i = 0; i < arrayWillBeSorted.length - 1; i++) {
			int cursor = i;
			
			//bir dıştaki for döngüsünün bulunduğu noktanın bir sonrasından başlayarak arrayın kalan kısmında
			// bir dış döngünün gösterdiği kısımda daha küçük bir sayı varmı diye teker teker kontrol eder.
			for (int j = i + 1; j < arrayWillBeSorted.length; j++) {
				//Eğer arrayın kalan kısmında bir j indeksinde daha küçün bir değer bulunursa cursor bu nokta ile değiştirir.
				//Döngü bittiğinde en küçük değer cursorın gösterdiği nokta olur
				if (arrayWillBeSorted[j] < arrayWillBeSorted[cursor]) {
					cursor = j;// en küçük değerin bulunduğu index
				}
			}
			//Basit swap işlemi
			SortUtil.swap(arrayWillBeSorted, cursor, i);
		}
	}

}
