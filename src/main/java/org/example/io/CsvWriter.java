package org.example.io;

import org.example.daa.metrics.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Простейший CSV writer для результатов.
 */


public class CsvWriter implements AutoCloseable {
    private final PrintWriter out;
    private boolean header = false;

    public CsvWriter(String path) throws IOException {
        out = new PrintWriter(new FileWriter(path, false));
    }

    public void writeHeader() {
        if (!header) {
            out.println("algorithm,n,trial,time_ns,maxDepth,comparisons,arrayAccesses,allocations");
            header = true;
        }
    }

    public void writeRow(String algorithm, int n, int trial, long timeNs, Metrics m) {
        writeHeader();
        out.printf("%s,%d,%d,%d,%d,%d,%d,%d%n",
                algorithm, n, trial, timeNs,
                m.getMaxDepth(), m.getComparisons(),
                m.getArrayAccesses(), m.getAllocations());
    }

    @Override
    public void close() {
        out.flush();
        out.close();
    }
}

