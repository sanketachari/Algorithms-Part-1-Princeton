import java.util.Scanner;

/**
 * Created by acharis on 2/14/2016.
 */
public class BinarySearch {

    private int[] a;
    int N, mid, index;

    public BinarySearch(int[] test, int key) {
        this.a = test;
        this.N = key;
    }

    public int bSearch(int low, int high) {
        if (low <= high) {

            mid = (high + low) / 2;

            if (this.a[mid] > N)
                index = bSearch(low, mid - 1);

            else if (this.a[mid] < N)
                index = bSearch(mid + 1, high);

            else
                return mid;
        } else
            return -1;

        return index;
    }

    public static void main(String[] args) {


        int[] a = {1, 2, 3, 4, 6, 7, 8, 9};

        System.out.print("Enter number to find in array: ");

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        BinarySearch aa = new BinarySearch(a, N);

        System.out.println();
        ;
        System.out.println("Found an element at: " + aa.bSearch(0, a.length - 1));
    }
}

