package com.stylingandroid.tts;

import android.annotation.TargetApi;
import android.os.Build;
import android.speech.tts.TextToSpeech;
/**
 * All tts class are taken from https://bitbucket.org/StylingAndroid/dirty-phrasebook/
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class LollipopTextToSpeech extends TextToSpeechCompat {
    protected LollipopTextToSpeech(TextToSpeech textToSpeech) {
        super(textToSpeech);
    }

    @Override
    public int speak(CharSequence text, int queueMode, String utteranceId) {
        return getTextToSpeech().speak(text, queueMode, null, utteranceId);
    }
}
