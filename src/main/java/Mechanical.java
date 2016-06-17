import javazoom.jl.player.Player;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.InputStream;

public class Mechanical {

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println("There was a problem registering the native hook.");
            e.printStackTrace();
            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new NativeKeyListener() {
            public void nativeKeyReleased(NativeKeyEvent e) {
                System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
            }

            public void nativeKeyPressed(NativeKeyEvent e) {
                System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

                if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
                    try {
                        GlobalScreen.unregisterNativeHook();
                    } catch (NativeHookException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    try {
                        InputStream in = this.getClass().getResourceAsStream("key.mp3");
                        Player playMP3 = new Player(in);
                        playMP3.play(10);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }


            public void nativeKeyTyped(NativeKeyEvent e) {
                System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
            }
        });
    }
}