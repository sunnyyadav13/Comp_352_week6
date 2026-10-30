public class BinarySearch {

    private BinarySearch() {
    }

    public static int indexOf(int[] sorted, int target) {
        int low = 0;
        int high = sorted.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sorted[mid] == target) {
                return mid;
            }
            if (sorted[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int firstIndexOf(int[] sorted, int target) {
        int low = 0;
        int high = sorted.length - 1;
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sorted[mid] == target) {
                result = mid;
                high = mid - 1;
            } else if (sorted[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int indexOfRecursive(int[] sorted, int target) {
        return search(sorted, target, 0, sorted.length - 1);
    }

    private static int search(int[] sorted, int target, int low, int high) {
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (sorted[mid] == target) {
            return mid;
        }
        if (sorted[mid] < target) {
            return search(sorted, target, mid + 1, high);
        }
        return search(sorted, target, low, mid - 1);
    }
}
