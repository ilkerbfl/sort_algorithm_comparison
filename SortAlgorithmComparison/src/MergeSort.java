
public class MergeSort {

	private int[] array;
	private int[] temporaryArrayForMergeOperation;
	private int length;
	
	public static void main(String[] args) {
		int[] arrayForDebug={7,2,4,8};
		MergeSort merge= new MergeSort();
		merge.sort(arrayForDebug);
	}

	public  long sort(int arrayWillBeSorted[]) {
		this.array = arrayWillBeSorted;
		this.length = arrayWillBeSorted.length;
		this.temporaryArrayForMergeOperation = new int[length];
		long startTime = System.nanoTime();
		doMergeSort(0, length - 1);
		long finishTime = System.nanoTime();
		return finishTime-startTime;
	}

	/**
	 * @param lowerIndex merge sort işlemin uygulanacagı indexin küçük olanı
	 * @param higherIndex merge sort işlemin uygulanacagı indexin büyük olanı olanı
	 */
	private void doMergeSort(int lowerIndex, int higherIndex) {
		
		if (lowerIndex < higherIndex) {
			int middle = lowerIndex + (higherIndex - lowerIndex) / 2; //ortadaki eleman
			// sol taraf için merge sort çağrılır
			doMergeSort(lowerIndex, middle);
			// sağ taraf için merge sort çağrılır
			doMergeSort(middle + 1, higherIndex);
			// sıralama işlemi bittikten sonra iki sıralı arrayı merge etmek için gerekli metot
			mergeParts(lowerIndex, middle, higherIndex);
		}
	}

	/**
	 * @param lowerIndex küçük index , yani sol taraftaki arrayın en küçüğü
	 * @param middle  iki arrayın ortasındaki index , 
	 * @param higherIndex büyük index yani sağ taraftaki arrayın en büyüğü
	 */
	private void mergeParts(int lowerIndex, int middle, int higherIndex) {
		//iki kısmıda geçici arraya yazdıran kod bloğu
		for (int i = lowerIndex; i <= higherIndex; i++) {
			temporaryArrayForMergeOperation[i] = array[i];
		}
		int i =lowerIndex,k=lowerIndex;
		int j = middle + 1;
		//Soldan veya sağdan hangisi küçükse onu orjinal arraye yazdıran kısım
		while (i <= middle && j <= higherIndex) {
			if (temporaryArrayForMergeOperation[i] <= temporaryArrayForMergeOperation[j]) {
				//soldaki küçük olduğu için orjinal arraye kopyalandı
				array[k] = temporaryArrayForMergeOperation[i];
				i++;
			} else {
				//Sağdaki küçük olduğu için orjinal arraye kopyalandı
				array[k] = temporaryArrayForMergeOperation[j];
				j++;
			}
			k++;
		}
		//Döngüden çıktığında i middledan küçükse , i orta noktaya yani kendi üst limitine gelene kadar 
		//geçici arrayin i indexindeki tüm değerleri orjinal array kopyalar.
		while (i <= middle) {
			array[k] = temporaryArrayForMergeOperation[i];
			k++;
			i++;
		}

	}
}
