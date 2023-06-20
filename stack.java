class Stack
{
    private int array[];
    private int elem;

    Stack(int size)
    {
        array = new int[size];
        elem = -1;
    }

    // добавление элемента в стек
    public void push(int x)
    {
        System.out.println("Добавление: " + x);
        array[++elem] = x;
    }

    // удаление элемента из стека
    public int pop()
    {
        // проверка пуст ли стек
        if (Empty())
        {
            System.out.println("Стек пуст");
            System.exit(-1);
        }
        System.out.println("Удаление: " + peek());
        return array[elem--];
    }
    
    // вывод верхнего элемента стека
    public int peek()
    {
        if (!Empty()) {
            return array[elem];
        }
        else {
            System.exit(-1);
        }
        return -1;
    }
    
	// проверка пуст ли стек или нет
    public boolean Empty() {
        return elem == -1;
    }
	
    // получение размера стека
    public int size() {
        return elem + 1;
    }

}

class Main
{
    public static void main (String[] args)
    {
        Stack stack = new Stack(99);
        stack.push(50);
        stack.push(20);
        stack.push(1);
        stack.pop();
        System.out.println("Верхний элемент: " + stack.peek());
        System.out.println("Размер стека: " + stack.size());
        stack.pop();
        System.out.println("Верхний элемент: " + stack.peek());
        if (stack.Empty()) {
            System.out.println("Стек пуст");
        }
        else {
            System.out.println("Стек всё ещё содержит элементы");
        }
    }
}