
## Implemented Algorithms

### 1. MergeSort (Master Case 2)
- **Idea:** Linear merge, reusable buffer, small-`n` cutoff (insertion sort).  
- **Recurrence: T(n) = 2T(n/2) + Θ(n)**  
By Master Theorem (Case 2): **Θ(n log n)**.  
- **Notes:** Buffer reuse reduces allocations; cutoff improves cache efficiency.  

### 2. QuickSort (Randomised, bounded stack)
- **Idea:** Randomised pivot, recurse on smaller partition, iterate on larger (stack ≈ O(log n)).  
- **Recurrence (expected): T(n) = T(αn) + T((1-α)n) + Θ(n)**  
Balanced split on average → **Θ(n log n)**.  
- **Notes:** Depth bounded by ~2·⌊log₂n⌋ + O(1).  

### 3. Deterministic Select (Median-of-Medians, O(n))
- **Idea:** Group by 5, recursive median pivot, in-place partition; recurse only on needed side.  
- **Recurrence:T(n) ≤ T(n/5) + T(7n/10) + Θ(n)**  
Solves to **Θ(n)** (Akra–Bazzi or substitution method).  
- **Notes:** Always linear time; practical overhead vs randomized.  

### 4. Closest Pair of Points (2D, O(n log n))
- **Idea:** Sort by `x`, recursive split, “strip” check by `y` order (7–8 neighbours).  
- **Recurrence: T(n) = 2T(n/2) + Θ(n)**  
→ **Θ(n log n)** (Master Case 2).  
- **Notes:** Validated against O(n²) brute force for small `n`.  

---

## Architecture Notes
- **Metrics:** Counters for comparisons, allocations, and recursion depth.  
- **Depth Control:** QuickSort recurses on smaller partition; deterministic select recurses only into one side.  
- **Efficiency:** Buffer reuse in MergeSort; cutoff to insertion sort for small `n`.  
- **CSV Writer:** Results logged in `results.csv` for plotting.  

---

## Measurements and Plots
**Collected metrics:**
- Running time vs input size (`n`).  
- Recursion depth vs input size.  
- Comparisons & allocations.  

**Plots (to insert as images):**
1. Time vs `n` for all algorithms.  
2. Depth vs `n` (QuickSort bounded; MergeSort log₂n).  
3. Constant-factor effects discussion (cache locality, JVM GC pauses).  

---

## Summary of Theory vs Measurements
- MergeSort and Closest Pair matched theoretical **Θ(n log n)** growth; constants affected by buffer/caching.  
- QuickSort observed depth ≲ 2·log₂n; randomized pivot avoided worst cases.  
- Deterministic Select consistently **Θ(n)** but slower than QuickSort for moderate `n` due to overhead.  
- Minor mismatches due to constant factors (Java object allocations, GC).  

---

## Submission
This project is submitted as a GitHub repository link:  
https://github.com/NurdauletAmangos/ADDD.git
