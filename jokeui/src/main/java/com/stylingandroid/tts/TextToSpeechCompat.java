package com.stylingandroid.tts;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.speech.tts.TextToSpeech;

import java.util.Locale;
/**
 * All tts class are taken from https://bitbucket.org/StylingAndroid/dirty-phrasebook/
 */
public abstract class TextToSpeechCompat {
    private static final String UTTERANCE_ID_FORMAT = "com.stylingandroid.tts.TextToSpeechCompat-%d";
    private static int currentUtteranceId = 0;
    private final TextToSpeech textToSpeech;


    protected TextToSpeechCompat(TextToSpeech textToSpeech) {
        this.textToSpeech = textToSpeech;
        //this.volumeController = volumeController;
    }

    public static TextToSpeechCompat newInstance(Context context, TextToSpeech.OnInitListener initListener) {
        TextToSpeech textToSpeech = new TextToSpeech(context, initListener);
        TextToSpeechCompat textToSpeechCompat;
        //AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        //VolumeController volumeController = new VolumeController(audioManager);
        //textToSpeech.setOnUtteranceProgressListener(volumeController);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeechCompat = new LollipopTextToSpeech(textToSpeech);
        } else {
            textToSpeechCompat = new LegacyTextToSpeech(textToSpeech);
        }
        return textToSpeechCompat;
    }

    private static String getUtteranceId() {
        return String.format(Locale.UK, UTTERANCE_ID_FORMAT, currentUtteranceId++);
    }

    protected TextToSpeech getTextToSpeech() {
        return textToSpeech;
    }

    public boolean isLanguageAvailable(Locale locale) {
        int availability = textToSpeech.isLanguageAvailable(locale);
        return availability != TextToSpeech.LANG_NOT_SUPPORTED;
    }

    public int setLanguage(Locale locale) {
        return textToSpeech.setLanguage(locale);
    }

    public int speak(CharSequence text, int queueMode) {
        String utteranceId = getUtteranceId();
        //volumeController.setVolume(utteranceId, volume);
        return speak(text, queueMode, utteranceId);
    }

    protected abstract int speak(CharSequence text, int queueMode, String utteranceId);

    public void shutdown() {
        textToSpeech.shutdown();
    }

}
