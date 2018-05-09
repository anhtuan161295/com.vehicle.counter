package com.vehicle.counter.configs;

import com.vehicle.counter.models.Resource;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Handler {
  private static final Logger log = LoggerFactory.getLogger(Handler.class);

  public static void createVideoFile() {
    try {
      String videoDataPath = FilenameUtils.separatorsToSystem(StringUtils.join(Constants.VIDEO_DATA_FOLDER_PATH, Constants.SLASH));
      String concatCmdTemplate = "cat {}* >  {}";
      String concatCmd = MessageFormatter.format(concatCmdTemplate, videoDataPath, Constants.TS_FILE_PATH).getMessage();

      String convertCmdTempate = "ffmpeg -i {} -acodec copy -vcodec copy {}";
      String convertCmd = MessageFormatter.format(convertCmdTempate, Constants.TS_FILE_PATH, Constants.VIDEO_FILE_PATH).getMessage();

      Process powerShellProcess = Runtime.getRuntime().exec("powershell.exe "+ concatCmd);
      powerShellProcess.getOutputStream().close();
      powerShellProcess = Runtime.getRuntime().exec("powershell.exe "+ convertCmd);
      powerShellProcess.getOutputStream().close();


      log.info(concatCmd);
      log.info(convertCmd);

      log.info("Run powershell to concatenate ts files");

    }catch (Exception e){
      log.error("Error in createVideoFile method of Handler : ", e);
    }
  }


  public static Resource getResourceFromResponse() {
    Resource resource = null;
    try {
      URL infoUrl = new URL(Constants.INFO_URL);
      HttpURLConnection infoConnection = (HttpURLConnection) infoUrl.openConnection();
      infoConnection.setRequestMethod("GET");

      InputStream infoIs = infoConnection.getInputStream();
      String data = IOUtils.toString(infoIs, StandardCharsets.UTF_8.displayName());

      if (StringUtils.isNotBlank(data)) {
        String[] lines = StringUtils.split(data, StringUtils.LF);
        String fileName = lines[lines.length - 1];

        String videoPath = StringUtils.joinWith(Constants.SLASH, Constants.VIDEO_FOLDER_PATH, fileName);
        File video = new File(FilenameUtils.separatorsToSystem(videoPath));

        if (video.exists()) {
          return null;
        }

        URL dataUrl = new URL(StringUtils.joinWith(Constants.SLASH, Constants.BASE_URL, fileName));
        HttpURLConnection dataConnection = (HttpURLConnection) dataUrl.openConnection();
        dataConnection.setRequestMethod("GET");

        byte[] bytes = IOUtils.toByteArray(dataConnection.getInputStream());

        resource = new Resource();
        resource.setFileName(fileName);
        resource.setBytes(bytes);
      }
    } catch (Exception e) {
      log.error("Error in getResourceFromResponse method of Handler : ", e);
    }
    return resource;
  }

  public static void setShutdownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> System.exit(0)));
  }


  public static File createVideoFolder() {
    return createFolder(Constants.VIDEO_FOLDER_PATH);
  }

  public static File createVideoDataFolder() {
    return createFolder(Constants.VIDEO_DATA_FOLDER_PATH);
  }

  public static File createFolder(String path) {
    File folder = new File(path);

    if (!folder.exists()) {
      folder.mkdirs();
    }
    return folder;
  }

  private Handler() {
    // prevent instantiation
  }

}
