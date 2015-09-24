package com.stylingandroid.tts;

import android.media.AudioManager;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
final class VolumeController extends android.speech.tts.UtteranceProgressListener {
    private final AudioManager audioManager;
    private final Map<String, Integer> volumeMap = new HashMap<>();

    VolumeController(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    @Override
    public void onStart(String utteranceId) {
        //NO-OP
    }

    @Override
    public void onDone(String utteranceId) {
        resetVolume(utteranceId);
    }

    @Override
    public void onError(String utteranceId) {
        resetVolume(utteranceId);
    }

    public void setVolume(String utteranceId, float volume) {
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int newVolume = (int) (volume * audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        setMusicVolume(newVolume);
        volumeMap.put(utteranceId, currentVolume);
    }

    private void resetVolume(String utteranceId) {
        Integer volume = volumeMap.remove(utteranceId);
        if (volume != null) {
            setMusicVolume(volume);
        }
    }

    private void setMusicVolume(int volume) {
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
    }
}
