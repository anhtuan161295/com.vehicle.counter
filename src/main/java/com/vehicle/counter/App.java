package com.vehicle.counter;


import com.vehicle.counter.configs.Handler;
import com.vehicle.counter.process.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App {
  private static final Logger log = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    log.info("Hello World");
    final ExecutorService executorService = Executors.newFixedThreadPool(1);
    final Listener listener = new Listener();

    try {
      Handler.setShutdownHook();
      executorService.submit(listener);

    } catch (Exception e) {
      log.error("Error in main method of App : ", e);
    }
  }


}
