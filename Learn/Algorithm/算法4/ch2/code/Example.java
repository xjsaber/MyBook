package code;

/**
 * Created by xjsaber on 2017/2/27.
 *
 */
public class Example {
    /**
     *
     * @param a
     */
    public static void sort(Comparable[] a){    }

    /**
     *
     * @param v
     * @param w
     * @return
     */
    private static boolean less(Comparable<Comparable> v, Comparable w){
        return v.compareTo(w) < 0;
    }

    /**
     *
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Comparable[] a, int i, int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     *
     * @param a
     */
    private static void show(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /**
     *
     * @param a
     * @return
     */
    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

//    public static void main(String[] args){
//        Scanner s = new Scanner(System.in);
//        String[] a = s.next().split('');
//        sort(a);
//        assert isSorted(a);
//        show(a);
//    }
}
