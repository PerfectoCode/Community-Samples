var scriptUtilities = exports.scriptUtilities = function () {

/**
	 * Retrieves timer value. There is no need to stop the timer to use the Get
	 * timer function
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param Timer
	 *            : Unique timer identifier, that was used to start the timer.
	 *            Enter a timer identifier that was used in the Timer start
	 *            function, or select 'Script' to get the time passed from the
	 *            beginning of the script Ex: script | TimerA | TimerB | TimerC
	 *            | other_name
	 *
	 *
	 * @param TimerType
	 *            : The selected timer type. String Elapsed - Elapsed time (no
	 *            segmentation) Device - Device work time UX - User experience
	 *            time System - System activity
	 *
	 *
	 * @return timer value
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. getTimer("TimerA", "elapsed");
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19170118>getTimer</a>
	 *
	 */
 this.getTimer = async function (Timer,TimerType) {
    var params = {
        'timerId':Timer ,
        'timerType':TimerType ,
    };
    return await browser.driver.executeScript('mobile:timer:info', params);
};


/**
	 * Begins to measure time, with the specified timer ID, until the Timer stop
	 * function is invoked. The Timer Initialization parameter is useful when
	 * using within a loop, where the timer can be reset or continued with each
	 * iteration.
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param Timer
	 *            Unique timer identifier.Enter an identifier or use one of the
	 *            listed identifiers. Ex: TimerA | TimerB | TimerC | other_name
	 *
	 *
	 *
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. timerStart("TimerA");
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19170113>timerStart</a>
	 *
	 */

this.timerStart = function (Timer) {
    var params = {
        'timerId':Timer
    };
    return browser.driver.executeScript('mobile:timer:start', params);
};


/**
	 * Stops the timer with the specified timer ID.
	 * There is no need to stop the timer to use the Get timer function
	 *
	 * @author Praveen H
	 * @version 1.0
	 *
	 * @param Timer
	 *            Unique timer identifier, that was used to start the timer.
	 *            Enter an identifier or use one of the listed identifiers. Ex: TimerA | TimerB | TimerC | other_name
	 *
	 *
	 *
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. timerStop("TimerA");
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19170116>timerStop</a>
	 *
	 */

this.timerStop = function (Timer) {
    var params = {
        'timerId':Timer
    };
    return browser.driver.executeScript('mobile:timer:stop', params);
}; 
};