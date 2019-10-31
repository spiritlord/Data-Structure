import java.util.StringJoiner;

/**
 * 封装自己的动态数组
 *
 * @author gaohao
 * @date 2019/10/31 15:52
 * @desc
 */
public class DynamicArray {

    /**
     * 数据存放
     */
    private int[] data;

    /**
     * 有效元素数
     */
    private int size;

    /**
     * 重写构造函数,初始化数组容量
     *
     * @param capacity 容量
     * @return
     * @author gaohao
     * @date 2019/10/31 16:05
     */
    public DynamicArray(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    /**
     * 重写构造函数,初始化静态数组
     *
     * @param arr 静态数组
     * @return
     * @author gaohao
     * @date 2019/10/31 16:06
     */
    public DynamicArray(int[] arr) {
        data = arr;
        size = 0;
    }

    /**
     * 无参构造函数,默认容量10
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 16:06
     */
    public DynamicArray() {
        this(10);
    }

    /**
     * 获取数组中的元素个数
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 16:10
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 16:10
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 16:15
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 在所有元素后添加一个新元素
     *
     * @param e 插入元素
     * @return
     * @author gaohao
     * @date 2019/10/31 16:23
     */
    public void addLast(int e) {
        add(size, e);
    }

    /**
     * 在所有元素前添加一个新元素
     *
     * @param e 插入元素
     * @return
     * @author gaohao
     * @date 2019/10/31 17:04
     */
    public void addFirst(int e) {
        add(0, e);
    }

    /**
     * 在第index的位置插入一个新元素e
     *
     * @param index 索引位置
     * @param e     插入元素
     * @return
     * @author gaohao
     * @date 2019/10/31 16:46
     */
    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("数组已满");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("数组下标非法");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 获取index索引位置的元素,保证未使用数组的数据安全
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 17:28
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("数组下标非法");
        }
        return data[index];
    }

    /**
     * 修改index索引位置的元素为e
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 17:41
     */
    public void set(int index, int e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("数组下标非法");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否包含元素e
     *
     * @param e 查找元素
     * @return
     * @author gaohao
     * @date 2019/10/31 17:48
     */
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引,如果不存在,则返回-1
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 17:51
     */
    public int findIndex(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public int findAllIndex(int e) {}

    /**
     * 从数组中删除索引index的元素,并返回删除的元素值
     *
     * @param index 索引位置
     * @return
     * @author gaohao
     * @date 2019/10/31 18:09
     */
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("数组下标非法");
        }
        int ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return ret;
    }

    /**
     * 从数组中删除第一个元素,并返回删除的元素值
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 18:25
     */
    public int removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素,并返回删除的元素值
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 18:34
     */
    public int removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/10/31 18:38
     */
    public boolean removeElement(int e) {
        int index = findIndex(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public boolean removeAllElement(int e) {}

    @Override
    public String toString() {
        StringJoiner res = new StringJoiner("");
        res.add(String.format("数组：size = %d, capacity = %d\n", size, data.length));
        res.add("[");
        for (int i = 0; i < size; i++) {
            res.add(String.valueOf(data[i]));
            if (i != size - 1) {
                res.add(",");
            }
        }
        res.add("]");
        return res.toString();
    }

    public static void main(String[] args) {
        DynamicArray arr = new DynamicArray(20);
        for (int i = 0; i < 20; i++) {
            arr.addLast(i);
        }
//        System.out.println(arr);
//        arr.add(1, 100);
//        System.out.println(arr);
//        arr.addFirst(-1);
//        System.out.println(arr);
//        System.out.println(arr.get(1));
//        arr.set(1, 1);
//        System.out.println(arr.get(1));
//        System.out.println(arr);
//        arr.remove(11);
//        System.out.println(arr);
//        arr.remove(0);
//        System.out.println(arr);
//        arr.remove(5);
//        System.out.println(arr);
        arr.remove(20);
        System.out.println(arr);
    }
}
