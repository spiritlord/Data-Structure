public class Test {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack);
    }
}
