import java.util.StringJoiner;

public class ArrayStack<E> implements Stack<E> {

    DynamicArray<E> array;

    public ArrayStack(int capacity) {
        array = new DynamicArray<>(capacity);
    }

    public ArrayStack() {
        array = new DynamicArray<>();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringJoiner res = new StringJoiner(",", "棧： [", "] top");
        for (int i = 0; i < array.getSize(); i++) {
            res.add(String.valueOf(array.get(i)));
        }
        return res.toString();
    }
}
