package com.vehicle.counter.configs;

import java.io.File;

public class Handler {

	public static void createVideoFolder() {
		File videoFolder = new File(Constants.VIDEO_FOLDER_PATH);

		if (!videoFolder.exists()) {
			videoFolder.mkdir();
		}
	}

	private Handler() {
		// prevent instantiation
	}

}
