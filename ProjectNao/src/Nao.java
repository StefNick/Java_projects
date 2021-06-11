import com.aldebaran.qi.Application;
import com.aldebaran.qi.helper.proxies.ALMotion;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import com.aldebaran.qi.helper.proxies.ALSonar;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;
import com.aldebaran.qi.helper.proxies.ALMemory;
import com.aldebaran.qi.helper.proxies.ALTextToSpeech;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Random;

public class Nao extends JPanel {
	private static int key;
	private static int key2;
	private static char c;

	public Nao() {
		KeyListener listener = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				key = e.getKeyCode();
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				key = 27;
			}
		};
		addKeyListener(listener);
		setFocusable(true);
	}

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame();
		Nao keyboard = new Nao();
		frame.add(keyboard);
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String robotUrl = "tcp://192.168.88.182:9559";

		Application application = new Application(args, robotUrl);
		application.start();

		ALMotion motion = new ALMotion(application.session());
		ALAnimatedSpeech tts = new ALAnimatedSpeech(application.session());
		ALRobotPosture posture = new ALRobotPosture(application.session());
		ALMemory memory = new ALMemory(application.session());
		ALSonar sonar = new ALSonar(application.session());
		ALTextToSpeech tts2 = new ALTextToSpeech(application.session());
		//ALSpeechRecognition speech = new ALSpeechRecognition(application.session());
		//ALAutonomousMoves aaa = new ALAutonomousMoves(application.session());
	   // ALTracker tracker = new ALTracker(application.session());
	    double[] targetCoordinate = {0.1, 0.0, 0.0};
	    
	    List<String> facts = new ArrayList<>();
		facts.add("The first oranges,weren't orange."
				+ "The original oranges from Southeast Asia, were a tangerine-pomelo hybrid, and they were actually green.");
		facts.add("Samsung tests their phones, with a robot shaped like a butt. It even wears jeans");
		facts.add("Dogs sniff good smell with their left nostril");
		facts.add("If you're being violent or drunk in Japan, police will get a futon and roll you into a burrito.");
		facts.add("If you fart for 6 years and 9 months, enough gas is produced to create the energy of an atomic bomb");
		facts.add("Worms eat their own poo");
		facts.add("Hot water is heavier than cold water");
		facts.add("Bananas are curved because they grow towards the sun");
		
		
		Random rand = new Random();
	
		motion.setFallManagerEnabled(true);
		motion.wakeUp();
		
		System.out.println(posture.getPostureList());

		while (true) {
			if (key == 27) { //Esc
				motion.stopMove();
				posture.goToPosture("Stand", 0.5f);
				key = 00;
			}
			else if (key == 87) {
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
					 tts.say("There is an obstacle,I can't move any further.");
					 sonar.unsubscribe("myApplication");
				 } else {
					 motion.moveToward(0.8f, 0f, 0f); // W
					 sonar.unsubscribe("myApplication");
				 }
			
			}
			else if (key == 83) {
				motion.moveToward(-0.5f, 0f, 0f); // S
				
			}
			else if (key == 65) {
				motion.moveToward(0.5f, 0.5f, 0.5709f); // A
				
			}
			else if (key == 68) {
				motion.moveToward(0.5f, 0.5f, -0.5709f);// D
			 	
			}
			
			else if (key == 112)
				motion.wakeUp(); // f1
			else if (key == 113)
				motion.rest(); // f2
			else if (key == 114) {	//f3
				motion.stopMove();
				int n = rand.nextInt(facts.size());
				tts.say("^start(animations/Stand/Gestures/Explain_1)" + facts.get(n) + "^wait(animations/Stand/Gestures/Explain_1)");
				
				
			}
			else if (key == 115) {	//f4
				motion.stopMove();
				tts.say("^start(animations/Stand/Gestures/Hey_3) Hello I am Frank!  Nice to meet you! ^wait(animations/Stand/Gestures/Hey_3) ");
				
			}
			else if (key == 116) {	//f5
				motion.stopMove();
				tts.say("^start(animations/Stand/Gestures/Hey_1) Hi, I am Eva. I love working with the students from the Technical University of Moldova.");
				//tts.say("^start(animations/Stand/Gestures/Hey_1) Hey there sexy! ^wait(animations/Stand/Gestures/Hey_1) ");
				
			}
			else if (key == 117 ) { //F6
				motion.stopMove();
				motion.wbEnable(true);
				motion.wbEnableEffectorControl("RArm", true);
				motion.wbSetEffectorControl("RArm", targetCoordinate);
				Thread.sleep(2000);
				motion.wbEnable(false);
				
			} else if (key == 118) {//F7
				motion.angleInterpolation("RShoulderPitch", 0.2f, 1.0f, true);
				motion.angleInterpolation("RElbowRoll", 0.7f, 0.7f, true);
				motion.openHand("RHand");
				TimeUnit.SECONDS.sleep(1);
				motion.closeHand("RHand");
				tts2.say("Nice to meet you");
				for(int i = 0; i < 2; i++) {
					motion.angleInterpolation("RShoulderPitch", 0.6f, 0.5f, true);
					motion.angleInterpolation("RShoulderPitch", 0.2f, 0.5f, true);
				}
				motion.openHand("RHand");
				motion.closeHand("RHand");
				posture.goToPosture("Stand", 0.5f);
				
			} else if (key == 119 ) {//F8
				motion.angleInterpolation("RShoulderPitch", 0.2f, 1.0f, true);
				motion.angleInterpolation("RElbowRoll", 0.7f, 0.5f, true);
				motion.angleInterpolation("RWristYaw", -0.8f, 0.5f, true);
				tts2.setParameter("pitchShift", 1.0f);
				tts2.say("Hey, here is a brofist");
				TimeUnit.SECONDS.sleep(2);
				posture.goToPosture("Stand", 0.5f);	
				
			}
		}

	}
}
