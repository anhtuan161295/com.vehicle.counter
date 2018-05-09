package com.vehicle.counter.process;

import com.vehicle.counter.configs.Constants;
import com.vehicle.counter.configs.Handler;
import com.vehicle.counter.models.Resource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Listener implements Runnable {
  private static final Logger log = LoggerFactory.getLogger(Listener.class);
  private boolean isAllowed = true;
  private int count = 0;
  private int max = 10;

  private boolean isRunning = true;


  @Override
  public void run() {
    try {
      while (isRunning) {
        if (isAllowed && count >= max) {
//          Handler.createVideoFile();
          isAllowed = false;
          count = 0;
          break;
        }

        if (isAllowed && !createVideoData()) {
          continue;
        }


        TimeUnit.MILLISECONDS.sleep(500);
      }


    } catch (Exception e) {
      log.error("Error in run method of Listener : ", e);
    }
  }

  private boolean createVideoData() throws IOException {
    Resource resource = Handler.getResourceFromResponse();
    if (Objects.isNull(resource)) {
      return false;
    }

    if (StringUtils.contains(resource.getFileName(), Constants.TS_EXTENSION)) {
      File videoFolder = Handler.createVideoFolder();
      File videoDataFolder = Handler.createVideoDataFolder();
      File video = new File(videoDataFolder, resource.getFileName());
      if (!video.exists()) {
        ByteArrayInputStream bais = new ByteArrayInputStream(resource.getBytes());
        FileUtils.copyToFile(bais, video);
        count++;
      }
    }

    return true;
  }


  public void close() {
    isRunning = false;
  }
}
