# JVM

Java Virtual Machine (JVM) is an engine that provides runtime environment to drive the Java Code or applications. It converts Java bytecode into machines language. JVM is a part of Java Runtime Environment (JRE). In other programming languages, the compiler produces machine code for a particular system. However, Java compiler produces code for a Virtual Machine known as Java Virtual Machine.

Byte code is an intermediate code between the source code and machine code. It is a low-level code that is the result of the compilation of a source code which is written in a high-level language. It is processed by a virtual machine like Java Virtual Machine (JVM). 

Machine code is a set of instructions that is directly machine-understandable and it is processed by the Central Processing Unit (CPU). Machine code is in binary (0’s and 1’s) format which is completely different from the byte code and source code.

The JVM consists of three distinct components:
- Class Loader,
- Runtime Memory/Data Area,
- Execution Engine.

---

Prerequisites:
- Compile the Source Code (Compile to Bytecode)
  - Once the source code is written, you need to compile it using the Java Compiler (javac).
  - The compiler takes your .java file and translates it into an intermediate form called bytecode. The bytecode is saved in .class files.
  - Bytecode is the platform-independent code that the JVM understands and can execute on any platform.
- Run the Bytecode on the JVM (Execution)
   - After compiling the source code, you need to run the bytecode using the Java Virtual Machine (JVM). The JVM reads the .class file and executes the bytecode.
   - The JVM interprets the bytecode and translates it into native machine instructions specific to the platform on which the JVM is running (which is why Java is platform-independent at the bytecode level).

According to the Java SE specification, in order to get code running in the JVM, you need to complete three steps:
- Loading the Bytecode (Class Loading)
  - When a Java program is executed, the first thing that happens is the loading of the bytecode (i.e., the compiled .class files) into the JVM. This step is handled by the Class Loader.
  - What happens during class loading? 
    - Class Loaders: Java uses class loaders to load classes into the JVM when they are needed. There are different types of class loaders, including:
      - Bootstrap ClassLoader: Loads core Java classes from the JDK (such as java.lang.*). 
      - Extension ClassLoader: Loads classes from Java extension libraries (like those in jre/lib/ext). 
      - System/Application ClassLoader: Loads classes from the classpath (typically used for application-specific classes). 
    - Loading Process:
      - The Class Loader checks if a class has already been loaded by the JVM. If it has, the class is reused. 
      - If it hasn’t been loaded, the class loader will read the corresponding .class file (which contains the bytecode) from the file system, JAR, or network location. 
      - The bytecode is loaded into the method area of the JVM memory, which is part of the heap dedicated to storing class-related information, like methods and variables.
- Linking the Bytecode
    - After loading the class bytecode, the next phase is linking. Linking involves verifying, preparing, and resolving references. It happens in three stages:
        - Verification
          - The JVM checks whether the bytecode follows the rules of the Java language. This ensures that the bytecode is not corrupted and that it adheres to the expected structure.
          - The bytecode verifier checks for invalid accesses, illegal type casts, stack overflows, and other errors in the bytecode that could lead to runtime exceptions or crashes.
        - Preparation:
          - In this phase, the JVM allocates memory for static fields, initializes default values (like 0 for integers, null for objects), and prepares other necessary resources. 
          - Static variables are initialized with default values during the preparation phase.
        - Resolution
          - In the resolution phase, symbolic references in the bytecode (such as method calls and field accesses) are replaced with direct references to the actual memory locations.
            - A symbolic reference is a reference to a class, method, or field by name, rather than a direct memory address. The JVM resolves these references dynamically at runtime by replacing them with actual memory addresses.
          - For example, when a method is called (e.g., System.out.println()), the JVM resolves this method call to the actual method address (memory location) during this phase.
          - This resolution includes linking the class to its superclasses and resolving references to external classes (i.e., classes referenced by the loaded class).
    - Example: When you write code like System.out.println("Hello, World!"), the JVM must resolve the method reference println from System.out to the actual implementation in the PrintStream class during the linking process.
- Initialization (Executing Bytecode and Initializing Objects)
    - After linking, the final phase involves initializing the class and executing its bytecode. This is the stage where objects are instantiated, static initializers are executed, and methods are invoked.
    - Initialization Process:
      - Static Initialization:
        - If the class contains any static blocks or static fields, they are initialized during this phase. 
        - Static fields and methods are initialized only once, the first time the class is referenced or an object of the class is created. 
        - For example, static variables are assigned their values at this point, and static blocks are executed. 
      - Instance Initialization:
        - When an object of a class is created (via new), the constructor is called to initialize the object. This involves setting the initial values for instance variables, executing any instance initialization code, and invoking the constructor of the superclass. 
      - Method Invocation:
        - When a method is called (e.g., obj.someMethod()), the corresponding bytecode is executed. During this execution, the JVM manages the call stack and local variables, and it invokes the method at the appropriate memory address resolved earlier during linking. 
    - Example: When an object of HelloWorld is created, the constructor (if defined) is executed, and the instance variables (if any) are initialized. In the case of a static method (e.g., public static void main(String[] args)), the method is called by the JVM during the program's execution.

Summary of the Three Steps:
- Class Loading: The JVM loads the .class files (bytecode) into memory using the Class Loader.
- Linking: The JVM performs verification, preparation, and resolution to ensure the bytecode is correct, allocate memory for static fields, and resolve references to methods, fields, and classes.
- Initialization: The JVM executes the bytecode, initializes static fields, invokes static initializers (if any), and initializes instance objects and methods.

--

The compilation phase happens before runtime, when the Java source code (.java file) is converted into bytecode (.class file) by the Java Compiler (javac). It performs the following tasks:   
- Lexical Analysis: Converts Java source code into tokens.
- Syntax and Semantic Analysis: Ensures the code follows Java syntax rules.
- Type Checking: Ensures type safety and correct method usage.
- Symbol Resolution (Limited): The compiler resolves references within the same compilation unit (i.e., classes available at compile-time).
- Bytecode Generation: Translates Java code into platform-independent bytecode.
- Optimization (Limited): Performs some optimizations like constant folding and method inlining (for final methods).

Compiler's Limitation in Linking
- The Java compiler does not perform full linking. It does not resolve references to external classes fully.
- If an external class is referenced but not available at compile time, compilation fails.
- It does not resolve method calls to actual memory locations—this is handled at runtime by the JVM.

---

References:
- https://javarush.com/en/groups/posts/en.646.how-classes-are-loaded-into-the-jvm
- https://www.freecodecamp.org/news/jvm-tutorial-java-virtual-machine-architecture-explained-for-beginners/

## java vs javac

- Technically, javac is the program that translates Java code into bytecode (.class file) - an intermediate format which is understandable by the Java Virtual Machine (JVM). And java is the program that starts the JVM, which in turn, loads the `.class` file, verifies the bytecode and executes it.
- Summary
    - Java compiler: javac
        - `javac sample.java`
    - Java application launcher: java
        - `java sample`
    - Java archive tool: jar
- References
    - <https://www.codejava.net/java-core/tools/understanding-the-triad-tools-javac-java-and-jar-in-jdk>
    - <https://www.tutorialspoint.com/what-is-the-difference-between-javac-java-commands>

## What is the difference between .java files vs .class file?

- A .java file contains your Java source code while a .class file contains the Java bytecode produced by the Java compiler.
- It is your .class files that run on the JVM to execute a Java application.
    - .class -> compiled (for JVM)
    - .java -> source (for humans)
- Ref: <https://stackoverflow.com/questions/1015340/class-vs-java>

## JVM Structure Overview

The Java Virtual Machine (JVM) is the engine that runs Java bytecode and enables platform independence. It translates bytecode into machine-specific code to run on any platform without modification, providing the crucial feature of Java’s write once, run anywhere philosophy.

The JVM is a part of the Java Runtime Environment (JRE), which includes the JVM, libraries, and other supporting components.

### JVM architecture

The JVM consists of the following key components:

1. ClassLoader
   - The Class Loader Subsystem is responsible for loading class files into the JVM memory at runtime. It loads the bytecode files (.class) and prepares them for execution.
   - Functions:
     - Loading: The class loader reads .class files and loads them into memory.
     - Linking: Ensures that classes are linked with the appropriate dependencies (like methods and fields).
     - Initialization: The class loader initializes the static variables and runs static blocks for each class.
   - Types of Class Loaders:
     - Bootstrap ClassLoader: Loads core Java classes from the JDK (e.g., java.lang.*, java.util.*).
     - Extension ClassLoader: Loads classes from the Java extensions directory (lib/ext).
     - System ClassLoader: Loads application classes from the classpath.
2. Runtime Data Areas
   - The Runtime Data Areas are regions of memory allocated to various components and used during program execution. The JVM divides memory into different sections:
   - Method Area:
     - Stores class-level information like class metadata, runtime constant pool, field and method data, and method code. 
     - Shared among all threads. 
   - Heap:
     - The heap is used for dynamic memory allocation and is the place where objects and arrays are stored. 
     - Managed by the garbage collector (GC) to reclaim memory for unused objects. 
   - Stack:
     - Each thread has its own stack used for storing method frames, local variables, and partial results. 
     - A method frame contains method arguments, local variables, and the return address (the code location to return to after the method finishes execution). 
     - Stack overflow occurs if the stack grows beyond its limit. 
   - Program Counter (PC) Register:
     - Each thread has its own PC register that holds the address of the current instruction being executed by the thread. 
     - Thread context switching involves saving and restoring the state of the PC register for the threads. 
   - Native Method Stack:
     - It’s used for managing native method calls (i.e., methods written in languages other than Java, like C or C++). 
     - This stack supports the execution of native methods that use the JNI (Java Native Interface).
3. Execution Engine
   - The Execution Engine is responsible for executing the bytecode instructions. It has several components that work together:
     - Interpreter:
       - Reads and executes bytecode instructions one at a time. 
       - Suitable for quick execution of short-lived programs but less efficient for long-running programs. 
     - Just-In-Time (JIT) Compiler:
       - The JIT compiler translates the bytecode into native machine code during runtime, improving performance by compiling frequently used methods into optimized native code. 
       - The JIT compiler is triggered by the JVM when it detects that a method or code path is being called frequently. 
     - Garbage Collector (GC):
       - The GC automatically manages memory by reclaiming memory from unused objects (objects that are no longer referenced). 
       - Various types of garbage collection algorithms exist (e.g., Mark-and-Sweep, Generational Garbage Collection). 
       - The main goal is to avoid memory leaks by freeing unused memory and heap fragmentation.
4. Native Interface (JNI)
   - The Java Native Interface (JNI) allows Java code to interact with programs and libraries written in other languages, such as C or C++. 
   - JNI enables Java programs to call native code and vice versa. 
   - It can be used when certain performance optimizations or hardware-level interactions are needed that cannot be achieved in pure Java.
5. Native Libraries
   - Native libraries are platform-dependent code that is written in languages such as C, C++, or assembly, and are used to interact with the underlying system hardware or OS-specific features. 
   - These libraries interact with the JVM through the JNI interface. 
   - Examples include graphics libraries or database connectors implemented natively for platform-specific optimizations.


#### JVM Structure Diagram (Overview)

```text
+-----------------------------------------+
|              JVM (Java Virtual Machine) |
+-----------------------------------------+
|    Class Loader Subsystem               |
|    - Loads, links, and initializes classes |
+-----------------------------------------+
|    Runtime Data Areas                   |
|    - Heap: Stores objects               |
|    - Stack: Method call stacks          |
|    - Method Area: Stores class info     |
|    - Program Counter: Thread instruction pointer |
|    - Native Method Stack: For native methods |
+-----------------------------------------+
|    Execution Engine                     |
|    - Interpreter: Executes bytecode     |
|    - JIT Compiler: Optimizes bytecode   |
|    - Garbage Collector: Manages memory  |
+-----------------------------------------+
|    Native Interface (JNI)               |
|    - Enables interaction with native code |
+-----------------------------------------+
|    Native Libraries (Platform-Specific) |
|    - Libraries written in native languages |
+-----------------------------------------+
```

### JVM Process Flow

- Loading: The Class Loader loads the classes (bytecode) from disk (either from the classpath, JAR, etc.) into memory.
- Execution: The Execution Engine executes the bytecode instructions. Initially, the Interpreter runs the bytecode, but frequently executed methods are compiled into native machine code by the JIT compiler.
- Memory Management: The JVM manages memory through heap and stack memory and uses the garbage collector to reclaim memory from unused objects.
- Native Code Interaction: If needed, the JNI allows calling and using platform-specific native code.

### JVM vs JRE vs JDK

| Component                          | Description                                                  |
|------------------------------------|--------------------------------------------------------------|
| **JVM**                            | Java Virtual Machine, executes Java bytecode.                |
| **JRE (Java Runtime Environment)** | Contains JVM + libraries to run Java applications.           |
| **JDK (Java Development Kit)**     | Includes JRE + development tools (compiler, debugger, etc.). |

### Garbage Collectors (GC)

Java's Garbage Collection (GC) is an automatic memory management process that reclaims memory occupied by objects that are no longer reachable. The JVM Garbage Collector (GC) prevents memory leaks and optimizes application performance by freeing unused memory.

Types of Garbage Collectors in Java:
1. Serial Garbage Collector (-XX:+UseSerialGC)
   - Best for: Single-threaded applications, small heaps.
   - How it works:
     - Uses a single thread to handle garbage collection.
     - Performs Stop-The-World (STW) pauses, meaning the application stops during GC.
     - Efficient for small Java applications but not scalable for large applications.
2. Parallel Garbage Collector (-XX:+UseParallelGC)
   - Best for: Multi-threaded applications, medium to large heaps.
   - How it works:
     - Uses multiple threads to perform garbage collection. 
     - Reduces pause times compared to the Serial GC. 
     - Focuses on high throughput rather than low latency.
3. G1 Garbage Collector (-XX:+UseG1GC) / **G1 GC (default)**
   - Best for: Low-latency applications, large heaps (multi-GB).
   - How it works:
     - Divides heap into regions instead of contiguous spaces (Young, Old, Humongous). 
     - Performs incremental garbage collection to reduce long pauses. 
     - Uses concurrent marking to prioritize garbage collection based on regions with most garbage.
4. Z Garbage Collector (ZGC) (-XX:+UseZGC)
   - Best for: Ultra-low latency applications, large heaps (multi-TB).
   - How it works:
     - Designed for sub-millisecond GC pauses, even with large heaps. 
     - Uses concurrent garbage collection to reduce stop-the-world pauses. 
     - Supports scalability up to terabytes of heap memory.
5. Shenandoah Garbage Collector (-XX:+UseShenandoahGC)
   - Best for: Low-latency applications, medium-to-large heaps.
   - How it works:
     - Performs almost concurrent garbage collection with pause times <10ms.
     - Reduces stop-the-world (STW) phases compared to G1 GC.
     - More efficient than G1GC for large, dynamic applications.

---

Summary:
- Serial GC → Best for small apps.
- Parallel GC → Good for throughput-oriented apps.
- G1 GC → Ideal for large applications with low pause times.
- ZGC → Best for ultra-low latency (real-time applications).
- Shenandoah GC → Alternative to G1 with even lower pause times.

#### Young Generation vs Old Generation

- The Young Generation is where all new objects are allocated and aged. When the young generation fills up, this causes a minor garbage collection. Minor collections can be optimized assuming a high object mortality rate. A young generation full of dead objects is collected very quickly. Some surviving objects are aged and eventually move to the old generation.
- The Old Generation is used to store long surviving objects. Typically, a threshold is set for young generation object and when that age is met, the object gets moved to the old generation. Eventually the old generation needs to be collected. This event is called a major garbage collection.
- Major garbage collection are also Stop the World events. Often a major collection is much slower because it involves all live objects. So for Responsive applications, major garbage collections should be minimized. Also note, that the length of the Stop the World event for a major garbage collection is affected by the kind of garbage collector that is used for the old generation space.
- The old generation is used to store long-lived objects. Eventually, the old generation also needs to be garbage collected and this is called the major garbage collection. The Permanent generation contains metadata required by the JVM to describe the classes and methods used in the application.
- Ref:
    - <https://www.oracle.com/technetwork/tutorials/tutorials-1873457.html#:~:text=Stop%20the%20World%20Event%20%2D%20All,to%20store%20long%20surviving%20objects>.
    - <https://recepinanc.medium.com/til-12-garbage-collection-young-vs-old-generations-ab95b6a68653#:~:text=The%20old%20generation%20is%20used,methods%20used%20in%20the%20application>.

#### How does GC work?

The Java Garbage Collector (GC) is an automatic memory management system that identifies and removes objects that are no longer needed to free up memory and prevent memory leaks.

The JVM (Java Virtual Machine) divides heap memory into different regions, and the GC runs in the background to clean up unused objects:
- Young Generation → Stores new objects
  - Eden Space (New objects are created here)
  - Survivor Spaces (S0 & S1) (Objects that survive multiple GC cycles)
- Old Generation (Tenured) → Stores long-lived objects

Steps of Garbage Collection Process:
- Step 1: Mark Phase
  - The GC scans the heap and identifies live objects (i.e., objects still referenced).
  - Any object not reachable from active references is considered garbage.
- Step 2: Sweep/Collect Phase
  - The GC removes unreferenced objects and reclaims memory.
  - Some GCs may compact memory to prevent fragmentation.
- Step 3: Compact Phase (Optional)
  - Some GCs rearrange remaining objects to reduce fragmentation, improving allocation efficiency.

Does G1 GC Work the Same as Other Garbage Collectors?

No, G1 GC (Garbage-First Garbage Collector) works differently compared to older garbage collectors like Serial GC or Parallel GC. It is designed for low pause times, better performance, and scalability.

Stop-The-World (STW) events do happen in G1 GC, but the goal of G1 GC is to minimize these pauses as much as possible and make them predictable. G1 GC is designed to split garbage collection work across multiple phases, allowing incremental garbage collection while keeping STW pauses short and manageable.

1. Heap is Divided into Regions Instead of Fixed Generations
   - Unlike traditional collectors that divide heap memory into Young and Old Generations, G1 GC divides the heap into multiple dynamic-sized regions (each a few MB in size). 
   - Regions can belong to Eden, Survivor, Old, or Humongous categories. 
   - Regions are dynamically resized based on application needs. 
   - This avoids heap fragmentation and makes garbage collection more efficient.
2. G1 GC Uses Concurrent Marking & Incremental Collection
   - Instead of stopping the application for long GC pauses, G1 GC runs concurrently with the application, marking and collecting garbage incrementally.
     - Phase 1 – Marking: G1 identifies live objects and classifies regions as mostly empty or full.
     - Phase 2 – Evacuation (Copying): G1 copies live objects to empty regions, freeing up entire old regions instead of compacting.
     - Phase 3 – Cleanup: The freed-up regions are added back to available memory. 
   - This "Garbage-First" approach prioritizes cleaning up regions with the most garbage first, minimizing pause times.
3. G1 GC Has Predictable Low Pause Times
   - G1 splits the work across multiple GC cycles rather than running one big stop-the-world collection.

#### What is a Stop Of The World phase?

- A "Stop-The-World" (STW) phase is a concept primarily associated with garbage collection (GC) in managed runtime environments such as Java's Java Virtual Machine (JVM). During an STW phase, all application threads are paused to allow the garbage collector to safely perform its tasks. This means that the application does not execute any instructions during this period.
- Parallel and Concurrent GC: Modern garbage collectors, such as the G1 (Garbage First) collector, ZGC (Z Garbage Collector), and Shenandoah, aim to minimize the duration of STW pauses by performing most of the GC work concurrently with application threads.
- Young Generation Collection (Minor GC): Often involves shorter STW pauses. The young generation is where most new objects are allocated and collected. Since many objects quickly become unreachable, the STW pause here is usually brief.
- Old Generation Collection (Major GC/Full GC): Typically involves longer STW pauses because it deals with a larger set of objects that have survived multiple garbage collection cycles.
- Minor GC happens in the Young Generation when the Eden space is full and there isn’t enough space to allocate new objects. For example: If your application is allocating objects frequently, it could trigger multiple Minor GCs within a short period. On the other hand, if object allocation is low, Minor GCs will happen less often.
- Major GC occurs when the Old Generation is full and cannot accommodate new objects or if there is no space for promoted objects from the Young Generation.
- Full GC can also happen if there is a lack of memory or if the JVM is explicitly forced to do so (e.g., System.gc()). Full GC is a more comprehensive garbage collection process that includes both the Young Generation and the Old Generation.
  - Full GC is triggered by the JVM under several circumstances:
    - When there is not enough memory in both the Young and Old Generations.
    - If the Old Generation is full and Major GC fails to reclaim enough space.
    - Explicit calls to System.gc() (though calling this method is discouraged).
    - Some garbage collectors like G1 GC might trigger Full GC under specific conditions when compacting the heap.
- Ref: <https://www.oracle.com/technetwork/tutorials/tutorials-1873457.html#:~:text=Stop%20the%20World%20Event%20%2D%20All,to%20store%20long%20surviving%20objects>.

---

When Does Stop-The-World (STW) Happen?

Garbage Collection is the main cause of STW events. Depending on the GC algorithm, STW can occur at different times:

- Full GC (Major Collection) 🟥 → ALWAYS causes a long STW pause
  - When the old generation is full and needs cleanup.
  - JVM compacts memory, moving objects to reduce fragmentation.
  - Most expensive type of STW event.
- Young Generation Collection (Minor GC) 🟨
  - STW happens briefly to move objects from Eden → Survivor → Old Gen.
  - More frequent but shorter pauses than Full GC.
- G1 GC Evacuation Pause 🟦
  - STW occurs when G1 moves live objects from one region to another.
  - Optimized to keep pauses low, tunable via -XX:MaxGCPauseMillis=200.

### Common JVM Flags

- What are JVM flags? What is the difference between Xms and Xmx?
    - -Xms
        - This option is to specify starting heap size for JVM, like Xms2048m which means an initial heap size of JVM is 2GB. When JVM starts, the heap memory will be this big. This is done to prevent resizing during startup and improve the startup time of JVM.
    - -Xmx
        - This option is to specify the maximum heap size of JVM, e.g., Xmx2048m means a maximum heap size of JVM will be 2GB. You will almost always see -Xms and -Xmx together.
        - You must specify sufficient heap space for your Java application to avoid java.lang.OutOfMemoryError: Java Heap space, which comes if there is not enough memory to create new objects or arrays.
    - -XX:+HeapDumpOnOutOfMemoryError
        - This JVM option creates a heap dump when your JVM dies with OutOfMemory Error. There is no overhead involved unless an OOM actually occurs. This flag is a must for production systems as it is often the only way to further analyze the problem.
    - Garbage collector
        - -XX:+UseG1GC
        - -XX:+UseParallelGC
        - -XX:NewRatio
          - Sets the ratio of young generation to old generation in the heap.
    - Ref: <https://www.java67.com/2016/08/10-jvm-options-for-java-production-application.html>

#### Importance of -Xms and -Xmx in JVM

-Xms and -Xmx are crucial for controlling the memory allocation behavior of the Java Virtual Machine (JVM). These options directly impact the performance and stability of Java applications by defining how much memory the JVM can initially allocate and the maximum memory it can use during runtime.

- -Xms (Initial Heap Size):
  - What it does: Sets the initial size of the heap, the area of memory where objects are allocated.
  - Why it's important: When the JVM starts, it needs a certain amount of memory to allocate objects. If -Xms is too small, the JVM will repeatedly increase the heap size as your application runs, leading to frequent garbage collection (GC) cycles, which can degrade performance.
- -Xmx (Maximum Heap Size):
  - What it does: Defines the maximum amount of memory the JVM is allowed to allocate to the heap.
  - Why it's important: If your application needs more memory than the specified -Xmx, the JVM will throw an OutOfMemoryError. Setting a sufficient value ensures that the application has enough memory to run without running into memory allocation issues.

---

When to Adjust -Xms and -Xmx:
- High Traffic Applications: Web servers, databases, and e-commerce sites that handle large numbers of concurrent users often require larger heap sizes to prevent memory bottlenecks and GC pauses.
- Memory-Intensive Applications: Applications that process large amounts of data (e.g., scientific computing, big data analysis, or machine learning) should be configured with sufficient memory to avoid frequent garbage collection.
- Production Environments: Always set appropriate memory limits in production environments based on testing and expected usage. Not setting them properly can cause the application to either consume excessive memory or run into OutOfMemoryError during peak usage.

## NoClassDefFoundError

NoClassDefFoundError is an error that is thrown when the Java Runtime System tries to load the definition of a class, and that class definition is no longer available. The required class definition was present at compile time, but it was missing at runtime.

## When does out of memory happen?

There is a dozen of different reasons why JVM may throw OutOfMemoryError, including

- Java heap space: when trying to allocate an object or an array larger than maximum continuous free block in either of heap generations;
- GC overhead limit exceeded: when the proportion of time JVM spends doing garbage collection becomes too high (see GCTimeLimit, GCHeapFreeLimit);
- PermGen space (before Java 8) or Metaspace (since Java 8): when the amount of class metadata exceeds MaxPermSize or MaxMetaspaceSize;
- Requested array size exceeds VM limit: when trying to allocate an array with length larger than Integer.MAX_VALUE - 2;
- Unable to create new native thread: when reaching the OS limit of user processes (see ulimit -u) or when there is not enough virtual memory to reserve space for thread stack;
- Direct buffer memory: when the size of all direct ByteBuffers exceeds MaxDirectMemorySize or when there is no virtual memory available to satisfy direct buffer allocation;
- When JVM cannot allocate memory for its internal structures, either because run out of available virtual memory or because certain OS limit reached (e.g. maximum number of memory map areas);
- When JNI code failed to allocate some native resource;
- Etc. Not to mention that an application can throw OutOfMemoryError itself at any time just because a developer decides so.
- To find out what is the reason of your particular error, you should at least look at the error message, the stacktrace and GC logs.

Ref: https://stackoverflow.com/questions/33924624/when-does-out-of-memory-happen

### Give memory leak examples.

- A Memory Leak is a situation where there are objects present in the heap that are no longer used, but the garbage collector is unable to remove them from memory, and therefore, they're unnecessarily maintained. Ref: <https://www.tutorialspoint.com/what-is-memory-leak-in-c-cplusplus>
- Example: A static field retains a reference to a large data structure or object, which can cause a memory leak if the data is no longer needed but remains accessible through the static field.

### JVM vs JIT Compiler: What’s the Difference?

- The JVM (Java Virtual Machine) is an abstract machine that runs Java bytecode on any platform without modification.
  - It acts as an interpreter and a runtime environment for Java applications.
  - The JVM is responsible for class loading, bytecode verification, memory management, and garbage collection.
  - It can execute Java programs either by interpreting bytecode line by line or using Just-In-Time (JIT) compilation.
- The JIT compiler is part of the JVM that compiles frequently used bytecode into native machine code at runtime.
  - Unlike an interpreter (which executes bytecode line by line), the JIT compiler converts the entire method or block of code into optimized machine code for faster execution.

---

The JVM can use both an interpreter and a JIT compiler for executing Java bytecode. Let’s compare them:
1. Interpreter
   - Reads and executes Java bytecode line by line.
   - Slower execution because each instruction is translated every time it is encountered.
   - Less memory usage since it doesn’t generate native code.
   - Useful for quick startup times but not efficient for long-running programs.
2. JIT Compiler
   - Converts frequently executed bytecode into optimized native machine code.
   - Provides faster execution through optimization.
   - Uses caching: Once compiled, the native code is reused instead of being re-interpreted.
   - Requires more memory because it stores compiled native code.
   - Optimizes performance for long-running applications.


How JIT Works in JVM?
- The JVM starts by interpreting the bytecode.
- The JIT compiler identifies frequently executed methods (hot spots).
- It compiles those methods into native code and caches them.
- The JVM executes the compiled code directly, improving performance.
- Over time, JIT applies more optimizations, like inlining and loop unrolling.


Without JIT (Using an Interpreter):
```text
Loop 1 → Interpret line 1, line 2, line 3...
Loop 2 → Interpret line 1, line 2, line 3...
Loop 3 → Interpret line 1, line 2, line 3...
```

With JIT (Compiled Code Execution):
```text
Loop 1 → Compile & execute native machine code
Loop 2 → Directly execute native machine code
Loop 3 → Directly execute native machine code
```

Conclusion
- JVM is the engine that runs Java programs. It can either interpret or use the JIT compiler for optimization.
- Interpreter runs Java bytecode line by line (slower but good for short-lived applications).
- JIT Compiler compiles frequently used code into native machine code for faster execution.
- JIT is part of the JVM and provides runtime optimizations to make Java nearly as fast as natively compiled languages like C++.

#### Is an Interpreter Part of Every JVM? Is It Different Across JVM Implementations?

Yes, all JVMs have an interpreter because it allows Java bytecode to be executed immediately without waiting for compilation.

However, the JIT compiler is optional—some JVMs only interpret bytecode, while others use a combination of both interpretation and JIT compilation.

The Java Virtual Machine (JVM) specification defines how a JVM should behave, but different implementations may use different interpreters. Here are some common JVM implementations:

- HotSpot JVM (Oracle/OpenJDK)
  - Uses an interpreter (C++ based) and a JIT compiler.
  - Implements adaptive optimization, meaning frequently used code is JIT-compiled.
  - Default JVM for most Java applications.
- GraalVM
  - Has an advanced JIT compiler (Graal) that replaces HotSpot’s JIT.
  - Can compile Java code ahead of time (AOT) for faster startup.
  - Uses an interpreter but focuses on compilation for better performance.
- IBM J9 (Eclipse OpenJ9)
  - Uses a different interpreter implementation optimized for cloud and memory efficiency.
  - Provides just-in-time (JIT) compilation similar to HotSpot but with different optimizations.
- Dalvik/ART (Android JVMs)
  - Dalvik VM (Older Android versions): Used a register-based interpreter instead of the stack-based JVM.
  - ART (Android Runtime, newer Android versions): Uses Ahead-of-Time (AOT) compilation instead of JIT for better performance on mobile devices.

#### What is HotSpot JIT Compiler?

The HotSpot JIT (Just-In-Time) Compiler is a key component of the HotSpot JVM (used in OpenJDK, Oracle JDK, and AWS Corretto). It dynamically compiles Java bytecode into native machine code at runtime, significantly improving performance compared to interpreting bytecode line by line.

How HotSpot JIT Works
- Interpreter Starts Execution
  - When a Java program runs, the JVM interprets bytecode line by line initially.
- Identifies "Hot Spots" (Frequently Executed Code)
  - JVM monitors method executions and identifies frequently used methods (hot spots).
- Compiles Hot Spots to Native Code
  - The JIT compiler compiles these methods into machine code for faster execution.
- Optimizes and Stores Compiled Code
  - Once compiled, the JVM executes the native code instead of interpreting it, improving performance.

HotSpot JIT applies several optimizations to improve execution speed:
- Method Inlining → Replaces method calls with actual code to avoid function call overhead.
- Loop Unrolling → Reduces loop overhead by executing multiple iterations in one step.
- Dead Code Elimination → Removes unused code for better efficiency.
- Escape Analysis → Allocates objects on the stack instead of the heap when possible, improving memory efficiency.

## JVM profiling

Profiling is the process of examining an application to locate memory or performance-related issues. 
When profiling a Java application, you can monitor the Java Virtual Machine (JVM) and obtain data about application performance, including method timing, object allocation and garbage collection.
Not every JDK comes with a full-featured profiler, but JDK includes several tools that can be used for basic profiling and performance monitoring. Java Flight Recorder (JFR) is available in Oracle JDK (since JDK 7) but was made free to use in JDK 11.

Reference: https://docs.oracle.com/cd/E40938_01/doc.74/e40142/test_profile_japps.htm

### Tools Included in the JDK for Profiling

Java Flight Recorder (JFR):
- Java Flight Recorder is a low-overhead profiling tool that comes bundled with the Oracle JDK starting from JDK 7 (but with additional features in newer versions).
- JFR records detailed runtime information such as CPU usage, memory consumption, garbage collection, and thread activity.
- JFR is available in Oracle JDK (since JDK 7) but was made free to use in JDK 11.
- You can use the jfr command-line tool to start and manage the recording.

JConsole:
- JConsole is a simple and basic monitoring tool that comes with the JDK. It allows you to view JVM metrics such as memory usage, thread activity, and garbage collection.
- It is suitable for monitoring running Java applications and provides a graphical interface to view metrics, but it's not as feature-rich as dedicated profilers like YourKit or JProfiler.

JVisualVM:
- JVisualVM is another monitoring tool included in the JDK (starting from JDK 6). It offers a visual interface for monitoring and troubleshooting Java applications.
- It can provide detailed information about memory usage, garbage collection, threads, CPU usage, and even allows you to take heap dumps or thread dumps.
- It also supports remote profiling and can integrate with JMX.

JStack:
- JStack is a utility that helps you analyze the thread dump of a Java process.
- It's useful for analyzing thread states, thread contention, and identifying deadlocks in your application.

### What is JMX?

- Java Management Extensions (JMX) is a Java technology that provides a way to manage and monitor applications, system objects, and devices. JMX is part of the Java Standard Edition and allows for dynamic management of resources, including the ability to interact with running applications and their components.
- JMX is built into the Java Virtual Machine (JVM) and enables developers to remotely monitor Java applications, collect performance data, and modify application behavior dynamically.
- JMX gives powerful remote access to your Java application. You can:
  - Monitor JVM health (memory, GC, threads)
  - Change configurations dynamically (logging, DB connections)
  - Execute actions (reload configs, trigger GC, enable features)
  - Debug issues remotely (heap dumps, profiling, thread analysis)
- Key Components of JMX
    - MBeans (Managed Beans): The core component in JMX. MBeans are Java objects that represent manageable resources. They expose attributes and operations that can be monitored and controlled.
    - MBeanServer: The central interface of JMX that acts as a registry for MBeans. It is used to register and manage MBeans.
    - Connectors and Adapters: Allow external management tools to connect to the MBeanServer. Connectors provide a remote interface, while adapters provide a web-based or other local interfaces.
- Example
    - Spring Boot Actuator provides JMX support out of the box, making it easy to expose and manage application metrics and beans.
    - Enable JMX and configure it in application.properties or application.yml.
    - Custom MBeans: Create custom MBeans using @ManagedResource, @ManagedAttribute, and @ManagedOperation annotations.

## Memory leak

There is no memory leak if the line is always the same level after running GC.

![img](images/jvm/no-memory-leak.png)

If objects are not removed from the memory, it means there is memory leak. You should notice that every GC the heap size is growing and keeping the same level as previously.

![img](images/jvm/memory-leak.png)

Reference: https://www.youtube.com/watch?v=Owp_BhlX7Pg&t=6984&ab_channel=WarsawJUG

### What JVM flags should be used?

Ref: <https://www.baeldung.com/jvm-tuning-flags>

Enable this flag on production: -XX:+HeapDumpOnOutOfMemoryError. It will prepare heap dump in case of critical error.

## Heap vs Swap

Heap memory is what the JVM uses, swap is what OS uses to push things not used often onto disk and save memory.

Red: https://stackoverflow.com/questions/47041855/what-is-the-difference-between-heap-and-swap-memory

## AOT Compilation in JVM

- Introduced in: Java 9
- Tool Used: jaotc (Java AOT Compiler)
- AOT Backend: Uses GraalVM for AOT compilation

How It Works
- Java bytecode is compiled into native machine code before execution.
- The compiled native code is loaded directly by the JVM at runtime, reducing the need for JIT compilation.
- AOT-compiled code is stored in shared libraries (.so, .dll, .dylib).

## Does the JDK provider make a difference?

- Yes, each provider supports its JDK version with different time range.
- Also, some of them include extra libraries e.g. some 3rd party vendors do add extra features. For example, the BellSoft Liberica JDK includes JavaFX, which was removed from OpenJDK in Java 11. Another example, Oracle JDK 8 contains JavaFx but Redhat JDK 8 does not.
- When choosing a JDK provider, consider the following factors:
    - Project Requirements: Ensure the provider meets your project's performance, support, and licensing needs.
    - Licensing: Check the licensing terms to ensure they align with your project's requirements.
    - Support Needs: Decide whether you need commercial support or are comfortable with community support.
    - Platform Compatibility: Verify that the provider supports the platforms you need.
- References
    - <https://tomgregory.com/which-jdk-version-and-vendor/>
    - <https://dzone.com/articles/an-overview-on-jdk-vendors>
