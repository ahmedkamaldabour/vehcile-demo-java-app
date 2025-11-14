# Setup Instructions

## You need to add the Gson library to make this work!

### Option 1: Download Gson JAR manually

1. Download Gson from: https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar

2. Create a `lib` folder in your project:
   ```
   E:\java\java_projcets\demo\lib\
   ```

3. Place the downloaded `gson-2.10.1.jar` file in the `lib` folder

4. In IntelliJ IDEA:
   - Right-click on the `lib` folder
   - Select "Add as Library"
   - Click OK

### Option 2: Convert to Maven project (Recommended)

1. Create a `pom.xml` file in the project root with this content:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>vehicle-rental</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- Gson for JSON handling -->
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.10.1</version>
        </dependency>
    </dependencies>
</project>
```

2. In IntelliJ:
   - Right-click on `pom.xml`
   - Select "Add as Maven Project"
   - Wait for dependencies to download

---

## How to Run the Application

1. Make sure Gson is installed (see above)

2. Run the Main.java file:
   - Right-click on `Main.java`
   - Select "Run 'Main.main()'"

3. You'll see a menu:
   ```
   Vehicle Rental System
   ======================
   1. List Vehicles
   2. Add a Vehicle
   3. Remove a Vehicle
   4. Rent a Vehicle
   5. Return a Vehicle
   6. Exit
   ```

4. Choose option 2 to add a vehicle
5. Choose option 1 to list all vehicles

---

## Troubleshooting

### "Cannot find symbol: class Gson"
- You need to add the Gson library (see Option 1 or 2 above)

### "File not found: vehicles.json"
- The application will create this file automatically when you add your first vehicle
- Make sure the path in `ScanData.java` is correct for your system

### "Scanner issues with input"
- This is common with Scanner in Java
- The `nextLine()` calls in the code handle this properly

---

## What Changed?

Your code has been completely refactored following SOLID principles. See `SOLID_GUIDE.md` for a detailed explanation.

### New Files Created:
- `Services/VehicleService.java` - Business logic
- `Services/VehicleInputService.java` - Input handling
- `Repository/VehicleRepositoryInterface.java` - Repository contract
- `Repository/JsonVehicleRepository.java` - JSON data access
- `Validators/VehicleValidator.java` - Validation logic
- `Validators/ValidationResult.java` - Validation results

### Modified Files:
- `Manager/AddVehicle.java` - Now uses dependency injection
- `Manager/ListAllVehicle.java` - Now uses repository pattern
- `ScanData.java` - Now bootstraps dependencies (like Laravel's Service Container)

