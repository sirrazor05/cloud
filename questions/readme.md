# Table of Contents

1. [What is an OOP language?](#oop)
2. [What are the SOLID principles?](#solid)
3. [What is the final keyword doing?](#final)
4. [Latches](#latches)
5. [Barriers](#barriers)
6. [Semaphores](#semaphores)
7. [Java equals() and hashCode() Contracts](#equals_hashcode)

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

Think of it like a group of workers waiting for certain tasks to be completed before
starting their next phase of work. They wait until all tasks are finished before
moving forward.

## 5. Barriers <a name="barriers"></a>

We have seen how latches can facilitate starting a group of related activities or
waiting for a group of related activities to complete. Latches are single-use objects;
once a latch enters the terminal state, it cannot be reset.

`Barriers` are similar to latches in that they block a group of threads until some
event has occurred [CPJ 4.4.3]. The key difference is that with a barrier, all the
threads must come together at a barrier point `at the same time` in order to proceed.
Latches are for waiting for `events`; barriers are for waiting for `other threads`. A
barrier implements the protocol some families use to rendezvous during a day at
the mall: “Everyone meet at McDonald’s at 6:00; once you get there, stay there
until everyone shows up, and then we’ll figure out what we’re doing next.”

`CyclicBarrier` allows a fixed number of parties to rendezvous repeatedly at
a barrier point and is useful in parallel iterative algorithms that break down a
problem into a fixed number of independent sub-problems. Threads call await
when they reach the barrier point, and await blocks until all the threads have
reached the barrier point. If all threads meet at the barrier point, the barrier has
been successfully passed, in which case all threads are released and the barrier is
reset so it can be used again. If a call to await times out or a thread blocked in
await is interrupted, then the barrier is considered broken and all outstanding calls
to await terminate with BrokenBarrierException. If the barrier is successfully
passed, await returns a unique arrival index for each thread, which can be used
to “elect” a leader that takes some special action in the next iteration. `CyclicBarrier`
also lets you pass a barrier action to the constructor; this is a Runnable that is
executed (in one of the subtask threads) when the barrier is successfully passed
but before the blocked threads are released.

Barriers are often used in simulations, where the work to calculate one step can
be done in parallel but all the work associated with a given step must complete
before advancing to the next step. For example, in n-body particle simulations,
each step calculates an update to the position of each particle based on the locations
and other attributes of the other particles. Waiting on a barrier between
each update ensures that all updates for step k have completed before moving on
to step k + 1.

Think of it like a group of friends agreeing to meet at a designated spot before
heading to an event together. They all wait at the meeting spot, and once
everyone is present, they move forward together.

## 6. Semaphores <a name="semaphores"></a>

`Counting semaphores` are used to control the number of activities that can access a
certain resource or perform a given action at the same time [CPJ 3.4.1]. Counting
semaphores can be used to implement resource pools or to impose a bound on a
collection.

A `Semaphore` manages a set of virtual permits; the initial number of permits is
passed to the Semaphore constructor. Activities can acquire permits (as long as
some remain) and release permits when they are done with them. If no permit is
available, acquire blocks until one is (or until interrupted or the operation times
out). The release method returns a permit to the semaphore.4 A degenerate case of
a counting semaphore is a binary semaphore, a Semaphore with an initial count
of one. A binary semaphore can be used as a mutex with nonreentrant locking
semantics; whoever holds the sole permit holds the mutex.

Semaphores are useful for implementing resource pools such as database connection
pools. While it is easy to construct a fixed-sized pool that fails if you
request a resource from an empty pool, what you really want is to block if the
pool is empty and unblock when it becomes nonempty again. If you initialize
a Semaphore to the pool size, acquire a permit before trying to fetch a resource
from the pool, and release the permit after putting a resource back in the pool,
acquire blocks until the pool becomes nonempty. This technique is used in the
bounded buffer class in Chapter 12. (An easier way to construct a blocking object
pool would be to use a BlockingQueue to hold the pooled resources.)

```java
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;
    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }
    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        }
        finally {
            if (!wasAdded)
                sem.release();
        }
    }
    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved)
            sem.release();
        return wasRemoved;
    }
}
```
Similarly, you can use a Semaphore to turn any collection into a blocking
bounded collection, as illustrated by BoundedHashSet in Listing 5.14. The
semaphore is initialized to the desired maximum size of the collection. The add
operation acquires a permit before adding the item into the underlying collection.
If the underlying add operation does not actually add anything, it releases
the permit immediately. Similarly, a successful remove operation releases a permit,
enabling more elements to be added. The underlying Set implementation
knows nothing about the bound; this is handled by BoundedHashSet.

## 7. Java equals() and hashCode() Contracts <a name="equals_hashcode"></a>

### The .equals() Contract

- `reflexive:` an object must equal itself
- `symmetric:` x.equals(y) must return the same result as y.equals(x)
- `transitive:` if x.equals(y) and y.equals(z), then also x.equals(z)
- `consistent:` the value of .equals() should change only if a property that is contained in .equals() changes (no randomness allowed)

### The .hashCode() Contract

All three criteria in the .hashCode() contract mention the .equals() method in some way:
- `internal consistency:` the value of hashCode() may only change if a property that is in equals() changes 
- `equals consistency:` objects that are equal to each other must return the same hashCode 
- `collisions:` unequal objects may have the same hashCode

