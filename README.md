# Vehicle Rental System - SOLID Refactored ✨

A Java application demonstrating SOLID principles, designed specifically for developers with PHP/Laravel background.

## 🎯 What This Project Teaches

This project is a **complete refactoring** of a simple vehicle management system, applying all 5 SOLID principles:

- ✅ **Single Responsibility Principle** - Each class has one job
- ✅ **Open/Closed Principle** - Open for extension, closed for modification
- ✅ **Liskov Substitution Principle** - Interfaces can be swapped
- ✅ **Interface Segregation Principle** - Small, focused interfaces
- ✅ **Dependency Inversion Principle** - Depend on abstractions, not concrete classes

---

## 📁 Project Structure

```
demo/
├── pom.xml                        # Maven configuration (dependencies)
├── src/
│   ├── Main.java                  # Application entry point
│   ├── ScanData.java             # Bootstrap & Dependency Injection
│   ├── vehicles.json             # Data storage (auto-created)
│   │
│   ├── Manager/                   # Controllers
│   │   ├── AddVehicle.java       # Add vehicle controller
│   │   └── ListAllVehicle.java   # List vehicles controller
│   │
│   ├── Services/                  # Business Logic Layer
│   │   ├── VehicleService.java   # Vehicle business logic
│   │   └── VehicleInputService.java  # User input handling
│   │
│   ├── Repository/                # Data Access Layer
│   │   ├── VehicleRepositoryInterface.java  # Repository contract
│   │   └── JsonVehicleRepository.java       # JSON implementation
│   │
│   ├── Validators/                # Validation Layer
│   │   ├── VehicleValidator.java       # Validation logic
│   │   └── ValidationResult.java       # Validation results
│   │
│   └── Vehicles/
│       └── Vehicle.java          # Vehicle entity/model
│
├── SETUP.md                      # Setup instructions
├── SOLID_GUIDE.md               # Complete SOLID principles guide
├── ARCHITECTURE.md              # Architecture diagrams
├── JAVA_FOR_PHP_DEVS.md        # Java syntax for PHP devs
└── README.md                    # This file
```

---

## 🚀 Quick Start

### Prerequisites
- Java 11 or higher
- Maven (or IntelliJ IDEA with Maven support)

### Installation

1. **Clone or download this project**

2. **Install dependencies** (Gson library):
   
   **Option A: Using IntelliJ IDEA**
   - Open the project in IntelliJ
   - Right-click on `pom.xml`
   - Select "Add as Maven Project"
   - Wait for dependencies to download

   **Option B: Using Maven CLI**
   ```bash
   mvn clean install
   ```

3. **Run the application**:
   - Right-click on `Main.java`
   - Select "Run 'Main.main()'"

---

## 🎮 How to Use

When you run the application, you'll see:

```
======================
Vehicle Rental System
======================
1. List Vehicles
2. Add a Vehicle
3. Remove a Vehicle
4. Rent a Vehicle
5. Return a Vehicle
6. Exit

Enter your choice:
```

### Adding a Vehicle:
1. Choose option `2`
2. Enter vehicle name (e.g., "Model S")
3. Enter vehicle brand (e.g., "Tesla")
4. Enter vehicle price (e.g., "79999")
5. The vehicle will be validated and saved to `vehicles.json`

### Listing Vehicles:
1. Choose option `1`
2. All vehicles will be displayed

---

## 📚 Learning Resources

### For Complete Beginners:
1. **Start here:** [`JAVA_FOR_PHP_DEVS.md`](JAVA_FOR_PHP_DEVS.md)
   - Java syntax explained through PHP examples
   - Side-by-side comparisons

### Understanding SOLID:
2. **Read next:** [`SOLID_GUIDE.md`](SOLID_GUIDE.md)
   - All 5 SOLID principles explained
   - Laravel vs Java examples
   - Real-world use cases

### Architecture Deep Dive:
3. **Then explore:** [`ARCHITECTURE.md`](ARCHITECTURE.md)
   - Visual diagrams
   - Data flow explanations
   - Layer-by-layer breakdown

### Setup Issues:
4. **If stuck:** [`SETUP.md`](SETUP.md)
   - Detailed installation steps
   - Troubleshooting guide

---

## 🏗️ Architecture Overview

### Layer Pattern (Like Laravel)

```
Controller → Service → Repository → Database
   ↓            ↓          ↓            ↓
AddVehicle → VehicleService → JsonRepo → vehicles.json
```

### Dependency Injection Flow

```
ScanData (Bootstrap)
   ↓
   ├─ Creates: JsonVehicleRepository
   ├─ Creates: VehicleValidator
   ├─ Creates: VehicleService (injects repository & validator)
   ├─ Creates: VehicleInputService
   └─ Creates: Controllers (inject services)
```

---

## 🎓 Key Concepts for Laravel Developers

| Java Component | Laravel Equivalent |
|----------------|-------------------|
| `Manager/AddVehicle.java` | Controller |
| `Services/VehicleService.java` | Service Class |
| `Repository/VehicleRepositoryInterface.java` | Repository Interface |
| `Repository/JsonVehicleRepository.java` | Repository Implementation |
| `Validators/VehicleValidator.java` | Form Request |
| `Vehicles/Vehicle.java` | Model/Entity |
| `ScanData.java` | Service Container + Routes |

---

## 💡 What You'll Learn

### 1. **Dependency Injection**
- How to inject dependencies through constructors
- Why it makes code testable and flexible

### 2. **Repository Pattern**
- Abstracting data access
- Easy to swap JSON for MySQL or MongoDB

### 3. **Validation Separation**
- Keeping validation logic separate
- Reusable validation across the app

### 4. **Service Layer**
- Business logic separate from controllers
- Following Single Responsibility Principle

### 5. **Interface-Based Design**
- Programming to interfaces, not implementations
- Making code flexible and maintainable

---

## 🔧 Extending the Application

### Adding MySQL Support

1. Create `MySqlVehicleRepository.java`:
```java
public class MySqlVehicleRepository implements VehicleRepositoryInterface {
    @Override
    public boolean save(Vehicle vehicle) {
        // MySQL save logic
    }
}
```

2. Change ONE line in `ScanData.java`:
```java
// From:
this.repository = new JsonVehicleRepository("src/vehicles.json");

// To:
this.repository = new MySqlVehicleRepository(connection);
```

That's it! No other code changes needed. This is the power of SOLID! 🎉

---

## 🐛 Troubleshooting

### "Cannot find symbol: class Gson"
➡️ Make sure Maven dependencies are installed. See [`SETUP.md`](SETUP.md)

### "File not found: vehicles.json"
➡️ The file will be created automatically when you add the first vehicle

### Validation errors
➡️ Check that:
- Vehicle name is not empty
- Vehicle brand is not empty  
- Vehicle price is greater than 0

---

## 📖 Additional Reading

### SOLID Principles:
- [`SOLID_GUIDE.md`](SOLID_GUIDE.md) - Complete guide with examples

### Architecture:
- [`ARCHITECTURE.md`](ARCHITECTURE.md) - Visual diagrams and flow

### Java Basics:
- [`JAVA_FOR_PHP_DEVS.md`](JAVA_FOR_PHP_DEVS.md) - Syntax comparison

### Setup:
- [`SETUP.md`](SETUP.md) - Installation and configuration

---

## 🎯 What Makes This Code SOLID?

### Before Refactoring:
```java
// Everything in one class ❌
public class AddVehicle {
    public void addVehicle() {
        // Get input
        // Validate  
        // Save to file
        // All mixed together!
    }
}
```

### After Refactoring:
```java
// Separated concerns ✅
AddVehicle (Controller)
    → VehicleInputService (Gets input)
    → VehicleService (Business logic)
        → VehicleValidator (Validates)
        → VehicleRepository (Saves data)
```

Each class has **one clear responsibility** and can be tested/modified independently!

---

## 🤝 Contributing

This is a learning project. Feel free to:
- Add more features (Remove vehicle, Rent vehicle, etc.)
- Implement MySQL repository
- Add unit tests
- Improve validation

---

## 📝 License

MIT License - Use this code for learning and teaching!

---

## ❓ Questions?

If you're confused about any part:
1. Check the relevant `.md` file in the project
2. Look at the comments in the code
3. Compare with Laravel examples in the guides

Remember: **Java is just more verbose PHP with strict typing!** The patterns are the same. 🚀

---

## 🎉 You've Got This!

Coming from Laravel, you already understand:
- ✅ MVC pattern
- ✅ Dependency Injection
- ✅ Repository pattern
- ✅ Service layer
- ✅ Validation

This is the same thing, just in Java syntax! Happy coding! 🎊

