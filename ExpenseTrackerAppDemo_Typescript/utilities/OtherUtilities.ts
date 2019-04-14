import { browser } from 'protractor';

class otherUtilities {

	/**
	 * Adds a comment in the script. Comments appear in the script and in the generated report.
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param String
	 *            : The comment text to display as part of the script and in the report.
	 *
	 *
	 * @return None
	 *
	 * @see : Examples : for calling functions :
	 * @see 1. comment("Hello test");
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/display/PD/Comment>comment</a>
	 *
	 */
	comment(Text: string) {
		var params = {
			'text': Text
		};
		return browser.driver.executeScript('mobile:comment', params);
	};

	/**
	 * Performs the drag gesture
	 * It is possible to define the touch sequence coordinates and time frame of the drag.
	 * There is a touch down at the beginning of the drag, and a touch up at the end of the drag. A touch-down is performed with the first pair of coordinates.
	 * A touch-down is performed with the first pair of coordinates. This is followed by a drag through all the coordinates until the last one, where a touch-up is performed.
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param locationCoordinates
	 *            : The touch event coordinates, Format - either "x1,y1,x2,y2" or "x1%,y1%,x2%,y2%"
	 *
	 * @param duration
	 *            : The duration, in seconds, for performing the drag operation.
	 *
	 * @param auxiliary
	 *            : tap | notap | down | up
	 * @return None
	 *
	 * @see : Examples : for calling functions :
	 * @see 1. drag("20%,27%,20%,65%", "tap",5);
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877239>drag</a>
	 *
	 */
	drag(locationCoordinates: string, Auxiliary: string, duration: number) {
		var params = {
			'location': locationCoordinates,
			'auxiliary': Auxiliary,
			'duration': duration
		};
		return browser.driver.executeScript('mobile:touch:drag', params);
	};


    /**
    	 * Performs the multi-touch operation, pinch and zoom.
    	 * The start and end coordinates must be defined. It is also possible to define the duration of the gesture.
    	 * The start and end coordinates specify the touch sequence path for a single finger, the other finger is symmetrical.
    	 *
    	 * @author Praveen H
    	 * @version 1.0
    	 *
    	 *
    	 * @param startCordinates
    	 *            : The start, touch down, event coordinates. Format - "x,y" or "x%,y%"
    	 *
    	 *@param endCordinates
    	 *             : The end, touch up, event coordinates. Format - "x,y" or "x%,y%"
    	 *
    	 * @param operation
    	 *            : 	The gesture operation type, ex:  zoom | pinch
    	 *
    	 * @param duration
    	 *            : The duration, in seconds, for performing the operation.
    	 *
    	 * @return None
    	 *
    	 * @see : Examples : for calling functions :
    	 * @see 1. gesture("20%,40%","15%,60%", "Zoom",5);
    	 * @see : For more information refer <a href=
    	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877239>drag</a>
    	 *
    	 */
	gesture(startCordinates: string, endCordinates: string, Operation: string, Duration: number) {
		var params = {
			'start': startCordinates,
			'end': endCordinates,
			'operation': Operation,
			'duration': Duration
		};
		return browser.driver.executeScript('mobile:touch:gesture', params);
	};

	/**
	   * Use this function at the beginning of a script, to ensure a known starting point for the user.
	   * For iOS and Android devices, the device is unlocked and returned to its default rotate orientation.
	   *  For example, iPhone devices are returned to portrait mode and iPad devices to landscape mode.
	   *
	   * @author Praveen H
	   * @version 1.0
	   *
	   *
	   * @return None
	   *
	   * @see : Examples : for calling functions :
	   * @see 1. home();
	   * @see : For more information refer <a href=
	   *      https://developers.perfectomobile.com/display/PD/Home>home</a>
	   *
	   */

	home() {
		var params = {
			'target': 'all'
		};
		return browser.driver.executeScript('mobile:handset:ready', params);
	};

	/**
	 * Performs a touch operation on the specified coordinate location. A
	 * long-press can be performed by using the duration parameter. A touch
	 * down/press is released with a touch up/release.
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param coordinates
	 *            : The touch event coordinates. Format - either "x1,y1,x2,y2"
	 *            or "x1%,y1%,x2%,y2%"
	 *
	 * @return None
	 *
	 * @see : Examples : for calling functions :
	 * @see 1. touch("20%,27%,20%,65%");
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877518>touch</a>
	 *
	 */
	touch(coordinates: string) {
		var params = {
			'location': coordinates
		};
		return browser.driver.executeScript('mobile:touch:tap', params);
	};

	/**
		*Performs the swipe gesture.
		*
		* @author Praveen H
		* @version 1.0
		*
		*
		* @param start coordinates
		*            : The start, touch down, event coordinates. Format - "x,y" or "x%,y%"
		*
		* @param end coordinates
		*           : The end, touch up, event coordinates. Format - "x,y" or "x%,y%"
		*
		* @param duration
		*           : The duration, in seconds, for performing the operation.
		*
		* @return None
		*
		* @see : Examples : for calling functions :
		* @see 1. swipe("20%,27%","20%,65%",3);
		* @see : For more information refer <a href=
		*      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14877516>swipe</a>
		*
		*/
	swipe(startCoordinates: string, stopCoordinates: string, Duration: number) {
		var params = {
			'start': startCoordinates,
			'end': stopCoordinates,
			'duration': Duration
		};
		return browser.driver.executeScript('mobile:touch:swipe', params);
	};


};

export {
	otherUtilities
}