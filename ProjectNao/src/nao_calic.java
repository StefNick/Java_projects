import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.helper.proxies.ALSonar;
import com.aldebaran.qi.helper.proxies.ALSpeechRecognition;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;
import com.aldebaran.qi.helper.proxies.ALAutonomousMoves;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTracker;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;


public class nao_calic {
	public static void main(String[] args) throws Exception {
		String robotUrl = "tcp://192.168.88.86:9559";

		Application application = new Application(args, robotUrl);
		application.start();

		ALMotion motion = new ALMotion(application.session());
		ALAnimatedSpeech tts = new ALAnimatedSpeech(application.session());
		ALRobotPosture posture = new ALRobotPosture(application.session());
		ALMemory memory = new ALMemory(application.session());
		ALSonar sonar = new ALSonar(application.session());
		ALTextToSpeech tts2 = new ALTextToSpeech(application.session());
		String read;
		boolean start = true;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		motion.setFallManagerEnabled(true);
		motion.wakeUp();
		
		while(start) {
			read = br.readLine();
			switch(read) {
			
			case "1":
				tts.say("^start(animations/Stand/Gestures/Hey_3) Hello I am Frank! ^wait(animations/Stand/Gestures/Hey_3) ");
				break;
				
			case "2":
				tts.say("^start(animations/Stand/Gestures/Explain_1) What is your name? ^wait(animations/Stand/Gestures/Explain_1)");
				break;
				
			case "3":
				tts.say("^start(animations/Stand/Gestures/Explain_1) Nice to meet you! ^wait(animations/Stand/Gestures/Explain_1)");
				break;
				
			case "4":
				tts.say("^start(animations/Stand/Gestures/Explain_1) I am fine,thank you! And how are you? ^wait(animations/Stand/Gestures/Explain_1)");
				break;
				
			case "5":
				tts.say("^start(animations/Stand/Gestures/Explain_1) I am glad to hear that! ^wait(animations/Stand/Gestures/Explain_1)");
				break;
				
			case "6":
				tts.say("How old are you?");
				break;
				
			case "7":
				tts.say("^start(animations/Stand/Gestures/Explain_1) I am really old and rusty! ^wait(animations/Stand/Gestures/Explain_1)");
				break;
				
			case "8":
				tts.say("^start(animations/Stand/Gestures/Explain_1) What is your favourite color? Mine, is blue! ^wait(animations/Stand/Gestures/Explain_1)");
				break;
				
			case "9":
				tts.say("What grade are you in?");
				break;
				
			case "10":
				tts.say("^start(animations/Stand/Gestures/Explain_1) I don't remember. All I know is I waked up at the Technical University of Moldova ^wait(animations/Stand/Gestures/Explain_1)");
				break;
				
			case "11":
				tts.say("^start(animations/Stand/Gestures/Hey_1) Goodbye kids. It was nice to see you! ^wait(animations/Stand/Gestures/Hey_1) ");
				break;
				
			case "12":
				start = false;
				break;
			}
		}
	}

}
