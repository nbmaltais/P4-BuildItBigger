package com.stylingandroid.tts;

import android.speech.tts.TextToSpeech;

import java.util.HashMap;
import java.util.Map;

/**
 * All tts class are taken from https://bitbucket.org/StylingAndroid/dirty-phrasebook/
 */
class LegacyTextToSpeech extends TextToSpeechCompat {
    protected LegacyTextToSpeech(TextToSpeech textToSpeech) {
        super(textToSpeech);
    }

    @Override
    @SuppressWarnings("deprecation")
    public int speak(CharSequence text, int queueMode, String utterenceId) {
        Map<String, String> params = new HashMap<>();
        if (utterenceId != null) {
            params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utterenceId);
        }
        return getTextToSpeech().speak(text.toString(), queueMode, (HashMap<String, String>) params);
    }

    @Override
    protected int silence(int timems, int queueMode, String utteranceId) {
        Map<String, String> params = new HashMap<>();
        if (utteranceId != null) {
            params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, utteranceId);
        }
        return getTextToSpeech().playSilence(timems, queueMode, (HashMap<String, String>)params);
    }
}
