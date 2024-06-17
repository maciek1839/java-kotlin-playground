package com.showmeyourcode.playground.java.overview.concurrency;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch is a synchronization aid that allows one or more threads to wait for a set of operations to complete.
 * See <a href="https://bazlur.ca/2023/10/04/what-is-countdownlatch-and-how-to-use-it/">What is CountDownLatch?</a>
 */
@Slf4j
public class CountDownLatchDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        var server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        server.createContext("/hello", new GreetingsHandler());

        CountDownLatch latch = new CountDownLatch(4);

        Thread.startVirtualThread(new Task("Load Config", latch));
        Thread.startVirtualThread(new Task("Init DB Connection", latch));
        Thread.startVirtualThread(new Task("Init Cache", latch));
        Thread.startVirtualThread(new Task("Start Embedded Server", latch));

        latch.await();
        log.info("All initializations complete. Application is starting...");

        // Uncomment if you want to run a server
        // server.start();
    }
}

@Slf4j
record Task(String name, CountDownLatch latch) implements Runnable {
    @Override
    public void run() {
        doHeavyLifting();
        log.info("{} has finished.", name);
        latch.countDown();
    }
    private static void doHeavyLifting() {
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            log.error("Cannot do heavy lifting. ", e);
            Thread.currentThread().interrupt();
        }
    }
}

class GreetingsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = "Hello world!";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
