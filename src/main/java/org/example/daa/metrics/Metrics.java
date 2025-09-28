package org.example.daa.metrics;

public class Metrics {
    private long comparisons = 0;
    private long arrayAccesses = 0;
    private long allocations = 0;

    private int depth = 0;
    private int maxDepth = 0;

    public void reset() {
        comparisons = 0;
        arrayAccesses = 0;
        allocations = 0;
        depth = 0;
        maxDepth = 0;
    }

    public void incComparisons(long d) { comparisons += d; }
    public void incArrayAccesses(long d) { arrayAccesses += d; }
    public void incAllocations(long d) { allocations += d; }

    public void enterRecursion() {
        depth++;
        if (depth > maxDepth) maxDepth = depth;
    }

    public void exitRecursion() {
        if (depth > 0) depth--;
    }

    public long getComparisons() { return comparisons; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getAllocations() { return allocations; }
    public int getMaxDepth() { return maxDepth; }

    @Override
    public String toString() {
        return "comp=" + comparisons + " access=" + arrayAccesses +
                " alloc=" + allocations + " depth=" + maxDepth;
    }
}