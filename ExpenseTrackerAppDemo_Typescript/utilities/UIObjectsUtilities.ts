import { browser } from 'protractor';

class uiObjectsUtilities {

	/**
		 * Identifies a button, based on an image label, and clicks on it.
		 * This function includes an integrated platform-specific OCR customization.
		 *
		 * @author Praveen H
		 *
		 * @version 1.0
		 *
		 * @param imagePath
		 *            : The image that appears on, or related to, the button.
		 * @param threshold
		 *           : The acceptable match level percentage, between 20 and 100.
		 * @param match
		 *           :The needle comparison method.
		 *
		 * @return None
		 *
		 * @see <b> Examples </b> for calling method
		 * @see 1. imageButtonClick("PUBLIC:Test/image1","80", "bounded");
		 * @see : For more information refer <a href=
		 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14876853>imageButtonClick</a>
		 *
		 */
	imageButtonClick(imagePath: string, threshold: number, match: string) {
		var params = {
			'label': imagePath,
			'threshold': threshold,
			'match': match
		};
		return browser.driver.executeScript('mobile:button-image:click', params);
	};


	/**
	 * Identifies a button, based on a text label, and clicks on it.
	 * This function includes an integrated platform-specific OCR customization.
	 *
	 * @author Praveen H
	 *
	 * @version 1.0
	 *
	 * @param expectedText
	 *            : The text that searching for to appear on the button.
	 *
	 * @param threshold
	 *           : The acceptable match level percentage, between 20 and 100.
	 * @param match
	 *           :The needle comparison method.
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. textButtonClick("Submit","80", "bounded");
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877145>textButtonClick</a>
	 *
	 */
	textButtonClick(expectedText: string, threshold: number, match: string) {
		var params = {
			'label': expectedText,
			'threshold': threshold,
			'match': match
		};
		return browser.driver.executeScript('mobile:button-text:click', params);
	};


    /**
    	 * Identifies an edit field, based on a text label, and retrieves its value. The value is returned as a string.
    	 *
    	 * @author Praveen H
    	 *
    	 * @version 1.0
    	 * @param relatedtText
    	 *            : The text that appears on, or related to, the edit field.It is recommended to use the entire string searched for, to ensure that if the OCR misses a few characters, the label will still be found.
    	 *
    	 * @param threshold
    	 *           : The acceptable match level percentage, between 20 and 100.
    	 * @param lines
    	 *           :The number of text lines to be retrieved from the edit field.
    	 *
    	 * @return String containing the lines of text returned from the Text field.
    	 *
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. editGetText("visual","80", "10";
    	 * @see : For more information refer <a href=
    	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877153>editGetText</a>
    	 *
    	 */
	async editGetText(relatedtText: string, threshold: number, lines: number) {
		var params = {
			'label': relatedtText,
			'threshold': threshold,
			'lines': lines
		};
		return await browser.driver.executeScript('mobile:edit-text:get', params);
	};


    /**
    	 *
    	 * Identifies an edit field, based on an image label, and retrieves its text value. The value is returned as a string.
    	 * @author Praveen H
    	 *
    	 * @version 1.0
    	 *
    	 * @param imagePath
    	 *            : The text that appears on, or related to, the edit field.It is recommended to use the entire string searched for, to ensure that if the OCR misses a few characters, the label will still be found.
    	 *
    	 * @param threshold
    	 *           : The acceptable match level percentage, between 20 and 100.
    	 * @param lines
    	 *           :The number of text lines to be retrieved from the edit field.
    	 *
    	 * @return String containing the lines of text returned from the field.
    	 *
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. getImagetText("PRIVATE:images/myImage.png", "80", "10");
    	 * @see : For more information refer <a href=
    	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877157>getImagetText</a>
    	 *
    	 */

	editGetImage(imagePath: string, threshold: number, lines: number) {
		var params = {
			'label': imagePath,
			'threshold': threshold,
			'lines': lines
		};
		return browser.driver.executeScript('mobile:edit-image:get', params);
	};


    /**
    	 * Identifies an edit field, based on an image label, and inserts the specified text in the value parameter into the field
         * The field is in relation to the found label and is defined by the label position and offset parameters.
    	 *
    	 * @author Praveen H
    	 *
    	 * @version 1.0
    	 *
    	 * @param imagePath
    	 *            :The image that appears on, or related to, the edit field.
    	 *
    	 * @param text
    	 *           : String - The text to insert in the edit field.
    	 *
    	 * @param labelDirection
    	 *           :The label position relative to the button. Ex: Inside | Above | Below | Left | Right | Leftmost | Rightmost
    	 *
    	 *@param labelOffset
    	 *           : Value can be in pixels or in percentage of screen height (0-100). X and Y values separated by comma
    	 *
    	 *
    	 * @return None
    	 *
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. setImageText("PRIVATE:images/myImage.png", "United","below", "7%");
    	 * @see : For more information refer <a href=
    	 *     https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877174>setImageText</a>
    	 *
    	 */

	setImageText(imagePath: string, text: string, labelDirection: string, labelOffset: string) {
		var params = {
			'label': imagePath,
			'text': text,
			'label.direction': labelDirection,
			'label.offset': labelOffset
		};
		return browser.driver.executeScript('mobile:edit-image:set', params);
	};

	/**
	   * Identifies an edit field, based on a text label, and inserts the specified text in the value parameter into the field.
	   * The field is in relation to the found label and is defined by the label position and offset parameters.
	   *
	   * @author Praveen H
	   *
	   * @version 1.0
	   *
	   * @param label
	   *            :The text that appears on, or related to, the edit field.
	   *
	   * @param text
	   *           : String - The text to insert in the edit field.
	   *
	   * @param labelDirection
	   *           :The label position relative to the button. Ex: Inside | Above | Below | Left | Right | Leftmost | Rightmost
	   *
	   *@param labelOffset
	   *           : Value can be in pixels or in percentage of screen height (0-100). X and Y values separated by comma
	   *
	   *
	   * @return String containing either "true" or "false" indicating success or failure.
	   *
	   * @see <b> Examples </b> for calling method
	   * @see 1. editSetText("my profile", "Jon smith","below", "7%");
	   * @see : For more information refer <a href=
	   *     https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877169>editSetText</a>
	   *
	   */
	editSetText(label: string, text: string, labelDirection: string, labelOffset: string) {
		var params = {
			'label': label,
			'text': text,
			'label.direction': labelDirection,
			'label.offset': labelOffset
		};
		return browser.driver.executeScript('mobile:edit-text:set', params);
	};


    /**
         * Finds an image (needle) on the device screen (haystack), and stores the coordinates for future commands.
    	 * Perform Find image to identify the location of the image on the screen.
         *
    	 * @author Praveen H
    	 *
    	 * @version 1.0
    	 *
    	 * @param imagePath
    	 *            :The image to search for.The image can be taken from the device screen using the preview tool or selected from the media repository.
    	 *             If selected from the repository the image must be a JPEG, PNG or BMP file.
    	 *
    	 * @param timeOut
    	 *           : The minimum time interval, in seconds, for a single analysis.
    	 *           If the analysis operation takes less than the interval, wait the remaining time before the next analysis operation
    	 *
    	 * @param scroll
    	 *           :Scroll until the label is found. ex: scroll | noscroll
    	 *
    	 *@param maxScroll
    	 *           : The maximum number of scroll actions to perform before returning. Finding the label will stop the scrolling.
    	 *             Effective values are in the range of 0-100. <default: 5>
    	 *
    	 * @param  threshold
    	 *                : The acceptable match level percentage, between 20 and 100.
    	 *
    	 * @param match
    	 *        : The needle comparison method. Ex: Same size | Any size | Bounded size
    	 *
    	 * @return String containing either "true" or "false" indicating success or failure.
    	 *
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. findImage("PUBLIC:/images/flight.png",20,"Scroll" "5","80","bounded");
    	 * @see : For more information refer <a href=
    	 *     https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877219>findImage</a>
    	 *
    	 */
	async findImage(imagePath: string, duration: number, scroll: string, maxScroll: number, threshold: number, match: string) {
		var params = {
			'content': imagePath,
			'duration': duration,
			'threshold': threshold,
			'scrolling': scroll,
			'maxscroll': maxScroll,
			'match': match
		};
		return await browser.driver.executeScript('mobile:image:find', params);
	};


	/**
	 * Finds an image (needle) on the device screen (haystack), and stores the coordinates for future commands.
	 * Perform Find image to identify the location of the image on the screen.
	 *
	 * @author Praveen H
	 *
	 * @version 1.0
	 *
	 * @param expectedText
	 *            :The text to search for.
	 *             It is recommended to use the entire string searched for, to ensure that if the OCR misses a few characters, the needle will still be found
	 *
	 * @param duration
	 *           : The minimum time interval, in seconds, for a single analysis.
	 *           If the analysis operation takes less than the interval, wait the remaining time before the next analysis operation
	 *
	 * @param scroll
	 *           :Scroll until the label is found. ex: scroll | noscroll
	 *
	 *@param maxScroll
	 *           : The maximum number of scroll actions to perform before returning. Finding the label will stop the scrolling.
	 *             Effective values are in the range of 0-100. <default: 5>
	 *
	 * @param  threshold
	 *                : The acceptable match level percentage, between 20 and 100.
	 *
	 *
	 * @return String containing either "true" or "false" indicating success or failure.
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. findText("John","20","Scroll","5");
	 * @see : For more information refer <a href=
	 *     https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877225>findText</a>
	 *
	 */
	async findText(expectedText: string, duration: number, scroll: string, maxScroll: number) {
		var params = {
			'content': expectedText,
			'duration': duration,
			'scrolling': scroll,
			'maxscroll': maxScroll,
			'match': 'contain',
			'words': 'words'
		};
		return await browser.driver.executeScript('mobile:text:find', params);
	};



    /**
         * Finds an image (needle) on the device screen (haystack), and stores the coordinates for future commands.
    	 * Perform Find image to identify the location of the image on the screen.
         *
    	 * @author Praveen H
    	 *
    	 * @version 1.0
    	 *
    	 *
    	 * @param expectedImage
    	 *            :The image to search for.
    	 *             The image can be taken from the device screen using the preview tool or selected from the media repository.
    	 *
    	 * @param threshold
    	 *           : The acceptable match level percentage, between 20 and 100.
    	 *            Too low values can lead to a false positive result, while too high values can lead to a false negative result.
    	 *
    	 * @param matchMode
    	 *           :The needle comparison method.
    	 *            Same size | Any size | Bounded size
    	 *
    	 *
    	 * @return None
    	 *
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. selectImage("PUBLIC:Home-cropped.png","80","bounded");
    	 * @see : For more information refer <a href=
    	 *     https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877149>selectImage</a>
    	 *
    	 */
	selectImage(expectedImage: string, threshold: number, matchMode: string) {
		var params = {
			'content': expectedImage,
			'threshold': threshold,
			'match': matchMode
		};
		return browser.driver.executeScript('mobile:image:select', params);
	};

    /**
         * Verifies that the given image appears on the device screen. In the case of failure, the script will be marked as failed.
    	 * By default the find image function searches for the image in the current screen.
         *
    	 * @author Praveen H
    	 *
    	 * @version 1.0
    	 *
    	 *
    	 * @param expectedImage
    	 *            :The image to search for.
    	 *             The image can be taken from the device screen using the preview tool or selected from the media repository.
    	 *
    	 * @param threshold
    	 *           : The acceptable match level percentage, between 20 and 100.
    	 *            Too low values can lead to a false positive result, while too high values can lead to a false negative result.
    	 *
    	 * @param matchMode
    	 *           :The needle comparison method.
    	 *            Same size | Any size | Bounded size
    	 *
    	 *
    	 * @return String value - "true" or "false"
    	 *
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. imageCheckPoint("PUBLIC:Home-cropped.png","80","bounded");
    	 * @see : For more information refer <a href=
    	 *     https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877163>imageCheckPoint</a>
    	 *
    	 */
	async imageCheckPoint(expectedImage: string, threshold: number, matchMode: string) {
		var params = {
			'content': expectedImage,
			'threshold': threshold,
			'match': matchMode
		};
		return await browser.driver.executeScript('mobile:checkpoint:image', params);
	};

    /**
         * Finds text (needle) on the device screen (haystack) and clicks on it.
    	 * By default the select text function searches for the text in the current screen.
         *
    	 * @author Praveen H
    	 *
    	 * @version 1.0
    	 *
    	 * @param expectedText
    	 *            :The text to search for.
    	 *             It is recommended to use the entire string searched for, to ensure that if the OCR misses a few characters, the needle will still be found.
    	 *
    	 * @param threshold
    	 *           : The acceptable match level percentage, between 20 and 100.
    	 *            Too low values can lead to a false positive result, while too high values can lead to a false negative result.
    	 * @return None
    	 *
    	 * @see <b> Examples </b> for calling method
    	 * @see 1. selectText("John","80");
    	 * @see : For more information refer <a href=
    	 *     https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877221>selectText</a>
    	 *
    	 */
	selectText(expectedText: string, threshold: number) {
		var params = {
			'content': expectedText,
			'threshold': threshold
		};
		return browser.driver.executeScript('mobile:text:select', params);
	};

	/**
	 * Verifies that the given text appears on the device screen. In the case of failure, the script will be marked as failed.
	 *
	 * @author Praveen H
	 *
	 * @version 1.0
	 *
	 *
	 * @param expectedText
	 *            :The text to search for.
	 *
	 *
	 * @param threshold
	 *           : The acceptable match level percentage, between 20 and 100.
	 *            Too low values can lead to a false positive result, while too high values can lead to a false negative result.
	 *
	 *
	 *
	 * @return String value - "true" or "false"
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. textCheckPoint("John","80");
	 * @see : For more information refer <a href=
	 *     https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877166>textCheckPoint</a>
	 *
	 */
	async textCheckPoint(expectedText: string, threshold: number) {
		var params = {
			'content': expectedText,
			'threshold': threshold,
		};
		return await browser.driver.executeScript('mobile:checkpoint:text', params);
	};

};

export {
	uiObjectsUtilities
}