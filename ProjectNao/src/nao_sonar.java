import java.io.BufferedReader;
import java.io.InputStreamReader;
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



public class nao_sonar {
	
	public static void main(String [] args) throws Exception {
		
		String robotUrl = "tcp://192.168.88.235:9559";
		
		Application application = new Application(args, robotUrl);
		application.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ALMemory memory = new ALMemory(application.session());
		ALSonar sonar = new ALSonar(application.session());
		ALMotion motion = new ALMotion(application.session());
		ALRobotPosture posture = new ALRobotPosture(application.session());
		ALSpeechRecognition speech = new ALSpeechRecognition(application.session());
		
		motion.wakeUp();
		posture.goToPosture("Stand", 0.3f);
		
		sonar.subscribe("myApplication");
		
		memory.insertData("Device/SubDeviceList/US/Actuator/Value", 68.0);
		//memory.insertData("Device/SubDeviceList/US/Sensor/Value", 4.0);
		System.out.println(memory.getData("Device/SubDeviceList/US/Sensor/Value"));
		
		//memory.insertData("Device/SubDeviceList/US/Actuator/Value", 0.0);
		//System.out.println(memory.getData("Device/SubDeviceList/US/Sensor/Value"));
		for(int i = 0; i < 2; i++ ) {
		System.out.println(memory.getData("Device/SubDeviceList/US/Left/Sensor/Value"));
		System.out.println(memory.getData("Device/SubDeviceList/US/Right/Sensor/Value"));
		}
		//memory.insertData("Putulica", 1.03);
		
		//System.out.println(memory.getData("Putulica"));
		
		
		Object RSonar = memory.getData("Device/SubDeviceList/US/Right/Sensor/Value");
		Float RValue = new Float((float)RSonar);
		
		 if(RValue > 0.5 ) System.out.println(RValue);
		 
		 
		 /*memory.removeData("Device/SubDeviceList/US/Left/Sensor/Value");
		 memory.removeData("Device/SubDeviceList/US/Right/Sensor/Value");
		 
		 for(int i = 0; i < 2; i++ ) {
				System.out.println(memory.getData("Device/SubDeviceList/US/Left/Sensor/Value"));
				System.out.println(memory.getData("Device/SubDeviceList/US/Right/Sensor/Value"));
				}
		 */
		 
		System.out.println(memory.getData("Device/SubDeviceList/RShoulderRoll/Position/Sensor/Value"));
		motion.angleInterpolation("RShoulderPitch", -0.9f, 1.5f, true);
		
		Object RArm = new Object();
		
		for(int i = 0; i < 9; i++) {
			RArm = memory.getData("Device/SubDeviceList/ RShoulderRoll/Position/Sensor/Value");
		}
		
		//Object RArm = memory.getData("Device/SubDeviceList/RShoulderRoll/Position/Sensor/Value");
		Float RArmSensor = new Float((float)RArm);
		System.out.println(RArmSensor);
		System.out.println(memory.getData("Device/SubDeviceList/RShoulderRoll/Position/Sensor/Value"));
		
		
		
		System.out.println(memory.getData("Device/SubDeviceList/RShoulderRoll/Position/Sensor/Value"));
		
		sonar.unsubscribe("myApplication");
		
		
		}

}
