
public class SortUtil {
	
    /**
     * @param array swap işlemi uygulanacak array
     * @param i swap işlemi uygulanacak indexten ilki
     * @param j swap işlemi uygulanacak indexten ikincisi
     */
    public  static void swap(int[] array, int i, int j){
        //Basit swap işlemi
        int temp= array[i];
        array[i]=array[j];
        array[j]=temp;
    }

}
