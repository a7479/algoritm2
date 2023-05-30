package pro.sky.algoritm2;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {

    private final Integer[] storage;
    private int size;

    public IntegerListImpl() {
        storage = new Integer[10];

    }

    public IntegerListImpl(int initSize) {
        storage = new Integer[initSize];
    }
    /*public static void main(String[] args) {
        int[] arr = generateRandomArray();
        for (int i = 0; i < arr.length ; i++) {

            System.out.println(arr[i]);
        }
    }

    public static int[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[30];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }
*/


    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        storage[size++]=item;
        return item;
        /*storage[size++]=item;
        if (size == storage.length) {
            resize(size + 1);
        }
        storage[size] = item;
        size++;

        return item;*/
    }

    @Override
    public Integer add(int index, Integer item) {
        validateSize();
        validateItem(item);
        validateIndex(index);

        if (index==size) {
            storage[size++]=item;
            return item;
        }
        System.arraycopy(storage,index,storage,index+1,size-index);
        storage[index]=item;
        size++;

        return item;


        /*checkBounds(index);
        if (size == storage.length) {
            resize(size + 1);
        }
        for (int i = size, i>index, i--){
            storage[i] = storage[i - 1];
        }
        storage[index] = item;
        size++;

        return item;*/
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;

    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);

        int index = indexOf(item);
        /*if (index==-1) {
            throw new itemNotFoundException();
        }
        if (index != size) {

            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;*/
        return remove(index);
    }

    @Override
    public Integer remove(int index) {

validateIndex(index);

        Integer item = storage[index];
        if (index != size) {

            System.arraycopy(storage, index + 1, storage, index, size - index);
        }

        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy=toArray();
        sortInsertion(storageCopy);
        return bynarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (storage[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);

        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Arrays.copyOf(storage, size);
        return new Integer[0];
    }

        /*private void resize(int newSize) {
            int size= this.size *2;
            size = Math.max(size, newSize);
            Integer[] newData = new Integer[size];
            System.arraycopy(storage,0,newData,0, this.size);
            storage =newData;
        }

        private void checkBounds(int index) {
            if (index < 0 || index> size) {
                throw new StorageIsFullException();

            }
        }*/

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size == storage.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    public static void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    public static boolean bynarySearch (Integer[] arr, int item) {
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (item == arr[mid]) {
                return true;
            }

            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

}

