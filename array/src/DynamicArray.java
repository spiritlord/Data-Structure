import java.util.StringJoiner;

/**
 * 封装自己的动态数组  支持随机访问
 *
 * @author gaohao
 * @date 2019/10/31 15:52
 * @desc
 */
public class DynamicArray<E> {

    /**
     * 数据存放
     */
    private E[] data;

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
        data = (E[]) new Object[capacity];
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
     * 在所有元素后添加一个新元素 O(1)
     *
     * @param e 插入元素
     * @return
     * @author gaohao
     * @date 2019/10/31 16:23
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 在所有元素前添加一个新元素 O(n)
     *
     * @param e 插入元素
     * @return
     * @author gaohao
     * @date 2019/10/31 17:04
     */
    public void addFirst(E e) {
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
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("数组下标非法");
        }
        if (size == data.length) {
            resize(data.length * 2);
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
     * @param index 索引位置
     * @return
     * @author gaohao
     * @date 2019/10/31 17:28
     */
    public E get(int index) {
        verify(index);
        return data[index];
    }

    /**
     * 修改index索引位置的元素为e
     *
     * @param index 索引位置
     * @param e     修改元素
     * @return
     * @author gaohao
     * @date 2019/10/31 17:41
     */
    public void set(int index, E e) {
        verify(index);
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
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的索引,如果不存在,则返回-1
     *
     * @param e 查找元素
     * @return
     * @author gaohao
     * @date 2019/10/31 17:51
     */
    public int findIndex(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除索引index的元素,并返回删除的元素值
     *
     * @param index 索引位置
     * @return
     * @author gaohao
     * @date 2019/10/31 18:09
     */
    public E remove(int index) {
        verify(index);
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
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
    public E removeFirst() {
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
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素e
     *
     * @param e 删除元素
     * @return
     * @author gaohao
     * @date 2019/10/31 18:38
     */
    public boolean removeElement(E e) {
        int index = findIndex(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

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

    /**
     * 校验索引合法性
     *
     * @param
     * @return
     * @author gaohao
     * @date 2019/11/1 16:05
     */
    private void verify(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("数组下标非法");
        }
    }

    /**
     * 数组扩容或缩容
     *
     * @param newCapacity 容量
     * @return
     * @author gaohao
     * @date 2019/11/1 15:55
     */
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
