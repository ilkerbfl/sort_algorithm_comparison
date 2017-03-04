
public class QuickSort {

	/**
	 * @param sıralanacak array
	 * @return sıralaması zamanı nanosaniye
	 */
	public long sort(int[] array) {
		long startTime = System.nanoTime();
		quicksort(array, 0, array.length - 1);
		long finishTime = System.nanoTime();
		return finishTime - startTime;
	}

	/**
	 * @param array sıralanması istenilen array
	 * @param beginIndex arrayın sıralanacak kısmının başlangıç indexi
	 * @param end arrayın sıralanacak kısmının bitiş indexi
	 */
	public void quicksort(int[] array, int beginIndex, int end) {
		//Base kısıt, 
		if (beginIndex >= end || beginIndex < 0 || end > array.length - 1) {
			return;
		}
		//Parçala , sırala
		int pivot = partition(array, beginIndex, end);
		quicksort(array, beginIndex, pivot - 1);
		quicksort(array, pivot + 1, end);
	}

	/**
	 * @param array bölünecek array
	 * @param beginIndex bölünecek arrayın  kısmının başlangıç indexi
	 * @param endIndex bölünecek arrayın  kısmının bitiş indexi
	 * @return
	 */
	public int partition(int[] array, int beginIndex, int endIndex) {

		// Pivot random olması gerektiği için random cursor yapıldı
		int random = beginIndex + ((int) Math.random() * (array.length)) / (endIndex - beginIndex + 1);

		// Pivot yeni pozisyonu
		int last = endIndex;

		// Pivotu arrayın sağına götür
		SortUtil.swap(array, random, endIndex);
		endIndex--;

		while (beginIndex <= endIndex) {
			if (array[beginIndex] < array[last]) {
				beginIndex++; // Küçükler sola
			} else {
				SortUtil.swap(array, beginIndex, endIndex);
				endIndex--;
			}
		}
		// Son olarak ,Pivotu doğru pozisyona getirir. 
		SortUtil.swap(array, beginIndex, last);

		return beginIndex;
	}
}
