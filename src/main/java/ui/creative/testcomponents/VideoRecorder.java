package ui.creative.testcomponents;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorder  {
	 
	public static ScreenRecorder screenRecorder ;
	
	public static void startRecording(String s) throws IOException, AWTException, InterruptedException {
		// Set the output file for the screen recording
        File file = new File("AutomationReports\\VideoRecordings\\"+s+".mov");

        // Set up the screen recorder
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        Rectangle captureSize = new Rectangle(0, 0, width, height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        // Create a new instance of ScreenRecorder
        screenRecorder = new ScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_QUICKTIME),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_QUICKTIME_ANIMATION,
                        CompressorNameKey, ENCODING_QUICKTIME_ANIMATION, DepthKey, 24, FrameRateKey, Rational.valueOf(30)),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null, file);

        // Start recording the screen
        screenRecorder.start();
        Thread.sleep(2000);
	}
	
	public static void stopRecording() throws IOException, InterruptedException {
		Thread.sleep(2000);
		screenRecorder.stop();
	}
	
	
}
