package com.vehicle.counter.configs;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class Constants {
  public static final String SLASH = "/";
  public static final String BACKSLASH = "\\";

  public static final String BASE_URL = "http://4co2.vp9.tv/chn/DNG67";
  public static final String INFO_FILE_NAME = "v.m3u8";
  public static final String INFO_URL = StringUtils.joinWith(SLASH, BASE_URL, INFO_FILE_NAME);

  public static final String PROJECT_FOLDER_PATH = System.getProperty("user.dir");
  public static final String VIDEO = "video";
  public static final String VIDEO_FOLDER_PATH = FilenameUtils.separatorsToSystem(StringUtils.joinWith(SLASH, PROJECT_FOLDER_PATH, VIDEO));

  public static final String VIDEO_DATA = "data";
  public static final String VIDEO_DATA_FOLDER_PATH = FilenameUtils.separatorsToSystem(StringUtils.joinWith(SLASH, VIDEO_FOLDER_PATH, VIDEO_DATA));

  public static final String TS_EXTENSION = ".ts";

  public static final String TS_FILE_NAME = "file_video.ts";
  public static final String TS_FILE_PATH = FilenameUtils.separatorsToSystem(StringUtils.joinWith(SLASH, VIDEO_FOLDER_PATH, TS_FILE_NAME));

  public static final String VIDEO_FILE_NAME = "file_video.mp4";
  public static final String VIDEO_FILE_PATH = FilenameUtils.separatorsToSystem(StringUtils.joinWith(SLASH, VIDEO_FOLDER_PATH, VIDEO_FILE_NAME));


  private Constants() {
    // prevent instantiation
  }

}
