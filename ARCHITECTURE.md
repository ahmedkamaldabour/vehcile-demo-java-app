# Architecture Diagram - SOLID Refactored Application

## Layer Architecture (Similar to Laravel)

```
┌─────────────────────────────────────────────────────────────┐
│                        Main.java                             │
│                    (Entry Point)                             │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                      ScanData.java                           │
│              (Bootstrap / Service Container)                 │
│   - Creates all dependencies                                 │
│   - Wires everything together (Dependency Injection)         │
└──────────────────────┬──────────────────────────────────────┘
                       │
        ┌──────────────┴──────────────┐
        │                             │
        ▼                             ▼
┌─────────────────┐          ┌─────────────────┐
│  AddVehicle     │          │ ListAllVehicle  │   ◄── CONTROLLER LAYER
│  (Controller)   │          │  (Controller)   │       (Like Laravel Controllers)
└────────┬────────┘          └────────┬────────┘
         │                            │
         │ uses                       │ uses
         │                            │
         ▼                            │
┌─────────────────┐                   │
│ VehicleService  │                   │           ◄── SERVICE LAYER
│  (Business      │◄──────────────────┘               (Business Logic)
│   Logic)        │
└────┬──────┬─────┘
     │      │
     │      │ uses
     │      ▼
     │   ┌─────────────────────┐
     │   │ VehicleValidator    │                 ◄── VALIDATOR LAYER
     │   │  (Validation)       │                     (Like Form Requests)
     │   └─────────────────────┘
     │
     │ uses
     ▼
┌─────────────────────────────┐
│VehicleRepositoryInterface   │                  ◄── ABSTRACTION
│        (Interface)          │                      (Contract/Interface)
└────────────┬────────────────┘
             │ implements
             ▼
┌─────────────────────────────┐
│ JsonVehicleRepository       │                  ◄── REPOSITORY LAYER
│  (Data Access - JSON)       │                      (Like Eloquent/Repository)
└─────────────────────────────┘
             │
             │ reads/writes
             ▼
┌─────────────────────────────┐
│      vehicles.json          │                  ◄── DATA STORAGE
│     (JSON Database)         │                      (Like MySQL Database)
└─────────────────────────────┘
```

---

## Data Flow: Adding a Vehicle

```
User Input
   │
   ▼
┌─────────────────────────────────────────────────────┐
│ 1. ScanData                                         │
│    User selects "Add Vehicle" from menu             │
└──────────────────────┬──────────────────────────────┘
                       │ calls
                       ▼
┌─────────────────────────────────────────────────────┐
│ 2. AddVehicle (Controller)                          │
│    addVehicle() method orchestrates the flow        │
└──────────────────────┬──────────────────────────────┘
                       │ calls
                       ▼
┌─────────────────────────────────────────────────────┐
│ 3. VehicleInputService                              │
│    getVehicleFromUser() - Gets name, brand, price   │
└──────────────────────┬──────────────────────────────┘
                       │ returns Vehicle object
                       ▼
┌─────────────────────────────────────────────────────┐
│ 4. VehicleService                                   │
│    addVehicle(vehicle) - Business logic             │
└──────────────────────┬──────────────────────────────┘
                       │ validates
                       ▼
┌─────────────────────────────────────────────────────┐
│ 5. VehicleValidator                                 │
│    validate(vehicle) - Checks rules                 │
│    ✓ Name not empty                                 │
│    ✓ Brand not empty                                │
│    ✓ Price > 0                                      │
└──────────────────────┬──────────────────────────────┘
                       │ if valid
                       ▼
┌─────────────────────────────────────────────────────┐
│ 6. JsonVehicleRepository                            │
│    save(vehicle) - Saves to JSON file               │
└──────────────────────┬──────────────────────────────┘
                       │ writes to
                       ▼
┌─────────────────────────────────────────────────────┐
│ 7. vehicles.json                                    │
│    [{"name":"Tesla","brand":"Tesla","price":50000}] │
└─────────────────────────────────────────────────────┘
```

---

## SOLID Principles Applied

### 🟢 Single Responsibility Principle (SRP)

Each class has ONE job:

- **VehicleValidator** → Only validates
- **VehicleService** → Only business logic
- **JsonVehicleRepository** → Only file I/O
- **AddVehicle** → Only coordinates the flow
- **VehicleInputService** → Only gets user input

---

### 🟢 Open/Closed Principle (OCP)

Open for extension, closed for modification:

```
Want MySQL instead of JSON?

┌─────────────────────────────┐
│VehicleRepositoryInterface   │  ◄── Interface stays the same
└────────────┬────────────────┘
             │
    ┌────────┴────────┐
    │                 │
    ▼                 ▼
┌──────────┐    ┌──────────────┐
│  JSON    │    │   MySQL      │  ◄── Just add new implementation
│  Repo    │    │   Repo       │      NO changes to existing code!
└──────────┘    └──────────────┘
```

---

### 🟢 Liskov Substitution Principle (LSP)

Any implementation can replace the interface:

```java
// Both work identically
VehicleRepositoryInterface repo;

repo = new JsonVehicleRepository();    // ✓ Works
repo = new MySqlVehicleRepository();   // ✓ Works
repo = new MongoVehicleRepository();   // ✓ Works
```

---

### 🟢 Interface Segregation Principle (ISP)

Small, focused interfaces:

```java
// We DON'T have a giant interface with 20 methods
// We have small, focused ones

interface VehicleRepositoryInterface {
    boolean save(Vehicle vehicle);      // Only what we need
    List<Vehicle> findAll();
}
```

---

### 🟢 Dependency Inversion Principle (DIP)

Depend on abstractions (interfaces), not concrete classes:

```
❌ BAD (Tight Coupling):
┌──────────────┐     depends on     ┌──────────────────┐
│  AddVehicle  │ ─────────────────> │ JsonVehicle      │
│ (Controller) │   (concrete class) │ Repository       │
└──────────────┘                    └──────────────────┘

✓ GOOD (Loose Coupling):
┌──────────────┐     depends on     ┌──────────────────┐
│  AddVehicle  │ ─────────────────> │ Vehicle          │
│ (Controller) │    (abstraction)   │ Repository       │
└──────────────┘                    │ Interface        │
                                    └──────────────────┘
                                            ▲
                                            │ implements
                                    ┌──────────────────┐
                                    │ JsonVehicle      │
                                    │ Repository       │
                                    └──────────────────┘
```

---

## Laravel Comparison

| Java Class | Laravel Equivalent | Purpose |
|------------|-------------------|---------|
| `Main.java` | `public/index.php` | Entry point |
| `ScanData.java` | `bootstrap/app.php` + Service Container | Bootstrap & DI |
| `Manager/AddVehicle.java` | `App\Http\Controllers\VehicleController` | Controller |
| `Services/VehicleService.java` | `App\Services\VehicleService` | Business logic |
| `Repository/VehicleRepositoryInterface.java` | Repository Interface | Contract |
| `Repository/JsonVehicleRepository.java` | `App\Repositories\VehicleRepository` | Data access |
| `Validators/VehicleValidator.java` | `App\Http\Requests\StoreVehicleRequest` | Validation |
| `Vehicles/Vehicle.java` | `App\Models\Vehicle` | Entity/Model |

---

## Key Takeaways

1. **Each layer has a clear purpose** - No mixing of concerns
2. **Dependencies flow inward** - Controllers depend on Services, Services depend on Repositories
3. **Everything is testable** - Can mock any dependency
4. **Easy to extend** - Add new features without breaking existing code
5. **Type-safe** - Java's strict typing catches errors at compile time

This is professional, production-ready code structure! 🎉

