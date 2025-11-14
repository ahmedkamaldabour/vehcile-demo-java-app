# SOLID Quick Reference Cheat Sheet

## The 5 SOLID Principles

### 1. Single Responsibility Principle (SRP)
**"One class = One job"**

❌ **Bad:**
```java
class Vehicle {
    void save() { /* saves to DB */ }
    void validate() { /* validates */ }
    void sendEmail() { /* sends email */ }
}
```

✅ **Good:**
```java
class Vehicle { /* Just holds data */ }
class VehicleRepository { void save() {} }
class VehicleValidator { void validate() {} }
class EmailService { void send() {} }
```

---

### 2. Open/Closed Principle (OCP)
**"Open for extension, Closed for modification"**

❌ **Bad:**
```java
class PaymentService {
    void pay(String type) {
        if (type.equals("stripe")) { /* stripe logic */ }
        else if (type.equals("paypal")) { /* paypal logic */ }
        // Need to modify this class for each new payment method!
    }
}
```

✅ **Good:**
```java
interface PaymentGateway {
    void charge();
}

class StripePayment implements PaymentGateway { }
class PayPalPayment implements PaymentGateway { }
// Add new payment methods without modifying existing code!
```

---

### 3. Liskov Substitution Principle (LSP)
**"Subtypes must be substitutable for their parent types"**

❌ **Bad:**
```java
class Bird {
    void fly() { /* flies */ }
}

class Penguin extends Bird {
    void fly() { throw new Exception("Can't fly!"); } // ❌ Breaks LSP
}
```

✅ **Good:**
```java
interface Bird { }
interface Flyable { void fly(); }

class Sparrow implements Bird, Flyable { }
class Penguin implements Bird { } // Doesn't implement Flyable
```

---

### 4. Interface Segregation Principle (ISP)
**"Don't force classes to implement unused methods"**

❌ **Bad:**
```java
interface Worker {
    void work();
    void eat();
    void sleep();
}

class Robot implements Worker {
    void work() { /* works */ }
    void eat() { /* ??? Robots don't eat! */ }
    void sleep() { /* ??? Robots don't sleep! */ }
}
```

✅ **Good:**
```java
interface Workable { void work(); }
interface Eatable { void eat(); }
interface Sleepable { void sleep(); }

class Robot implements Workable { }
class Human implements Workable, Eatable, Sleepable { }
```

---

### 5. Dependency Inversion Principle (DIP)
**"Depend on abstractions, not concrete classes"**

❌ **Bad:**
```java
class OrderService {
    private MySqlRepository repository = new MySqlRepository();
    // Tightly coupled to MySQL!
}
```

✅ **Good:**
```java
class OrderService {
    private Repository repository;
    
    public OrderService(Repository repository) {
        this.repository = repository;
    }
    // Can inject any repository implementation!
}
```

---

## Our Application Structure

```
┌─────────────────────────────────────────────┐
│           ScanData (Bootstrap)              │  Creates everything
└─────────────────┬───────────────────────────┘
                  │
        ┌─────────┴─────────┐
        ▼                   ▼
┌─────────────┐      ┌─────────────┐
│ AddVehicle  │      │ListAllVeh.. │           Controllers (SRP)
└──────┬──────┘      └──────┬──────┘
       │                    │
       │                    │
       └──────────┬─────────┘
                  ▼
          ┌──────────────┐
          │VehicleService│                      Service (SRP)
          └───┬──────┬───┘
              │      │
     ┌────────┘      └────────┐
     ▼                        ▼
┌─────────┐          ┌────────────────┐
│Validator│          │ Repository     │        Validator (SRP) + Repository (DIP)
└─────────┘          │ Interface      │
                     └────────┬───────┘
                              │ implements
                              ▼
                     ┌──────────────────┐
                     │JsonVehicleRepo   │      Implementation (OCP, LSP)
                     └──────────────────┘
```

---

## Dependency Injection Pattern

### Constructor Injection (Recommended)

```java
public class AddVehicle {
    private final VehicleService service;
    
    // Inject through constructor
    public AddVehicle(VehicleService service) {
        this.service = service;
    }
}
```

### Bootstrap (Wire Everything Together)

```java
public class ScanData {
    public ScanData() {
        // Create dependencies bottom-up
        VehicleRepository repo = new JsonVehicleRepository();
        VehicleValidator validator = new VehicleValidator();
        VehicleService service = new VehicleService(repo, validator);
        
        // Inject into controllers
        AddVehicle controller = new AddVehicle(service);
    }
}
```

---

## Key Java Keywords

| Keyword | Meaning | Example |
|---------|---------|---------|
| `final` | Cannot be changed | `final String name = "Tesla";` |
| `private` | Only accessible within class | `private String name;` |
| `public` | Accessible everywhere | `public void save()` |
| `interface` | Contract/Blueprint | `interface Repository {}` |
| `implements` | Implements interface | `class JsonRepo implements Repository` |
| `extends` | Inherits from class | `class Car extends Vehicle` |
| `@Override` | Overrides parent method | `@Override public void save()` |

---

## Common Patterns

### Repository Pattern
```java
// Interface (abstraction)
interface VehicleRepository {
    void save(Vehicle v);
}

// Implementation (concrete)
class JsonVehicleRepository implements VehicleRepository {
    public void save(Vehicle v) { /* save to JSON */ }
}
```

### Service Pattern
```java
class VehicleService {
    private VehicleRepository repo;
    
    public void createVehicle(Vehicle v) {
        // Business logic here
        repo.save(v);
    }
}
```

### Validation Pattern
```java
class VehicleValidator {
    public ValidationResult validate(Vehicle v) {
        ValidationResult result = new ValidationResult();
        if (v.getPrice() <= 0) {
            result.addError("Price must be positive");
        }
        return result;
    }
}
```

---

## Laravel vs Java Quick Comparison

| Concept | Laravel | Java |
|---------|---------|------|
| **Controller** | `VehicleController.php` | `AddVehicle.java` |
| **Service** | `VehicleService.php` | `VehicleService.java` |
| **Repository** | `VehicleRepository.php` | `JsonVehicleRepository.java` |
| **Model** | `Vehicle.php` (Eloquent) | `Vehicle.java` |
| **Form Request** | `StoreVehicleRequest.php` | `VehicleValidator.java` |
| **DI Container** | `app()->make()` | Constructor injection |
| **Interface** | `RepositoryInterface.php` | `VehicleRepositoryInterface.java` |

---

## Benefits of SOLID Code

✅ **Testable** - Can mock dependencies  
✅ **Maintainable** - Changes isolated to single classes  
✅ **Flexible** - Easy to swap implementations  
✅ **Readable** - Clear separation of concerns  
✅ **Scalable** - Easy to add new features  

---

## Testing Strategy

```java
// Can easily mock dependencies for testing
@Test
public void testAddVehicle() {
    // Mock repository
    VehicleRepository mockRepo = mock(VehicleRepository.class);
    
    // Inject mock
    VehicleService service = new VehicleService(mockRepo);
    
    // Test
    service.addVehicle(vehicle);
    verify(mockRepo).save(vehicle);
}
```

---

## When to Use Each Principle

| Principle | Use When |
|-----------|----------|
| **SRP** | Class is doing too many things |
| **OCP** | Need to add new features without changing existing code |
| **LSP** | Using inheritance or implementing interfaces |
| **ISP** | Interface has methods not all implementations need |
| **DIP** | Want to make code testable and flexible |

---

## Red Flags (Code Smells)

🚩 **God Class** - One class doing everything → Violates SRP  
🚩 **Switch Statements** - for types → Violates OCP  
🚩 **Tight Coupling** - `new ConcreteClass()` everywhere → Violates DIP  
🚩 **Fat Interfaces** - Many unused methods → Violates ISP  
🚩 **Type Checking** - `if (x instanceof Y)` → Violates LSP  

---

## Remember

**SOLID is not about being perfect, it's about being better!**

Start with one principle at a time:
1. Start with **SRP** - Split large classes
2. Add **DIP** - Use interfaces and injection
3. Apply **OCP** - Make code extensible
4. Consider **ISP** - Keep interfaces small
5. Check **LSP** - Ensure substitutability

**Practice makes perfect!** 🚀

