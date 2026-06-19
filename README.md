# Classic Synchronization Problems

This repository contains clean, robust implementations of classic multi-threading and process synchronization problems in computer science. These problems demonstrate core concurrency concepts, concurrency flaws (like race conditions, deadlocks, and starvation), and how to solve them safely using synchronization primitives like **Semaphores** and **Mutexes**.

## 🚀 Problems Covered

### 1. The Producer-Consumer (Bounded-Buffer) Problem
* **The Challenge:** Multiple producer and consumer threads sharing a fixed-size buffer. Producers must stop when the buffer is full, consumers must stop when it's empty, and data corruption must be prevented.
* **The Solution:** Implemented using a binary semaphore (`mutex`) for mutual exclusion and two counting semaphores (`emptySlots`, `fullSlots`) to track buffer states without CPU busy-waiting.

### 2. The Readers-Writers Problem
* **The Challenge:** Managing database access where multiple readers can read concurrently, but writers require exclusive access. 
* **The Solution:** Implemented using a binary semaphore (`writer`) for exclusive access and a secondary binary semaphore (`mutex`) to safely protect a shared reader counter (`rc`).

* ### 3. The Dining Philosophers Problem
* **The Challenge:** Five philosophers sit around a table alternating between thinking and eating. They share five chopsticks, and each needs two adjacent chopsticks to eat. If every philosopher grabs their left chopstick at the same time, the system permanently freezes.
* **The Solution:** Implemented using an array of binary semaphores (`fork`). Deadlock is prevented by breaking the circular wait symmetry—forcing the final philosopher (`id == 4`) to pick up their right chopstick first, ensuring at least one thread can always eat and release its resources.

---

## 🛠️ Tech Stack & Prerequisites

* **Language Used:** Java 21 (works for Java 7 and higher)
* **Core Libraries:** `java.util.concurrent.Semaphore`, `java.lang.Thread`

---

## 💻 How to Run the Projects

1. **Clone the Repository:**
   ```bash
   git clone [https://github.com/prajith-11/Classic-Synchronisation-Problems.git](https://github.com/prajith-11/Classic-Synchronisation-Problems.git)
   ```

2. **Compile the Code:**
    ```bash
    javac <filename>.java
    ```
3. **Run the Simulation:**
   ```bash
   java <filename>
   ```
