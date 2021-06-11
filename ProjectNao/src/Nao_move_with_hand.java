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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Nao_move_with_hand {
	public static void main(String [] args) throws Exception {
		
        String robotUrl = "tcp://192.168.88.182:9559";
        boolean start = true;
		
		Application application = new Application(args, robotUrl);
		application.start();
		
		ALMemory memory = new ALMemory(application.session());
		ALMotion motion = new ALMotion(application.session());
		ALTextToSpeech tts = new ALTextToSpeech(application.session());
		ALRobotPosture posture = new ALRobotPosture(application.session());
		ALSonar sonar = new ALSonar(application.session());
		ALSpeechRecognition speech = new ALSpeechRecognition(application.session());
		ALAutonomousMoves am = new ALAutonomousMoves (application.session());
		
		//System.out.println(motion.getStiffnesses("RShoulderPitch"));
		//System.out.println(memory.getData("Device/SubDeviceList/RShoulderPitch/Position/Sensor/Value"));
		
		motion.wakeUp();
		posture.goToPosture("Stand", 0.3f);
		am.setExpressiveListeningEnabled(false);
		//speech.unsubscribe("Test");
		
	while(start != false) {	
//		ArrayList <String> vocabulary = new ArrayList <String> ();
//		vocabulary.add("Eva hand");
//		vocabulary.add("Eva hand please");
//		
//		speech.setWordListAsVocabulary(vocabulary);
//		
//        speech.subscribe("Test");
//		
//		TimeUnit.SECONDS.sleep(3); 
//		ArrayList <Object> word = (ArrayList<Object>) memory.getData("WordRecognized");
//		
//		String words = (String)word.get(0);
//		
//		if(words.equals("Eva hand")) {
//			tts.say("No");
//			speech.unsubscribe("Test");
//		} else if (words.equals("Eva hand please")) {
			
		//speech.unsubscribe("Test");
		motion.angleInterpolation("RShoulderPitch", -1.0f, 1.5f, true);
		
		//System.out.println(motion.getStiffnesses("RShoulderPitch"));
		//System.out.println(memory.getData("Device/SubDeviceList/RShoulderPitch/Position/Sensor/Value"));
		
		ArrayList <String> names = new ArrayList <String>(); 
		names.add("RShoulderPitch");
		names.add("RShoulderRoll");
		float timeLists = 1.0f;
		float stiffness = 0.0f;
		
		motion.stiffnessInterpolation(names, stiffness, timeLists);
		motion.setWalkArmsEnabled(false, false);
		
		
		
		boolean stop = true;
		int counter = 0;
		
		while(stop != false) {
			Object RShoulder = memory.getData("Device/SubDeviceList/RShoulderPitch/Position/Sensor/Value");
		    Float RShoulderSituation = new Float((float)RShoulder);
		    
		    Object RRoll = memory.getData("Device/SubDeviceList/RShoulderRoll/Position/Sensor/Value");
		    Float RShoulderRoll = new Float((float)RRoll);
		    
		     if(RShoulderSituation >= -0.7f && RShoulderRoll <= 0.1f && RShoulderRoll >= -0.30f) {
		    	 sonar.subscribe("myApplication");
				 memory.insertData("Device/SubDeviceList/US/Actuator/Value", 68.0);
				 Object RSensor = memory.getData("Device/SubDeviceList/US/Right/Sensor/Value");
				 Float RValue = (float) RSensor;
				 Object LSensor = memory.getData("Device/SubDeviceList/US/Left/Sensor/Value");
				 Float LValue = (float) LSensor;
				 System.out.println(RValue);
				 System.out.println(LValue);
					if( RValue < 0.3f || LValue < 0.3f ) {
					   motion.stopMove();
					   tts.say("There is an obstacle,I can't move any further");
					   sonar.unsubscribe("myApplication");
					 } else {
					   motion.moveToward(0.7f, 0f, 0f); // W
					   sonar.unsubscribe("myApplication");
					 }
		    	 counter = 0;
		     }
		     
		     if(RShoulderSituation < -1.0f) {
		    	 motion.moveToward(-0.5f, 0f, 0f);// S
		    	 counter = 0;
		     }
		     
		     if(RShoulderSituation >= -0.7f && RShoulderRoll < -0.30f) {
		    	 motion.moveToward(0.5f, 0.0f, -0.3f);// D
		    	 counter = 0;
		     }
		     
		     if(RShoulderSituation >= -0.7f && RShoulderRoll > 0.1f) {
		    	 motion.moveToward(0.5f, 0.0f, 0.3f); // A
		    	 counter = 0;
		     }
		     
             if(RShoulderSituation < -0.7 && RShoulderSituation > -1.0) {
		     motion.stopMove();
		     counter++;
		     }
           
             if(counter == 400) {
            	 stop = false;
             }
	  }
		
		posture.goToPosture("Stand", 0.5f);
		start = false;
//	  } else {
//		tts.say("I am sorry,I did not understand you");
//		speech.unsubscribe("Test");
//	}
   }
  }
}
