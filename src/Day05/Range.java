package Day05;

public class Range implements Comparable<Range> {
    private long min;
    private long max;

    public Range(long min, long max) {
        this.min = min;
        this.max = max;
    }

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public boolean contains(long val) {
        return val >= min && val <= max;
    }

    public long count() {
        return this.max - this.min + 1;
    }

    public Range merge(Range mergeWith) {
        if (mergeWith.getMin() > this.max || mergeWith.getMax() < this.min) {
            return null;
        }
        long newMin = this.min < mergeWith.getMin() ? this.min : mergeWith.getMin();
        long newMax = this.max > mergeWith.getMax() ? this.max : mergeWith.getMax();
        return new Range(newMin, newMax);
    }

    @Override
    public String toString() {
        return "Range [min=" + min + ", max=" + max + "]";
    }

    @Override
    public int compareTo(Range b) {
        return Long.compare(this.min, b.getMin()); // Ascending order min
    }
}
