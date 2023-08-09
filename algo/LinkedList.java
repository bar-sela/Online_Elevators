package ex0.algo;

public class LinkedList{
    protected int _size;
    protected Link _head;

    public LinkedList() {
        _size = 0;
        this._head = null;
    }

    public LinkedList copy(){
        LinkedList copy = new LinkedList();
        Link current = this._head;
        while(current != null){
        copy.addInPlace(current.getData());
        current = current.getNext();
    }
    return copy;
    }

    public LinkedList copyReverse(){
        LinkedList copy = new LinkedList();
        Link current = this._head;
        while(current != null){
            copy.addInPlaceReverse(current.getData());
            current = current.getNext();
        }
        return copy;
    }

    public boolean isEmpty(){
        return this._head == null;
    }

    public int indexOf(int num) {
        int index = -1;
        Link current = this._head;
        while(current!=null){
            index++;
            if (current._data==num) return index;
            else current = current.getNext();
        }
        return -1;
    }

    public boolean contains(int data) {
        Link current = this._head;
        while(current != null)
            if(current.getData() == data) return true;
            else current = current.getNext();
        return false;
    }

    public void addLast(int data) {
        if (_head == null) {
            _head = new Link(data);
        }
        else {
            Link current = _head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current._next = new Link(data);
        }
    }


    public boolean removeElement(int data) {
        if(!contains(data)) return false;
        else {
            _size--;
            if(_head.getData() == data)
                _head = _head.getNext();
            else {
                Link current = _head;
                while(current.getNext().getData() != data)
                    current = current.getNext();
                current._next = current.getNext().getNext();
            }
            return true;
        }
    }


    public void removeFirst() {
        if (this._head != null) {
            this._head = this._head.getNext();
            _size--;
        }
    }

    public void sort() {
        LinkedList ans = new LinkedList();

        ans.addInPlace(this._head.getData());
        Link current = _head.getNext();

        if(current != null && current.getData() < ans._head.getData()) {
            //	ans.head.setNext(new Link(current.getData(), ans.head.getNext()));
            Link temp =	new Link(current.getData());
            ans._head = temp;
        }
        assert current != null;
        current = current.getNext();

        while(current != null) {
            Link p = ans._head;
            while(p.getNext() != null && p.getNext().getData() <= current.getData()) {
                p = p.getNext();
            }
            p._next = new Link(current.getData());
            current = current.getNext();
        }

        this._head = ans._head;
    }



    public int getNum(int i) {
        try {
            Link current = _head;
            for(int j=0; j<i; j++) {
                current = current.getNext();
                if(current == null)
                    throw new Exception();
            }
            return current.getData();
        }
        catch (Exception ex) {
            //System.err.println("No entry number");
            return -1;
        }
    }

    public int peek(){
        return this._head._data;
    }

    public void removeUp(int lastFloor, int pos) {
        if(_size == 0 )
            return;

        if(this._head._data == lastFloor){
            while(!this.isEmpty() && this._head._data <= pos)  {
                this.removeFirst();
            }
        }
        if (this._head == null) return;
        Link current = this._head;
        while (current.getNext()!= null && current.getNext()._data != lastFloor)
            {current = current.getNext();}
        while(current.getNext() != null && current.getNext()._data <= pos)  {
            remove(current.getNext()._data);
        }
    }


    public void removeDown(int lastFloor, int pos) {
        if(_size == 0 )
            return;

        if(this._head._data == lastFloor){
            while(!this.isEmpty() && this._head._data >= pos)  {
                this.removeFirst();
            }
        }
        if (this._head == null) return;
        Link current = this._head;
        while (current.getNext()!= null && current.getNext()._data != lastFloor)
        {current = current.getNext();}
        while(current.getNext() != null && current.getNext()._data >= pos)  {
            remove(current.getNext()._data);
        }
    }


    void remove(int data) {
        if(!contains(data))
            return;

            if(_head.getData() == data)
                _head = _head.getNext();
            else {
                Link current = _head;
                while(current.getNext().getData() != data)
                    current = current.getNext();
                current._next = current.getNext().getNext();
            }
            _size--;
    }

    public void removeUp(int num) {
        if(_size == 0 )
            return;
        while(!this.isEmpty() && this._head._data <= num)  {
            this.removeFirst();
        }
    }

    public void removeDown(int num) {
        if(_size ==0 )
            return;
        while(!this.isEmpty() && this._head._data >= num) {
            this.removeFirst();
        }
    }


    public static void swap(CallList list) {
        LinkedList temp = list._l1 ;
        list._l1 = list._l2 ;
        list._l2 = temp;
    }

    public void addInPlace(int num) {
        if (!contains(num)) {
            _size++;
            Link add = new Link(num);
            if (this._head == null) {
                this._head = add;
            } else {
                Link current = this._head;
                if (num < this._head._data) {
                    add._next = current;
                    this._head = add;
                } else {
                    while (current._next != null) {
                        if (add._data < current.getNext()._data) {
                            add._next = current.getNext();
                            current._next = add;
                            break;
                        } else current = current.getNext();
                    }
                    current._next = add;

                }
            }
        }
    }

    public void addInPlaceReverse(int num) {
        if ((!contains(num))) {
            _size++;
            Link add = new Link(num);
            if (this._head == null) {
                this._head = add;
            } else {
                Link current = this._head;
                if (num > this._head._data) {
                    add._next = current;
                    this._head = add;
                } else {
                    while (current._next != null) {
                        if (add._data > current.getNext()._data) {
                            add._next = current.getNext();
                            current._next = add;
                            break;
                        } else current = current.getNext();
                    }
                    current._next = add;

                }
            }
        }
    }

    public String toString() {
        String retStr = "Contents:\n";

        Link current = this._head;
        while (current != null) {
            retStr += current.getData() + ", ";
            current = current.getNext();

        }
        return retStr;
    }








    public static void main(String[] args) {
        CallList l = new CallList_Down();
        LinkedList l1 = new LinkedList();
        l1.addInPlace(3);
        l1.addInPlace(5);
        l1.addInPlace(0);
        l1.addInPlace(1);
        l1.addInPlace(-100);

        LinkedList l2 = new LinkedList();
        l2.addInPlaceReverse(2);
        l2.addInPlaceReverse(40);
        l2.addInPlaceReverse(17);
        l._l1 = l1;
        l._l2 = l2;
        System.out.println("l1: "+ l._l1.toString());
        System.out.println("l2: "+ l._l2.toString());


        swap(l);
        System.out.println();
        System.out.println("l1: "+ l._l1.toString());
        System.out.println("l2: "+ l._l2.toString());

      //  System.out.println(l1.getNum(0));
       // System.out.println(l2.getNum(0));
        l2.addInPlaceReverse(3);
        l2.addInPlaceReverse(50);
        System.out.println(l2.toString());
     //   l2.removeDown(50,2);
        System.out.println("l2 after the delete: " +l2.toString());

        l2.addInPlace(5);
        System.out.println();
        System.out.println("l2: "+ l2);
        l2.sort();
        System.out.println("sort l2: "+ l2);


    }



}
