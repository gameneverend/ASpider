package com.less.aspider.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 * Created by deeper on 2018/1/7.
 */

public class Player {
    /**
     * 单次播放声音用
     */
    ContinuousAudioDataStream cas;
    /**
     * 循环播放声音
     */
    private AudioStream as;

    public Player(String filename) {
        try {
            InputStream in = new FileInputStream(filename);
            as = new AudioStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (as == null) {
            System.out.println("AudioStream object is not created!");
            return;
        } else {
            AudioPlayer.player.start(as);
        }
    }

    public void stop() {
        if (as == null) {
            System.out.println("AudioStream object is not created!");
            return;
        } else {
            AudioPlayer.player.stop(as);
        }
    }

    /**
     * 循环播放开始
     */
    public void continuousStart() {
        AudioData data = null;
        try {
            data = as.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create ContinuousAudioDataStream.
        cas = new ContinuousAudioDataStream(data);
        // Play audio.
        AudioPlayer.player.start(cas);
    }

    /**
     * 循环播放 停止
     */
    public void continuousStop() {
        if (cas != null) {
            AudioPlayer.player.stop(cas);
        }
    }

    public static void main(String[] args) {
        Player player = new Player("/home/rock/kanxg/tools/pidgin-2.10.11/share/sounds/alert.wav");
        player.start();
    }
}