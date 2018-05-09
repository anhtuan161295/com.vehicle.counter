package com.vehicle.counter;


import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.vehicle.counter.configs.Constants;
import com.vehicle.counter.process.Listener;

/**
 * Hello world!
 */
public class App {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		final ExecutorService executorService = Executors.newFixedThreadPool(1);
		final Listener listener = new Listener();



		try {


			executorService.submit(listener);

			System.out.println(Constants.VIDEO_FOLDER_PATH);


		} catch (Exception e) {

		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				listener.close();
				executorService.shutdown();
				System.exit(0);
			}
		}));

	}

	public static String getFileNameFromResponse() {
		String fileName = StringUtils.EMPTY;
		try {
			URL url = new URL(Constants.URL);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			String data = StringUtils.EMPTY;
			try (BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream())) {
				data = IOUtils.toString(bis, StandardCharsets.UTF_8.displayName());
			}

			if (StringUtils.isNotBlank(data)) {
				String[] lines = StringUtils.split(data, StringUtils.LF);
				fileName = lines[lines.length - 1];
			}

		} catch (Exception e) {

		}

		return fileName;
	}


}
