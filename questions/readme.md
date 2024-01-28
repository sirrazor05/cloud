# Table of Contents

1. [What is an OOP language?](#oop)
2. [What are the SOLID principles?](#solid)


## 1. What is an OOP language? <a name="oop"></a>?

An OOP (Object-Oriented Programming) language is a programming paradigm that is based on
the concept of "objects," which can contain data in the form of fields (attributes or properties)
and code in the form of procedures (methods or functions).

Key features of OOP languages include:

### Encapsulation
 Objects can encapsulate data and methods within themselves, hiding the internal state. Well defined interfaces are used to interact with objects.
### Inheritance
Objects can inherit attributes and methods from other objects, allowing for
   code reuse and the creation of hierarchical relationships between classes.
###  Polymorphism
Objects of different classes can be treated uniformly through a common
   interface, allowing for flexibility and extensibility in the code. 
   
```java
// Superclass
class Animal {
    // Method to make sound
    public void makeSound() {
        System.out.println("Some generic sound");
    }
}

// Subclass Dog
class Dog extends Animal {
    // Override makeSound method
    @Override
    public void makeSound() {
        System.out.println("Woof");
    }
}

// Subclass Cat
class Cat extends Animal {
    // Override makeSound method
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog(); // Polymorphism: Dog object treated as Animal
        Animal myCat = new Cat(); // Polymorphism: Cat object treated as Animal

        myDog.makeSound(); // Calls Dog's makeSound method
        myCat.makeSound(); // Calls Cat's makeSound method
    }
}

````
### Abstraction

OOP languages allow developers to create abstract representations of real-world entities, focusing on essential characteristics while hiding unnecessary details.

Example: Consider a `BankAccount` class. It abstracts the concept of a bank
account, focusing on essential attributes and methods such as `balance`,
`deposit()`, and `withdraw()`, while hiding the intricate details of how these
operations are implemented internally.

## 2. What are the SOLID principles <a name="solid"></a>? 

The SOLID principles are a set of five design principles that aim to guide software design and 
development to create systems that are easy to understand, maintain, and extend. These
principles were introduced by Robert C. Martin (also known as Uncle Bob) and are widely
regarded as foundational principles in object-oriented design.

The SOLID acronym stands for:

1. Single Responsibility Principle (SRP):
   - A class should have only one reason to change, meaning it should have only one
     responsibility or job.
   - This principle promotes high cohesion within classes, making them easier to
     understand, maintain, and reuse.
   - Breaking down complex systems into smaller, focused components that each
     have a single responsibility helps improve overall system flexibility and
     robustness.
2. Open/Closed Principle (OCP):
   - Software entities (classes, modules, functions, etc.) should be open for extension
     but closed for modification.
   - This principle encourages designing systems in a way that allows new
     functionality to be added through extension rather than modifying existing code.
   - By using abstractions, interfaces, and polymorphism, you can design
     components that can be extended without altering their existing behavior.
3. Liskov Substitution Principle (LSP):
   - Objects of a superclass should be replaceable with objects of its subclass without
     affecting the correctness of the program.
   - In other words, subclasses should be substitutable for their base classes without
     changing the desired properties of the program.
   - Adhering to this principle ensures that inheritance hierarchies are well-designed
     and that polymorphism works as intended, promoting code reuse and flexibility.
4. Interface Segregation Principle (ISP):
   - Clients should not be forced to depend on interfaces they do not use.
   - Instead of creating large, monolithic interfaces, design smaller, more focused
   interfaces that are tailored to the specific needs of clients.
   - By avoiding fat interfaces, you can prevent clients from being burdened with
   unnecessary dependencies and reduce the impact of changes.
5. Dependency Inversion Principle (DIP):
   - High-level modules should not depend on low-level modules. Both should
   depend on abstractions.
   - Abstractions should not depend on details; details should depend on
   abstractions.
   - This principle promotes loose coupling between modules by introducing
   abstractions/interfaces that decouple high-level and low-level components,
   making the system more flexible, extensible, and easier to maintain.