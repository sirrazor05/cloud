# Table of Contents

1. [What are the SOLID principles?](#solid)

## 1. What are the SOLID principles <a name="solid"></a>? 

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