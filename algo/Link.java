package ex0.algo;

public class Link {
        protected int _data;
        protected Link _next;

        public Link(int data){
            this._data = data;
            this._next = null;
        }

        public int getData() {return this._data;}
        public Link getNext() {return this._next;}
}
