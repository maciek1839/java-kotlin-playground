package com.showmeyourcode.playground.java.overview.concurrency;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * A monitor is mechanism to control concurrent access to an object.
 * A monitor is an entity that possesses both a lock and a wait set.
 * <br>
 * In Java, any Object can serve as a monitor.
 * <br>
 * It's not a special object.
 * It's synchronization mechanism placed at class hierarchy root: java.lang.Object.
 * <br>
 * There are also wait and notify methods that will also use object's monitor to communication among different threads.
 * <br>
 *
 * Reference: <a href="https://stackoverflow.com/questions/3362303/whats-a-monitor-in-java">What's a monitor in Java?</a>
 */
@Slf4j
public class Monitor {

    synchronized void criticalSection(){
         log.info("Woooho synchronzied method!");
         // access to a shared resource here
    }

    public static void main(String[] args) {
        var m = new Monitor();
        m.criticalSection ();
    }
}
