# 🔧 FIX: Gson Library Errors - STEP BY STEP

## The Problem
You're seeing errors like:
- ❌ `Cannot resolve symbol 'google'`
- ❌ `Cannot resolve symbol 'Gson'`
- ❌ `Cannot resolve symbol 'GsonBuilder'`

**Root Cause:** The Gson library hasn't been downloaded yet because IntelliJ hasn't loaded the Maven project.

---

## ✅ SOLUTION: Load Maven Project in IntelliJ

### Step 1: Open Maven Tool Window
1. Look at the **right side** of IntelliJ window
2. Click on the **"Maven"** tab (vertical text)
   - If you don't see it, go to: **View → Tool Windows → Maven**

### Step 2: Add pom.xml as Maven Project
**Option A: Using Maven Tool Window**
1. In the Maven window, click the **"+"** button (top-left)
2. Navigate to: `E:\java\java_projcets\demo\pom.xml`
3. Select it and click **OK**
4. Wait for Maven to download dependencies (watch bottom status bar)

**Option B: Right-click on pom.xml**
1. In the Project view, find **pom.xml** at the root
2. **Right-click** on `pom.xml`
3. Select **"Add as Maven Project"** (or "Import Maven Project")
4. Wait for dependencies to download

**Option C: From File Menu**
1. **File → New → Module from Existing Sources**
2. Select `E:\java\java_projcets\demo\pom.xml`
3. Choose **"Import project from external model"**
4. Select **Maven** and click **Finish**

---

## What Should Happen

You'll see:
1. ⏳ **Status bar** shows: "Importing Maven projects..."
2. ⏳ **Maven tool window** shows: Downloading dependencies
3. ✅ **Project structure** shows: "External Libraries" → Maven: com.google.code.gson:gson:2.10.1
4. ✅ **Red underlines disappear** from `import com.google.gson.*`

**This may take 1-5 minutes depending on your internet speed.**

---

## Verify It Worked

### Check 1: External Libraries
1. In **Project** view, expand **"External Libraries"**
2. You should see: **Maven: com.google.code.gson:gson:2.10.1**

### Check 2: No More Red Underlines
1. Open `JsonVehicleRepository.java`
2. The imports should **NOT** be red anymore:
   ```java
   import com.google.gson.Gson;        // ✅ Should be white/green
   import com.google.gson.GsonBuilder; // ✅ Should be white/green
   ```

### Check 3: Maven Dependencies
1. Open **Maven** tool window (right side)
2. Expand your project → **Dependencies**
3. You should see **gson:2.10.1**

---

## Still Not Working? Try This:

### Force Maven Reimport
1. In **Maven** tool window (right side)
2. Click the **"Reload All Maven Projects"** button (circular arrows icon)
3. Wait for it to finish

### Invalidate Caches
1. **File → Invalidate Caches / Restart**
2. Select **"Invalidate and Restart"**
3. Wait for IntelliJ to restart and reindex

### Manual Maven Command (If you have Maven installed)
```bash
cd E:\java\java_projcets\demo
mvn clean install
```

Then in IntelliJ:
1. **File → Project Structure**
2. **Libraries → +** → **From Maven**
3. Type: `com.google.code.gson:gson:2.10.1`
4. Click **OK**

---

## Don't Have Maven Installed? Alternative Solution

### Download Gson JAR Manually

1. **Download Gson:**
   - Go to: https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/
   - Download: `gson-2.10.1.jar`

2. **Create lib folder:**
   ```
   E:\java\java_projcets\demo\lib\
   ```

3. **Copy the JAR file:**
   - Place `gson-2.10.1.jar` in the `lib` folder

4. **Add to IntelliJ:**
   - Right-click on the `lib` folder in IntelliJ
   - Select **"Add as Library"**
   - Click **OK**

---

## After Dependencies Load

Once Gson is loaded, you should be able to:
1. ✅ Build the project without errors
2. ✅ Run `Main.java`
3. ✅ Add and list vehicles successfully

---

## Quick Visual Check

**BEFORE (with errors):**
```java
import com.google.gson.Gson;        // ❌ RED underline
import com.google.gson.GsonBuilder; // ❌ RED underline
```

**AFTER (dependencies loaded):**
```java
import com.google.gson.Gson;        // ✅ NO underline
import com.google.gson.GsonBuilder; // ✅ NO underline
```

---

## Summary

**You need to:** Load `pom.xml` as a Maven project in IntelliJ

**Easiest way:** 
1. Right-click `pom.xml` 
2. Select "Add as Maven Project"
3. Wait for download
4. Done! ✅

The code is correct - you just need to download the Gson library! 🚀

