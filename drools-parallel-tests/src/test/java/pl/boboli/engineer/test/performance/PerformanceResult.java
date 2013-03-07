package pl.boboli.engineer.test.performance;

/**
 * Created with IntelliJ IDEA.
 * User: jboboli
 * Date: 1/25/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class PerformanceResult {
    long parallelRunTime;
    long sequentialRunTime;

    public PerformanceResult() {
    }

    public long getParallelRunTime() {
        return parallelRunTime;
    }

    public void setParallelRunTime(long parallelRunTime) {
        this.parallelRunTime = parallelRunTime;
    }

    public long getSequentialRunTime() {
        return sequentialRunTime;
    }

    public void setSequentialRunTime(long sequentialRunTime) {
        this.sequentialRunTime = sequentialRunTime;
    }
}
