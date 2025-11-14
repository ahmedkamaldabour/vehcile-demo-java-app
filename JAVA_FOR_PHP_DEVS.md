# Java for PHP/Laravel Developers - Quick Guide

## Basic Syntax Differences

### 1. Variables and Types

**PHP:**
```php
$name = "John";                    // Type inferred
$age = 25;                         // Can change type later
$price = 99.99;
```

**Java:**
```java
String name = "John";              // Must declare type
int age = 25;                      // Type is fixed
double price = 99.99;
final String brand = "Tesla";      // final = readonly/const
```

---

### 2. Classes and Objects

**PHP:**
```php
class Vehicle {
    private $name;
    
    public function __construct($name) {
        $this->name = $name;
    }
    
    public function getName() {
        return $this->name;
    }
}

$vehicle = new Vehicle("Tesla");
echo $vehicle->getName();
```

**Java:**
```java
public class Vehicle {
    private String name;
    
    public Vehicle(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}

Vehicle vehicle = new Vehicle("Tesla");
System.out.println(vehicle.getName());
```

---

### 3. Arrays vs Lists

**PHP:**
```php
$vehicles = [];
$vehicles[] = "Tesla";
$vehicles[] = "BMW";

foreach ($vehicles as $vehicle) {
    echo $vehicle;
}
```

**Java:**
```java
List<String> vehicles = new ArrayList<>();
vehicles.add("Tesla");
vehicles.add("BMW");

for (String vehicle : vehicles) {
    System.out.println(vehicle);
}
```

---

### 4. Interfaces

**PHP:**
```php
interface PaymentGateway {
    public function charge($amount);
}

class StripePayment implements PaymentGateway {
    public function charge($amount) {
        // Implementation
    }
}
```

**Java:**
```java
public interface PaymentGateway {
    boolean charge(double amount);
}

public class StripePayment implements PaymentGateway {
    @Override
    public boolean charge(double amount) {
        // Implementation
        return true;
    }
}
```

---

### 5. Dependency Injection

**Laravel:**
```php
class VehicleController extends Controller {
    public function __construct(
        private VehicleService $service
    ) {}
    
    public function store(Request $request) {
        $this->service->create($request->all());
    }
}
```

**Java:**
```java
public class AddVehicle {
    private final VehicleService service;
    
    public AddVehicle(VehicleService service) {
        this.service = service;
    }
    
    public void addVehicle() {
        this.service.create(vehicle);
    }
}
```

---

## Common Java Keywords

| Keyword | PHP Equivalent | Meaning |
|---------|---------------|---------|
| `public` | `public` | Accessible everywhere |
| `private` | `private` | Only within class |
| `protected` | `protected` | Within class and subclasses |
| `final` | `readonly` / `const` | Cannot be changed |
| `static` | `static` | Belongs to class, not instance |
| `void` | No return type | Method returns nothing |
| `implements` | `implements` | Implements an interface |
| `extends` | `extends` | Inherits from a class |
| `@Override` | No equivalent | Indicates method override |

---

## Data Types

### PHP vs Java

| PHP | Java | Example |
|-----|------|---------|
| `string` | `String` | `String name = "John";` |
| `int` | `int` | `int age = 25;` |
| `float` | `double` | `double price = 99.99;` |
| `bool` | `boolean` | `boolean active = true;` |
| `array` | `List`, `ArrayList` | `List<String> names = new ArrayList<>();` |
| `null` | `null` | `String name = null;` |

---

## Method/Function Syntax

**PHP:**
```php
public function calculateTotal($price, $tax) {
    return $price * (1 + $tax);
}
```

**Java:**
```java
public double calculateTotal(double price, double tax) {
    return price * (1 + tax);
}
```

---

## Namespaces vs Packages

**PHP:**
```php
namespace App\Services;

use App\Repositories\VehicleRepository;

class VehicleService {
    //...
}
```

**Java:**
```java
package Services;

import Repository.VehicleRepository;

public class VehicleService {
    //...
}
```

---

## String Operations

**PHP:**
```php
$name = "Tesla";
$brand = "Model 3";
$message = "$name $brand";           // Interpolation
$message = $name . " " . $brand;     // Concatenation
```

**Java:**
```java
String name = "Tesla";
String brand = "Model 3";
String message = name + " " + brand;              // Concatenation
String message = String.format("%s %s", name, brand);  // Formatting
```

---

## Null Safety

**PHP:**
```php
$name = $vehicle->name ?? "Unknown";
if (isset($vehicle->name)) {
    echo $vehicle->name;
}
```

**Java:**
```java
String name = (vehicle.getName() != null) ? vehicle.getName() : "Unknown";
if (vehicle.getName() != null) {
    System.out.println(vehicle.getName());
}
```

---

## Exception Handling

**PHP:**
```php
try {
    $vehicle = Vehicle::create($data);
} catch (Exception $e) {
    echo $e->getMessage();
}
```

**Java:**
```java
try {
    Vehicle vehicle = new Vehicle(data);
} catch (Exception e) {
    System.out.println(e.getMessage());
}
```

---

## File Operations

**PHP:**
```php
$data = json_encode($vehicles);
file_put_contents('vehicles.json', $data);

$json = file_get_contents('vehicles.json');
$vehicles = json_decode($json, true);
```

**Java (with Gson):**
```java
Gson gson = new Gson();
String json = gson.toJson(vehicles);
FileWriter writer = new FileWriter("vehicles.json");
writer.write(json);
writer.close();

FileReader reader = new FileReader("vehicles.json");
List<Vehicle> vehicles = gson.fromJson(reader, List.class);
reader.close();
```

---

## Common Gotchas for PHP Developers

### 1. **Everything needs a type**
```java
// ❌ This doesn't work in Java
var name = "John";  // (unless using var in Java 10+)

// ✓ Must specify type
String name = "John";
```

### 2. **No dollar signs for variables**
```java
// ❌ Wrong
$name = "John";

// ✓ Correct
String name = "John";
```

### 3. **Method calls need parentheses**
```java
// ❌ Wrong
vehicle.getName;

// ✓ Correct
vehicle.getName();
```

### 4. **Arrays are fixed size, use ArrayList**
```java
// ❌ Fixed size
String[] vehicles = new String[5];

// ✓ Dynamic size (like PHP arrays)
List<String> vehicles = new ArrayList<>();
```

### 5. **No associative arrays (use HashMap)**
```php
// PHP
$vehicle = ['name' => 'Tesla', 'price' => 50000];
```

```java
// Java
Map<String, Object> vehicle = new HashMap<>();
vehicle.put("name", "Tesla");
vehicle.put("price", 50000);
```

### 6. **String comparison uses .equals()**
```java
// ❌ Wrong (compares references)
if (name == "Tesla") {}

// ✓ Correct (compares values)
if (name.equals("Tesla")) {}
```

---

## Quick Reference: Our Application

### When you see this pattern:

```java
private final VehicleService vehicleService;

public AddVehicle(VehicleService vehicleService) {
    this.vehicleService = vehicleService;
}
```

**Think Laravel:**
```php
public function __construct(
    private VehicleService $vehicleService
) {}
```

It's the same thing! **Constructor Dependency Injection** 🎉

---

## Summary

Java is more **verbose** and **strict** than PHP, but the concepts are the same:

- ✅ Classes, Objects, Interfaces
- ✅ Dependency Injection
- ✅ SOLID Principles
- ✅ Repository Pattern
- ✅ Service Layer
- ✅ Validation

Once you understand the syntax differences, everything else translates 1:1 from Laravel! 🚀

