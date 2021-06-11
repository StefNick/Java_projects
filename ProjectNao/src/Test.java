import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;
import com.aldebaran.qi.helper.proxies.ALAutonomousMoves;
import com.aldebaran.qi.helper.proxies.ALTracker;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALSonar;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;



public class Test {
	
	public static void main(String [] args) throws Exception {
		
		String robotUrl = "tcp://192.168.88.116:9559";
		
		Application application = new Application(args, robotUrl);
		application.start();
		
		ALMemory memory = new ALMemory(application.session());
		ALSonar sonar = new ALSonar(application.session());
		ALMotion motion = new ALMotion(application.session());
		ALRobotPosture posture = new ALRobotPosture(application.session());
		ALSpeechRecognition speech = new ALSpeechRecognition(application.session());
	    ALTextToSpeech tts = new ALTextToSpeech(application.session());
	    
	    System.out.println(memory.getData("Device/DeviceList/ChestBoard/BodyId"));
	    System.out.println(tts.getAvailableLanguages());
	    tts.setLanguage("French");
	    System.out.println(tts.getLanguage());
	    tts.say("Bonjour bonjour mes amis. Je suis content");
	}
}