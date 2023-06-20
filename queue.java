class Queue
{
    private int array[];
    private int elem;

    Queue(int size)
    {
        array = new int[size];
        elem = -1;
    }

    // добавление элемента в очередь
    public void Enqueue(int x)
    {
        System.out.println("Добавление: " + x);
        array[++elem] = x;
    }

    // удаление элемента из очереди
    public int Dequeue()
    {
        // проверка пуста ли очередь
        if (Empty())
        {
            System.out.println("Очередь пуста");
            System.exit(-1);
        }
        System.out.println("Удаление: " + peek());
        return array[elem--];
    }
    
    // вывод верхнего элемента очереди
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
    
	// проверка пуста ли очередь или нет
    public boolean Empty() {
        return elem == -1;
    }
	
    // получение размера очереди
    public int size() {
        return elem + 1;
    }

}

class Main
{
    public static void main (String[] args)
    {
        Queue queue = new Queue(99);
        queue.Enqueue(50);
        queue.Enqueue(20);
        queue.Enqueue(1);
        queue.Dequeue();
        System.out.println("Верхний элемент: " + queue.peek());
        System.out.println("Размер очереди: " + queue.size());
        queue.Dequeue();
        System.out.println("Верхний элемент: " + queue.peek());
        if (queue.Empty()) {
            System.out.println("Очередь пуста");
        }
        else {
            System.out.println("Очередь всё ещё содержит элементы");
        }
    }
}