package com.showmeyourcode.playground.java.overview.files;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

@Slf4j
public class ReadingFilesOverview {

    /**
     * For the best performance when reading and processing text files in Java using JDK solutions:
     *
     * - Use BufferedReader if you need simple and efficient reading with minimal memory usage.
     * - Use Files.lines if you prefer a modern approach with the flexibility of streams and potential for parallel processing.
     */
    public static void main(String[] args) {
        blockingIO();

        newIO();
    }

    private static void blockingIO() {
        fileReader();
        bufferedReader();
        inputStreamReaderAndFileInputStream();
        scanner();
    }

    /**
     * Non-blocking I/O (Java)
     *
     * java.nio (NIO stands for New Input/Output) is a collection of Java programming language APIs that offer features for intensive I/O operations.
     * It was introduced with the J2SE 1.4 release of Java by Sun Microsystems to complement an existing standard I/O.
     * NIO was developed under the Java Community Process as JSR 51.
     * An extension to NIO that offers a new file system API, called NIO.2, was released with Java SE 7 ("Dolphin").
     *
     * https://en.wikipedia.org/wiki/Non-blocking_I/O_(Java)
     *
     * It provides:
     * Buffer – to read chunks of data at a time
     * CharsetDecoder – for mapping raw bytes to/from readable characters
     * Channel – for communicating with the outside world
     * Selector – to enable multiplexing on a SelectableChannel and provide access to any Channels that are ready for I/O
     * non-blocking mode – to read whatever is ready
     *
     * https://www.baeldung.com/java-io-vs-nio
     *
     *
     *
     * In contrast, the java.nio libraries allow for non-blocking communication using Buffers and Channels and can provide direct memory access for faster performance.
     * NIO is used not because it's faster but because it has better scalability especially there are amounts of clients.
     * IO (Blocking IO/Stream IO) is usually one thread per connection to get better response to the clients.
     *
     * https://stackoverflow.com/questions/7611152/nio-performance-improvement-compared-to-traditional-io-in-java
     */
    private static void newIO() {
        filesNIO();
        streamAPI();
    }

    /**
     * FileReader is a simple and straightforward way to read text files.
     * However, it doesn't provide buffering, which means it reads characters one at a time,
     * making it less efficient for larger files.
     * <p>
     * FileReader uses very little memory compared to methods like Files.readAllBytes() or Files.readAllLines(), because it reads the file one character at a time.
     * <p>
     * So, for situations, where we need to use custom values for the character set, buffer size, or input stream, we must use InputStreamReader.
     * Moreover, we all know that I/O cycles are expensive and can introduce latency to our application.
     * So, it’s in our best interest to minimize the number of I/O operations by wrapping a BufferedReader around our FileReader object.
     * <p>
     * Reference: https://www.baeldung.com/java-filereader
     */
    @SneakyThrows
    private static void fileReader() {
        var builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(getSampleFile())) {
            int nextChar;
            while ((nextChar = fileReader.read()) != -1) {
                builder.append((char) nextChar);
            }
        }
        log.info("File reader: {}", builder);
    }

    /**
     * BufferedReader is more efficient than FileReader because it uses buffering.
     * This means it reads larger chunks of data at once (line by line), reducing the number of I/O operations.
     * This makes it generally faster than using FileReader alone.
     */
    @SneakyThrows
    private static void bufferedReader() {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(getSampleFile()))){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.info("BufferedReader line: {}", line);
            }
        }
    }

    /**
     * Both allows you to read data from File, but FileInputStream is used to read binary data,
     * while FileReader is used to read character data.
     *
     * The InputStreamReader class is a bridge from byte streams to character streams,
     * and FileInputStream obtains input bytes from a file.
     */
    @SneakyThrows
    private static void inputStreamReaderAndFileInputStream() {
        var inputStreamReader = new InputStreamReader(new FileInputStream(getSampleFile()), StandardCharsets.UTF_8);
        try(var bufferedReader = new BufferedReader(inputStreamReader)){
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.info("BufferedReader(InputStreamReader) line: {}", line);
            }
        }
    }

    /**
     * Scanner is convenient for parsing primitive types and strings using regular expressions.
     * However, it is generally slower than BufferedReader for simple text file reading because of the overhead of parsing and regular expression matching.
     */
    @SneakyThrows
    private static void scanner() {
        try(Scanner scanner = new Scanner(getSampleFile())){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                log.info("Scanner line: {}", line);
            }
        }
    }

    /**
     * The Files class provides methods to read files into memory using NIO (New I/O).
     * Files.readAllLines loads the entire file into memory as a List<String>, where each element is a line from the file.
     * It internally uses a BufferedReader to read the file line by line and accumulates the lines into a List<String>.
     */
    @SneakyThrows
    private static void filesNIO() {
        // Reading all lines into a list
        List<String> lines = Files.readAllLines(getSampleFilePath(), StandardCharsets.UTF_8);
        String content = new String(Files.readAllBytes(getSampleFilePath()), StandardCharsets.UTF_8);
        log.info("Files.readAllLines: {} Files.readAllBytes: {}", lines, content);
    }


    /**
     * Stream API (Java 8 and later)
     *
     * Using Files.lines with the Stream API is a modern and efficient way to process large files, allowing for lazy evaluation and parallel processing.
     * This can be very performant, especially with large files, as it doesn't require loading the entire file into memory.
     */
    @SneakyThrows
    private static void streamAPI() {
        try (Stream<String> lines = Files.lines(getSampleFilePath(), StandardCharsets.UTF_8)) {
            log.info("Files.lines: {}", lines.toList());
        }

        // Java 11 Files API Enhancements
        // Java 11 introduced methods like Files.readString and Files.writeString,
        // which can be very efficient and convenient for handling file I/O, particularly for smaller files.
        // For large files, Files.lines is still preferable due to its streaming nature.

        // Reading file content as a string:
        log.info("Files.lines: {}", Files.readString(getSampleFilePath(), StandardCharsets.UTF_8));

        // Writing content to a file:
        Files.writeString(getSampleFilePath(), "Hello, World!", StandardCharsets.UTF_8);
    }

    @NotNull
    private static Path getSampleFilePath() throws URISyntaxException {
        return getSampleFile().toPath();
    }

    @NotNull
    private static File getSampleFile() throws URISyntaxException {
        return new File(Objects.requireNonNull(ReadingFilesOverview.class.getClassLoader().getResource("example.txt")).toURI());
    }
}
