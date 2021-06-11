import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.BufferedInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Random;


public class Nao_move_yourself {
	public static void main(String [] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String robotUrl = "tcp://192.168.88.248:9559";
		
		Application application = new Application(args, robotUrl);
		application.start();
		
		Random rand = new Random();
		ALMemory memory = new ALMemory(application.session());
		ALAnimatedSpeech as = new ALAnimatedSpeech(application.session());
		ALMotion motion = new ALMotion(application.session());
		ALTextToSpeech tts = new ALTextToSpeech(application.session());
		ALRobotPosture posture = new ALRobotPosture(application.session());
		ALAudioPlayer audio = new ALAudioPlayer(application.session());
		ALSpeechRecognition speech = new ALSpeechRecognition(application.session());
		ALBodyTemperature bodyTemperature = new ALBodyTemperature(application.session());
		boolean start = true;
		
		motion.wakeUp();
		//posture.goToPosture("Stand", 0.3f);
		List<String> joints = new ArrayList<>(Arrays.asList("RShoulderPitch", "LShoulderPitch"));
		List<Float> angleList = new ArrayList<>(Arrays.asList(-1.0f, 1.0f));
		
		motion.angleInterpolation(joints, angleList, 1.5, true);
		List<Float> angleList2 = new ArrayList<>(Arrays.asList(-0.1f, 0.1f));
		
		motion.angleInterpolation(joints, angleList2, 1.5, true);
		
		
		
		//motion.angleInterpolation("RShoulderRoll", -1.0f, 1.5, true);
		//motion.angleInterpolation("LShoulderRoll", 1.0f, 1.5, true);
		//motion.angleInterpolation("RShoulderPitch", 0.5F, 1.0, true);
		//motion.angleInterpolation("LShoulderPitch", 0.5F, 1.0, true);
//		//motion.angleInterpolation("LShoulderPitch", 0.5f, 1.5, true);
//		motion.angleInterpolation("LElbowYaw", 0.5f, 1.5, true);
//		motion.angleInterpolation("LElbowRoll", 0.5f, 1.5, true);
//
//		motion.angleInterpolation("RElbowYaw", -0.5f, 1.5, true);
//		motion.angleInterpolation("RElbowRoll", -0.5f, 1.5, true);
//
//		motion.angleInterpolation("LElbowYaw", 0.1f, 1.5, true);
//		motion.angleInterpolation("LElbowRoll", 0.1f, 1.5, true);
//
//		motion.angleInterpolation("RElbowYaw", -0.1f, 1.5, true);
//		motion.angleInterpolation("RElbowRoll", -0.1f, 1.5, true);
		
		//motion.angleInterpolation("");
		
		
		
//		for(int i = 0; i < 20; ++i) {
//			//System.out.println("LShoulderPitch: " + memory.getData("Device/SubDeviceList/LShoulderPitch/Temperature/Sensor/Value"));
//			//System.out.println("LShoulderPitch:" + memory.getData("Device/SubDeviceList/LShoulderPitch/Temperature/Sensor/Status"));
//			//System.out.println("LShoulderPitch:" + memory.getData("Device/SubDeviceList/LShoulderPitch/ElectricCurrent/Sensor/Value"));
//			//System.out.println("LShoulderPitch:" + memory.getData("Device/SubDeviceList/LShoulderPitch/Hardness/Actuator/Value"));
//			System.out.println("LShoulderRoll:" + memory.getData("Device/SubDeviceList/LShoulderRoll/Temperature/Sensor/Value"));
//			System.out.println("LShoulderRoll:" + memory.getData("Device/SubDeviceList/LShoulderRoll/Temperature/Sensor/Status"));
//			System.out.println("LShoulderRoll:" + memory.getData("Device/SubDeviceList/LShoulderRoll/ElectricCurrent/Sensor/Value"));
//			System.out.println("LShoulderRoll:" + memory.getData("Device/SubDeviceList/LShoulderRoll/Hardness/Actuator/Value"));
//			System.out.println("RShoulderPitch: " + memory.getData("Device/SubDeviceList/RShoulderPitch/Temperature/Sensor/Value"));
//			System.out.println("RShoulderPitch:" + memory.getData("Device/SubDeviceList/RShoulderPitch/Temperature/Sensor/Status"));
//			System.out.println("RShoulderPitch:" + memory.getData("Device/SubDeviceList/RShoulderPitch/ElectricCurrent/Sensor/Value"));
//			System.out.println("RShoulderPitch:" + memory.getData("Device/SubDeviceList/RShoulderPitch/Hardness/Actuator/Value"));
//		}
		
		//System.out.println(bodyTemperature.getMethodList());
		//System.out.println(bodyTemperature.getTemperatureDiagnosis().toString());
//		speech.unsubscribe("Test");
//
//		ArrayList<String> vocabulary = new ArrayList<String>();
//		vocabulary.add("Hi Eva");
//		vocabulary.add("How are you?");
//		vocabulary.add("How tall are you?");
//		vocabulary.add("How old are you?");
//		vocabulary.add("Can you give me a handshake?");
//		vocabulary.add("Tell me something I don't know");
//		vocabulary.add("Go to sleep");
//
//		speech.setWordListAsVocabulary(vocabulary);
//
//		ArrayList<String> facts = new ArrayList<String>();
//		facts.add("The first oranges weren't orange."
//				+ "The original oranges from Southeast Asia were a tangerine-pomelo hybrid, and they were actually green.");
//		facts.add("Samsung tests their phones with a robot shaped like an ass or butt. It even wears jeans");
//		facts.add("Dogs sniff good smell with their left nostril");
//		facts.add("If you're being violent or drunk in Japan, police will get a futon and roll you into a burrito.");
//		facts.add("If you fart for 6 years and 9 months, enough gas is produced to create the energy of an atomic bomb");
//		facts.add("Worms eat their own poo");
//
//		while(start != false) {
//		speech.subscribe("Test");
//		TimeUnit.SECONDS.sleep(4);
//		ArrayList<Object> word = (ArrayList<Object>)memory.getData("WordRecognized");
//
//		String word2 = (String)word.get(0);
//		System.out.println(word);
//
//		switch(word2) {
//
//		case "Hi Eva":
//			as.say("^start(animations/Stand/Gestures/Hey_2) Goodbye! ^wait(animations/Stand/Gestures/Hey_2)");
//			break;
//
//		case "How are you?":
//			as.say("I am fine,thanks. ^start(animations/Stand/Gestures/You_1) And how are you?");
//			break;
//
//		case "How tall are you?":
//			as.say("^start(animations/Stand/Gestures/Me_1)I am 58 centimeters tall. But I can still crush you");
//			break;
//
//		case "How old are you?":
//			as.say("^start(animations/Stand/Gestures/IDontKnow_1) To me,that is unknown. All I know is that I waked up at Technical University of Moldova");
//			break;
//
//		case "Can you give me handshake?":
//			as.say("^start(animations/Stand/Gestures/Enthusiastic_4) Sure!");
//			motion.angleInterpolation("RShoulderPitch", 0.2f, 1.0f, true);
//			motion.angleInterpolation("RElbowRoll", 0.7f, 0.7f, true);
//			motion.openHand("RHand");
//			TimeUnit.SECONDS.sleep(1);
//			motion.closeHand("RHand");
//			tts.say("Nice to meet you");
//			for(int i = 0; i < 2; i++) {
//				motion.angleInterpolation("RShoulderPitch", 0.8f, 0.5f, true);
//				motion.angleInterpolation("RShoulderPitch", 0.2f, 0.5f, true);
//			}
//			motion.openHand("RHand");
//			motion.closeHand("RHand");
//			posture.goToPosture("Stand", 0.5f);
//			break;
//
//		case "Tell me something I don't know":
//			int n = rand.nextInt(facts.size()) + 0;
//			as.say("^start(animations/Stand/Gestures/Explain_1)" + facts.get(n) + "^wait(animations/Stand/Gestures/Explain_1)");
//            break;
//
//		case "Go to sleep":
//			start = false;
//			speech.unsubscribe("Test");
//			break;
//
//        default:
//        	tts.say("I am sorry, I did not understand you");
//        	break;
//		}
//		//speech.unsubscribe("Test");
//
//		}
//
//
		
  }
}
