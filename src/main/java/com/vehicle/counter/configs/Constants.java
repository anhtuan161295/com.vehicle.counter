package com.vehicle.counter.configs;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

public class Constants {

	public static final String URL = "http://4co2.vp9.tv/chn/DNG67/v.m3u8";

	public static final String SLASH = File.separator;

	public static final String PROJECT_FOLDER_PATH = System.getProperty("user.dir");
	public static final String VIDEO = "video";
	public static final String VIDEO_FOLDER_PATH = StringUtils.joinWith(SLASH, PROJECT_FOLDER_PATH, VIDEO);

	public static final String FOLDER_NAME = "data";


	private Constants() {
		// prevent instantiation
	}

}
