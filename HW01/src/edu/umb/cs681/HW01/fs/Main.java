package edu.umb.cs681.HW01.fs;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.IntSummaryStatistics;
import java.util.Map;

import static edu.umb.cs681.HW01.fs.FileStructure.createFS;

public class Main {
    public static void main(String[] args) {
        FileSystem fs = createFS();
        LocalDateTime threshold = LocalDateTime.of(2024, Month.FEBRUARY, 1, 05, 10);
        String extension = ".java";
        int count = fs.countFilesAfter(threshold, extension);
        System.out.println("Number of files created after " + threshold + " with extension " + extension + ": " + count);

        Map<String, IntSummaryStatistics> groupedFileStats = fs.groupFilesByExtensionWithStats();
        for (Map.Entry<String, IntSummaryStatistics> entry : groupedFileStats.entrySet()) {
            extension = entry.getKey();
            IntSummaryStatistics stats = entry.getValue();
            System.out.println("Extension: " + extension);
            System.out.println(" - Count: " + stats.getCount());
            System.out.println(" - Total size: " + stats.getSum());
            System.out.println(" - Average size: " + stats.getAverage());
            System.out.println(" - Min size: " + stats.getMin());
            System.out.println(" - Max size: " + stats.getMax());
        }
    }
}