import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *Sıralama algoritmalarının karşılaştırılmasının yapılabildiği sınıftır 
 */
public class SortAlgorithmComparisonMain {

	private static int[] smallArray = new int[5000];
	private static int[] mediumArray = new int[20000];
	private static int[] bigArray = new int[80000];
	private static Map<String, Statistic> arrayNameStatisticMap = new HashMap<>();

	public static void main(String[] args) {
		MergeSort mergeSort = new MergeSort();
		SelectionSort selectionSort = new SelectionSort();
		QuickSort quickSort = new QuickSort();
		CountingSort countingSort = new CountingSort();
		int arraySize = 0;
		long totalTime = 0L;
		//25 kere yeni random sayılarla dolu array list oluşturur, bunlar için ayrı ayrı sıralama algoritmalarını çalıştırır.
		for (int i = 0; i < 25; i++) {
			List<int[]> arrayList = fillArraysWithRandomInteger();
			
			//Array listesinde bulunan 5000, 20000,80000 uzunluklarında rastgele doldurulmuş her bir array için algoritmaları çalıştırır
			//mapte tutulan statistic objelerinde yeni algoritma bitme zamanına göre değişiklik yapılması gerekiyorsa yapar.
			for (int[] arrayWithDifferentSize : arrayList) {
				arraySize = arrayWithDifferentSize.length;

				totalTime = selectionSort.sort(getCopyOfGivenArray(arrayWithDifferentSize));
				createOrChangeMapValueOfGivenKeyIfNecessary("selection" + arraySize, totalTime);
				
				totalTime = mergeSort.sort(getCopyOfGivenArray(arrayWithDifferentSize));
				createOrChangeMapValueOfGivenKeyIfNecessary("merge" + arraySize, totalTime);

				totalTime = quickSort.sort(getCopyOfGivenArray(arrayWithDifferentSize));
				createOrChangeMapValueOfGivenKeyIfNecessary("quick" + arraySize, totalTime);

				totalTime = countingSort.sort(getCopyOfGivenArray(arrayWithDifferentSize));
				createOrChangeMapValueOfGivenKeyIfNecessary("counting" + arraySize, totalTime);
			}
		}
		//Tablo üretmek için kullanılan metot
		sysOutForSorts(1);
		sysOutForSorts(2);
		sysOutForSorts(3);
		sysOutForSorts(4);
	}

	/**
	 * Gerekli olan 3 adet arrayi random sayılarla doldurur ve liste halinde geri döner
	 */
	private static List<int[]> fillArraysWithRandomInteger() {
		List<int[]> arrayList = new ArrayList<>();
		fillGivenArrayWithRandomNumber(smallArray);
		fillGivenArrayWithRandomNumber(mediumArray);
		fillGivenArrayWithRandomNumber(bigArray);
		arrayList.add(smallArray);
		arrayList.add(mediumArray);
		arrayList.add(bigArray);
		return arrayList;
	}
	
	/**
	 * @param arrayWillBeFilled random sayılarla doldurulmak istenen array
	 * verilen arrayın boyutunun 2 katı uzaklıgında random sayılar üretir ve arrayı bunlarla doldurur
	 */
	private static void fillGivenArrayWithRandomNumber(int[] arrayWillBeFilled) {
		int randomNumber = 0;
		for (int i = 0; i < arrayWillBeFilled.length; i++) {
			//Min+(Math.random()*(max-min)+1) patterni ile rastgele sayı üretir
			randomNumber = 0 + (int) (Math.random() * ((arrayWillBeFilled.length * 2) - 0) + 1);
			arrayWillBeFilled[i] = randomNumber;
		}
	}

	
	/**
	 * @param arrayName algoritmanın ismi + arrayin boyutunun birleşiminde oluşan stringdir. Bu mapte key olacaktır
	 * @param totalTime algoritma bitene kadar geçen süre
	 * Aldığı array ismini map'e key olarak koyar ve value kısmını bir statistic objesi yerleştirir. Böylelikle
	 * Her algoritmanın her belli array uzunluğuna ait bir statistic objesi tutulur. Algoritmalar bitince burdaki statistic objeleri
	 * yazdırılır
	 */
	private static void createOrChangeMapValueOfGivenKeyIfNecessary(String arrayName, long totalTime) {
		//Mapte zaten bu keye karşılık varsa statistic objesini çağırır ve gerekliyse, best case , worst case değiştirir. Yeni average case
		// hesaplar
		if (arrayNameStatisticMap.containsKey(arrayName)) {
			Statistic statistic = arrayNameStatisticMap.get(arrayName);
			long averageCase = statistic.getAverageCase();
			int cursor = statistic.getCursor();
			averageCase = ((averageCase * cursor) + totalTime) / (cursor + 1);
			statistic.setAverageCase(averageCase);
			statistic.setCursor(cursor + 1);

			if (statistic.getBestCase() > totalTime) {
				statistic.setBestCase(totalTime);
			} else if (statistic.getWorstCase() < totalTime) {
				statistic.setWorstCase(totalTime);
			}
			arrayNameStatisticMap.put(arrayName, statistic);
		} else {
			//Mapte böyle bir key yoksa yeni bir statistic objesi oluşturur ve bunu mape koyar
			Statistic statistic = new Statistic(totalTime);
			arrayNameStatisticMap.put(arrayName, statistic);
		}
	}
	//Mapte tutulan değerleri almak, neticesinde istenilen formatta output üretmek için kullanılan metot
	/**
	 * @param version sıralama algoritmasının versiyonu , 1 selection
	 * 2 merge
	 * 3 quick
	 * 4 counting.
	 * Versiyona göre uygun parametrelerle bir alt metotu cagırır ve output üretir
	 */
	private static void sysOutForSorts(int version) {
		switch (version) {
		case 1:
			sysOutSort(1, "Selection Sort", "selection");
			break;
		case 2:
			sysOutSort(2, "Merge Sort", "merge");
			break;
		case 3:
			sysOutSort(3, "Quick Sort", "quick");
			break;
		case 4:
			sysOutSort(4, "Counting Sort", "counting");
			break;

		}

	}

	/**
	 * @param version outputta bulunan alan
	 * @param versionName outputta bulunan alan
	 * @param mapKeyName mapten çekmek için gerekli algoritma ismi
	 */
	private static void sysOutSort(int version, String versionName, String mapKeyName) {
		System.out.println();
		System.out.println("Version " + version + " - " + versionName + ": Run-Times over 25 test runs");
		System.out.format("%-15s%-15s%-15s%-15s%n", "Input Size", "Best-Case ", "Worst-Case", "Average-Case");
		int cursor = 1250;// bu cursor 5000, 20000, 80000 üretmek için kullanılır, algoritmaismi + uzunluğu mapte key olduğu için
		for (int i = 0; i < 3; i++) {
			cursor = cursor * 4;//cursor 4le carpılarak arttırıyorum, bana lazım ismi üretmek için
			Statistic statistic = arrayNameStatisticMap.get(mapKeyName + cursor);
			System.out.format("%-15s%-15d%-15d%-15d%n", "N = " + cursor, statistic.getBestCase(),
					statistic.getWorstCase(), statistic.getAverageCase());

		}

	}

	private static int[] getCopyOfGivenArray(int[] originalArray) {
		int[] copyArray = new int[originalArray.length];
		System.arraycopy(originalArray, 0, copyArray, 0, originalArray.length);
		return copyArray;
	}


}
