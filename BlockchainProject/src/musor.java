import java.util.ArrayList;

class A {
  int x;

  public A(int x) {
    this.x = x;
  }

  public void decrease(int dx) {
    x -= dx;
  }
}

class B {
  int x;

  public B(int x) {
    this.x = x;
  }

  public void decrease(int dx) {
    x -= dx;
  }
}

public class musor {
  public static void main(String[] args) {
    A objectA = new A(10);
    B objectB = new B(100);
    sub(objectA, objectB, 9);
    System.out.println(objectA.x);
    System.out.println(objectB.x);

    ArrayList<Object> arr = new ArrayList<Object>();
    arr.add(13);
    arr.add("qwer");
    arr.add(objectB);
    for (Object o: arr) {
      System.out.println(o);
    }
  }

  public static void sub(A a, B b, int dx) {
    a.decrease(dx);
    b.decrease(dx);
  }
}
