import com.aldebaran.qi.Application;
import com.aldebaran.qi.CallError;
import com.aldebaran.qi.helper.proxies.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class InnovativeFest {
    final static String robotUrl = "tcp://192.168.88.215:9559";
    static Map<String, String> phrases = new HashMap<>();
    final static boolean running = false;
    public static Random random = new Random();
    
    static Scanner scanner = new Scanner(System.in);
    
    static {
        phrases.put("0", "Hello, I am Eva. Nice to meet you!");
        phrases.put("1", "I came here from the Technical University of Moldova.");
        phrases.put("2", "My so called \"parents\" are Stanislav and Valeria.");
        phrases.put("3", "I've been taught to dance, say jokes, beatbox and other things");
        phrases.put("4", "^start(animations/Stand/Gestures/Hey_1) Hi, I am Eva. Nice to meet you at the Innovative Fest!");
        phrases.put("5", "^start(animations/Stand/Gestures/Hey_1) Hi, I am Eva. I love working with the students from the Technical University of Moldova.");
    }
    
    
    public static void main(String[] args) throws Exception {
        Application application = new Application(args, robotUrl);
        application.start();
    
        ALMotion motion = new ALMotion(application.session());
        ALAnimatedSpeech animatedSpeech = new ALAnimatedSpeech(application.session());
        ALRobotPosture robotPosture = new ALRobotPosture(application.session());
        ALTextToSpeech tts = new ALTextToSpeech(application.session());
        ALAudioPlayer player = new ALAudioPlayer(application.session());
        
        animatedSpeech.setBodyLanguageModeFromStr("contextual");
        player.stopAll();
        //player.playFileInLoop("/home/nao/songs/Queen-Killer_Queen.mp3");
//        Integer songId = player.loadFile("/home/nao/songs/Queen - Bohemian Rhapsody (Official Video).mp3");
//        player.play(songId);
        motion.wakeUp();
        
        
        
        String line;
        while (true) {
            try {
                line = scanner.nextLine();
                if (line.equals("6")) {
                    shakeHand(motion, tts, robotPosture);
                } else if (line.equals("7")) {
                    break;
                } else {
                    animatedSpeech.say(phrases.get(line));
                }
                robotPosture.goToPosture("Stand", 0.5f);
            } catch (InterruptedException | CallError e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void shakeHand(ALMotion motion, ALTextToSpeech tts, ALRobotPosture posture) throws Exception {
        motion.angleInterpolation("RShoulderPitch", 0.2f, 1.0f, true);
        motion.angleInterpolation("RElbowRoll", 0.7f, 0.7f, true);
        motion.openHand("RHand");
        TimeUnit.SECONDS.sleep(1L);
        motion.closeHand("RHand");
        tts.say("Nice to meet you");
        for(int i = 0; i < 2; i++) {
            motion.angleInterpolation("RShoulderPitch", 0.6f, 0.5f, true);
            motion.angleInterpolation("RShoulderPitch", 0.2f, 0.5f, true);
        }
        motion.openHand("RHand");
        motion.closeHand("RHand");
        posture.goToPosture("Stand", 0.5f);
    }
}
