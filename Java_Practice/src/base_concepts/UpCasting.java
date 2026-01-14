package base_concepts;

class Animal {
    void eat() {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}

public class UpCasting {
    public static void main(String[] args) {
        Animal a = new Dog(); // ✅ Upcasting (Dog → Animal)
        a.eat();              // Can call superclass method
        // a.bark();          // ❌ Compile-time error: Animal reference can't see Dog method
    }
}

