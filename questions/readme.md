# Table of Contents

1. [What is an OOP language?](#oop)
2. [What are the SOLID principles?](#solid)
3. [What is the final keyword doing?](#final)
4. [Latches?](#latches)

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

## 3. What is the final keyword doing? <a name="final"></a>

Final keyword in java can be applied on variables, methods or classes.

### Final Variables:

- When applied to a variable, the final keyword indicates that the variable's value
cannot be modified once it has been assigned a value.
- For primitive data types, the value of a final variable cannot change once it's assigned.
- For reference data types (objects), the reference stored in the variable cannot change
after initialization. However, the state of the object itself may still be modified (unless the
object is immutable).

### Final Methods:

- When applied to a method, the final keyword indicates that the method cannot be
overridden by subclasses. This means that the implementation of the method in the
superclass is final and cannot be changed by subclasses.
- Declaring a method as final prevents subclasses from providing a different
implementation for that method.

### Final Classes:

- When applied to a class, the final keyword indicates that the class cannot be
subclassed (i.e., no other class can extend it). It effectively prevents inheritance for that
class.
- Final classes are often used when the design of a class is complete, and it's not
intended to be subclassed or extended.

## 4. Latches <a name="latches"></a>

A latch is a synchronizer that can delay the progress of threads until it reaches
its terminal state [CPJ 3.4.2]. A latch acts as a gate: until the latch reaches the
terminal state the gate is closed and no thread can pass, and in the terminal
state the gate opens, allowing all threads to pass. Once the latch reaches the
terminal state, it cannot change state again, so it remains open forever. Latches
can be used to ensure that certain activities do not proceed until other one-time
activities complete, such as:

- Ensuring that a computation does not proceed until resources it needs have
been initialized. A simple binary (two-state) latch could be used to indicate
“Resource R has been initialized”, and any activity that requires R would
wait first on this latch.
- Ensuring that a service does not start until other services on which it depends
have started. Each service would have an associated binary latch;
starting service S would involve first waiting on the latches for other services
on which S depends, and then releasing the S latch after startup completes
so any services that depend on S can then proceed.
- Waiting until all the parties involved in an activity, for instance the players
in a multi-player game, are ready to proceed. In this case, the latch reaches
  the terminal state after all the players are ready.

`CountDownLatch` is a flexible latch implementation that can be used in any of
these situations; it allows one or more threads to wait for a set of events to occur.
The latch state consists of a counter initialized to a positive number, representing
the number of events to wait for. The countDown method decrements the counter,
indicating that an event has occurred, and the await methods wait for the counter
to reach zero, which happens when all the events have occurred. If the counter is
nonzero on entry, await blocks until the counter reaches zero, the waiting thread
is interrupted, or the wait times out.

```java
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task)
            throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) { }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end-start;
    }
}

```
TestHarness  illustrates two common uses for latches. Test-
Harness creates a number of threads that run a given task concurrently. It uses
two latches, a “starting gate” and an “ending gate”. The starting gate is initialized
with a count of one; the ending gate is initialized with a count equal to the number
of worker threads. The first thing each worker thread does is wait on the starting
gate; this ensures that none of them starts working until they all are ready to start.
The last thing each does is count down on the ending gate; this allows the master
thread to wait efficiently until the last of the worker threads has finished, so it can
calculate the elapsed time.

Why did we bother with the latches in TestHarness instead of just starting the
threads immediately after they are created? Presumably, we wanted to measure
how long it takes to run a task n times concurrently. If we simply created and
started the threads, the threads started earlier would have a “head start” on the
later threads, and the degree of contention would vary over time as the number
of active threads increased or decreased. Using a starting gate allows the master
thread to release all the worker threads at once, and the ending gate allows the
master thread to wait for the last thread to finish rather than waiting sequentially
for each thread to finish.