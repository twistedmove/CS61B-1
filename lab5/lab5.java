public class lab5 {
    public static void main(String[] args) {
        System.out.println("Hi");
        sub SB = new sub();
        SB.ma();
    }
}

class sup {
    public int square(int x) {
        return x*x;
    }
}

interface sup2 {
    public int square(long x);
}

class sub extends sup implements sup2 {
    void ma() {
        System.out.println(square(10));
    }
}
