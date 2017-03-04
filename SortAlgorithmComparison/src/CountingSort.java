
import java.util.*;

public class CountingSort {

	/**
	 * @param sıralanacak
	 * @return çalışma zamanı nano saniye
	 */
	public long sort(int[] array) {
		long startTime = System.nanoTime();
		countingSort(array);
		long finishTime = System.nanoTime();
		return finishTime - startTime;

	}

	public int[] countingSort(int[] array) {
		// geçici array işlemlerin yapıldığı
		int[] temporaryArray = new int[array.length];

		int minimumValue = array[0];
		int maximumValue = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < minimumValue) {
				minimumValue = array[i];
			} else if (array[i] > maximumValue) {
				maximumValue = array[i];
			}
		}

		// Sayı kaç kere tekrarlamış onların tutulacağı array
		int[] howManyTimeOccurredArray = new int[maximumValue - minimumValue + 1];

		// Sayı kaç kere tekrarlamış , tekrarladıkça bir artıran değer
		int tempValue=0;
		for (int i = 0; i < array.length; i++) {
			tempValue=howManyTimeOccurredArray[array[i] - minimumValue]+1;
			howManyTimeOccurredArray[array[i] - minimumValue]=tempValue;
		}

		// Bu kısım arrayin en alttan başlayarak kümülatif olarak toplayarak devam eder.
		// Örneğin ilk index 2 kere tekrarlamış 2. index 3 kere tekrarlamıssa 2. indexe 5 yazar
		//Bu bir sonraki döngüde 2. indexi yerleştirirken ilk nereye yerleştireceğimize karar verdirir.
		//Tüm işlemler için sonuna kadar devam eder.
		howManyTimeOccurredArray[0]--;
		for (int i = 1; i < howManyTimeOccurredArray.length; i++) {
			howManyTimeOccurredArray[i] = howManyTimeOccurredArray[i] + howManyTimeOccurredArray[i - 1];
		}

		/*
		 * 
		 * Burda ise orjinal arrayi tersten dolaşır. Arrayın indexinde bulunan değerden minimum değeri çıkarır
		 * Böylelikle howManyTimeOccurredArray hangi indexe bakacagını hesaplar. howManyTimeOccurredArray deki
		 * Değeri aldıktan sonra bu değeri bir azaltır bir sonra eğer aynı sayı buraya bakarsa sol tarafına yazsın diye
		 * ve götürür geçici arraya yazar. En sonunda geçici arrayı döner
		 */
		for (int i = array.length - 1; i >= 0; i--) {
			temporaryArray[howManyTimeOccurredArray[array[i] - minimumValue]--] = array[i];
		}
		return temporaryArray;
	}

	public static void main(String[] args) {

		int[] unsorted = { 5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 4 };
		System.out.println("Before: " + Arrays.toString(unsorted));
		CountingSort cs = new CountingSort();
		int[] sorted = cs.countingSort(unsorted);
		System.out.println("After: " + Arrays.toString(sorted));

	}
}
