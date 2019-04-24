import { browser } from 'protractor';

class AudioUtilities {

    /**
     * The command accepts an audio file that may be recorded from a device and
     * creates a text file that contains the textual translation of the audio
     * file.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param deviceAudio
     *            : Identifies the recorded audio file.
     * @param repositoryFile
     *            : Indicates the repository key of an audio file.
     * @param language
     *            : Audio file language. Supported languages include US English,
     *            UK English, Spanish, Japanese, French, Chinese, Portuguese,
     *            Arabic
     * @param rate
     *            : Indicates the sampling rate of the audio recording. Possible
     *            values are narrow or broad. Default value is broad.
     * @param profile
     *            : Selection of the speech-to-text infrastructure used for the
     *            conversion.
     * @param phrases
     *            : Provides a list of phrases for speech-to-text library to use
     *            to avoid confusion. For example, provide the words:"two" and
     *            "four" to avoid confusion with "to" and "for".
     *
     * @see <b> Examples </b> for calling method
     * @see 1. audioToText("","PUBLIC:wideband_ref.wav"
     *      ,"us-english","broad","accuracy",listOfPhrases);
     * @see 2. audioToText("","PUBLIC:wideband_ref.wav"
     *      ,null,null,null,null);
     * @see :For more information refer <a href=
     *      https://developers.perfectomobile.com/display/PD/Audio+to+text>audioToText</a>
     */
    async audioToText(deviceAudio: string, repositoryFile: string, language: string, rate: string, profile: string, phrases: string) {
        var params = {};
        if (!(deviceAudio == null || deviceAudio == ''))
            params['deviceAudio'] = deviceAudio;
        if (!(repositoryFile == null || repositoryFile == ''))
            params['key'] = repositoryFile;
        if (!(language == null || language == ''))
            params['language'] = language;
        if (!(rate == null || rate == ''))
            params['rate'] = rate;
        if (!(profile == null || profile == ''))
            params['profile'] = profile;
        if (!(phrases == null || phrases == ''))
            params['phrase'] = phrases;
        return await browser.driver.executeScript('mobile:audio:text', params);
    };

    /**
     * The command accepts an audio file that may be recorded from a device and
     * creates a text file that contains the textual translation of the audio
     * file.In addition, performs a text checkpoint function, checking for the
     * existence of a text string in the translated text file.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param content
     *            : Text that should be validated against the generated text
     *            file.
     * @param deviceAudio
     *            : Identifies the recorded audio file.
     * @param repositoryFile
     *            : Indicates the repository key of an audio file.
     * @param language
     *            : Audio file language. Supported languages include US English,
     *            UK English, Spanish, Japanese, French, Chinese, Portuguese,
     *            Arabic
     * @param rate
     *            : Indicates the sampling rate of the audio recording. Possible
     *            values are narrow or broad. Default value is broad.
     * @param profile
     *            : Selection of the speech-to-text infrastructure used for the
     *            conversion.
     * @param phrases
     *            : Provides a list of phrases for speech-to-text library to use
     *            to avoid confusion. For example, provide the words:�two� and
     *            �four� to avoid confusion with �to� and �for�.
     *
     * @see <b> Examples </b> for calling method
     * @see 1. audioToTextValidation("Hello, Welcome"
     *      ,"","PUBLIC:wideband_ref.wav"
     *      ,"us-english","broad","accuracy",listOfPhrases);
     * @see 2. audioToTextValidation("Hello, Welcome"
     *      ,"","PUBLIC:wideband_ref.wav" ,null,null,null,null);
     * @see :For more information refer <a href=
     *      https://developers.perfectomobile.com/display/PD/Audio+to+Text+Validation>audioToTextValidation</a>
     */
    async audioToTextValidation(content: string, deviceAudio: string, repositoryFile: string, language: string, rate: string, profile: string, phrases) {
        var params = {
            'content': content,
            'language': language,
            'rate': rate,
            'profile': profile
        };
        if (!(deviceAudio == null || deviceAudio == ''))
            params['deviceAudio'] = deviceAudio;
        if (!(repositoryFile == null || repositoryFile == ''))
            params['key'] = repositoryFile;
        if (!(phrases == null || phrases == ''))
            params['phrase'] = phrases;
        return await browser.driver.executeScript('mobile:audio-text:validation', params);
    };

    /**
     * The command receives an audio file, may have been recorded from a device,
     * uses the parameters to trim off any silence from the beginning or end of
     * the audio. Using this trimmed file, executes an analysis service
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param deviceAudio
     *            : Identifies the recorded audio file.
     * @param repositoryFile
     *            : Indicates the repository key of an audio file.
     * @param profile
     *            : Indicates the type of analyzed voice. The default profile is
     *            VOIP. The VOLTE profile can be used when the audio was
     *            recorded from an LTE connection.
     * @param start
     *            :
     * @param end
     *            :
     * @param noiseAudioFile
     *            :
     *
     * @see <b> Examples </b> for calling method
     * @see 1. audioValidation("","PUBLIC:wideband_ref.wav","us-english",
     *      "voip",,,"");
     * @see 2. audioValidation("","PUBLIC:wideband_ref.wav"
     *      ,null,null,null,null);
     * @see :For more information refer <a href=
     *      https://developers.perfectomobile.com/display/PD/Audio+Validation>audioValidation</a>
     */
    async audioValidation(deviceAudio: string, repositoryFile: string, profile: string, noiseAudioFile: string) {
        var params = {
            'deviceAudio': deviceAudio
        };
        if (!(repositoryFile == null || repositoryFile == ''))
            params['key'] = repositoryFile;
        if (!(profile == null || profile == ''))
            params['profile'] = profile;
        if (!(noiseAudioFile == null || noiseAudioFile == ''))
            params['deviceAudio.calibration'] = noiseAudioFile;
        return await browser.driver.executeScript('mobile:audio:validation', params);
    };

    /**
     * Plays an audio file into the device audio-in. Supported file types: MP3
     * and WAV
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param repositoryFile
     *            : The full repository path, including directory and file name,
     *            where to locate the audio file.
     *
     * @see <b> Examples </b> for calling method
     * @see 1. injectAudio("PUBLIC:wideband_ref.wav");
     * @see 2. injectAudio("PUBLIC:wideband_ref.mp3");
     * @see :For more information refer <a href=
     *      https://developers.perfectomobile.com/display/PD/Inject+audio>injectAudio</a>
     */
    async injectAudio(repositoryFile: string) {
        var params = {
            'key': repositoryFile,
            'wait': 'wait',
        };
        return await browser.driver.executeScript('mobile:audio:inject', params);
    };

    /**
     * Command starts recording the audio output from the device and creates a
     * WAV file.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @return A URL to the WAV file location created
     *
     * @see <b> Examples </b> for calling method
     * @see 1. startAudioRecording();
     * @see :For more information refer <a href=
     *      https://developers.perfectomobile.com/display/PD/Start+audio+recording>startAudioRecording</a>
     */
    async startAudioRecording() {
        var params = {};
        return await browser.driver.executeScript('mobile:audio.recording:start', params);
    };

    /**
     * Command stops recording the audio output from the device, closes the
     * file, and stores in the media storage server at the URL declared at the
     * start of audio recording.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @see <b> Examples </b> for calling method
     * @see 1. stopAudioRecording();
     * @see :For more information refer <a href=
     *      https://developers.perfectomobile.com/display/PD/Stop+audio+recording>stopAudioRecording</a>
     */
    async stopAudioRecording() {
        var params = {};
        return await browser.driver.executeScript('mobile:audio.recording:stop', params);
    };

    /**
     * The command accepts either a text file or text string and returns an
     * audio file. The function can be configured to produce the audio with a
     * male or female voice, and supports several languages.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param fileToStoreAudio
     *            : Repository key for storing the audio file.
     * @param textInput
     *            : The text string to convert to audio.
     * @param textInputFile
     *            : Repository key of a text file. Either Text or Text file key must be specified. Max text length is ~5000 characters.
     * @param language
     *            : Text and audio language. The supported languages are US English, UK English, Spanish, Japanese, French, German, Portuguese, Italian
     * @param gender
     *            : Indicates the gender voice to use in the audio result file. Values are either 'male' or 'female'.
     *
     * @see <b> Examples </b> for calling method
     * @see 1. textToAudio("","","","","");
     * @see :For more information refer <a href=
     *      https://developers.perfectomobile.com/display/PD/Text+to+audio>textToAudio</a>
     */
    async textToAudio(fileToStoreAudio: string, textInput: string, textInputFile: string, language: string, gender: string) {
        var params = {
            'repositoryFile': fileToStoreAudio,
            'language': language,
            'gender': gender
        };
        if (!(textInput == null || textInput == ''))
            params['text'] = textInput;
        if (!(textInputFile == null || textInputFile == ''))
            params['key'] = textInputFile;
        return await browser.driver.executeScript('mobile:text:audio', params);
    };

    /**
     * Sends text String to the device's Voice Assistant (Siri) and allows the
     * assistant to complete the action.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param text
     *           : the text to send to siri
     *
     * @see <b> Examples </b> for calling method
     * @see 1. voiceAssistantInject("Send text here");
     * @see :For more information refer <a href=
     *      https://developers.perfectomobile.com/display/PD/Voice+assistant+inject>voiceAssistantInject</a>
     *
     */
    async voiceAssistantInject(text: string) {
        var params = {
            'text': text
        };
        return await browser.driver.executeScript('mobile:voice:assist', params);
    };

    /**
     * Ensure the device is playing sounds. The audio checkpoint validates that
     * an audio clip is playing on the device.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param volume
     *           : The minimal volume threshold in decibels.
     * @param duration
     *           : The time, in seconds, to wait for the audio to play.
     * @param timeout
     *           : The length, in seconds, of the validation, to ensure the device plays audio during a set period of time. Setting this parameter will cause the system to continually check the audio output for the defined duration.
     *
     * @see <b> Examples </b> for calling method
     * @see 1. audioCheckpoint(-80,12,20);
     * @see :For more information refer <a href=
     *      https://developers.perfectomobile.com/display/PD/Audio+checkpoint>audioCheckpoint</a>
     *
     */
    async audioCheckpoint(volume: number, duration: number, timeout: number) {
        var params = {
            'volume': volume,
            'duration': duration,
            'timeout': timeout
        };
        return await browser.driver.executeScript('mobile:checkpoint:audio', params);
    };
};

export {
    AudioUtilities
}