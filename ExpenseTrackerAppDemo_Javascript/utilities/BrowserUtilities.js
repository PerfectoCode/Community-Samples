var browserUtilities = exports.browserUtilities = function () {

/**
	 * Clears the browser content. If this command is used, browser will start
	 * afresh as in you have launched the browser for first time after
	 * installing it
	 *
	 * @author Kumar L
	 * @version 1.0
	 *
	 * @see <b> Examples </b> for calling function
	 * @see 1. browserClean();
	 * @see :For more information refer <a href=
	 *      http://developers.perfectomobile.com/display/PD/Browser+clean>browserClean</a>
	 */
	this.browserClean = async function () {
		var params = {};
		return await browser.driver.executeScript('mobile:browser:clean', params);
	};

/**
	 * Executes a JavaScript script on the device browser application
	 *
	 * @author Kumar L
	 * @version 1.0
	 *
	 * @param script
	 *            : The JavaScript code to execute.
	 * @param repositoryFile
	 *            : The full repository path, including directory and file name,
	 *            where to locate the JavaScript script.
	 * @param timeout
	 *            : The time, in seconds, to wait for the page to be loaded in
	 *            order to execute the JavaScript code.
	 *
	 * @see <b> Examples </b> for calling function
	 * @see 1. browserExecute("var helloStr = \"Hello\";",null,40);
	 * @see 2. browserExecute(null,"PUBLIC:Javascript/abc.js",40);
	 * @see :For more information refer <a href=
	 *      http://developers.perfectomobile.com/display/PD/Browser+execute>browserExecute</a>
	 */
	this.browserExecute = async function (script,repositoryFile,timeout) {
		var params = {};

		if (!(script == null || script == ''))
            params['script'] = script;
        if (!(repositoryFile == null || repositoryFile == ''))
            params['repositoryFile'] = repositoryFile;
        if (timeout > 0)
        	params['timeout'] = timeout;
		return await browser.driver.executeScript('mobile:browser:execute', params);
	};

/**
	 * Opens the browser application and browses to the specified location
	 *
	 * @author Kumar L
	 * @version 1.0
	 *
	 * @param url
	 *            : The specified URL.
	 * @param automation
	 *            : The automation mode for web applications.
	 *
	 * @see <b> Examples </b> for calling function
	 * @see 1. browserGoto("http://www.nxc.co.il/demoaut",null);
	 * @see 2. browserGoto("http://www.nxc.co.il/demoaut","chrome" );
	 * @see 3. browserGoto("http://www.nxc.co.il/demoaut","safari" );
	 * @see 4. browserGoto("http://www.nxc.co.il/demoaut", "simulated");
	 * @see :For more information refer <a href=
	 *      http://developers.perfectomobile.com/display/PD/Browser+go+to>browserGoto</a>
	 */
	this.browserGoto = async function (url,automation) {
		var params = {'url':url};

        if (!(automation == null || automation == ''))
            params['automation'] = automation;

		return await browser.driver.executeScript('mobile:browser:goto', params);
	};

/**
	 * Directs the browser page back and forward. It also refreshes the webpage.
	 *
	 * @author Kumar L
	 * @version 1.0
	 *
	 * @param target
	 *            : The browser page navigation target direction.
	 *
	 * @see <b> Examples </b> for calling function
	 * @see 1. browserNavigate("forward");
	 * @see 2. browserNavigate("back");
	 * @see 3. browserNavigate("refresh");
	 * @see :For more information refer <a href=
	 *      http://developers.perfectomobile.com/display/PD/Browser+navigate>browserNavigate</a>
	 */
	this.browserNavigate = async function (target) {
		var params = {
			'target':target
		};
		return await browser.driver.executeScript('mobile:browser:navigate', params);
	};

/**
	 * Opens the browser
	 *
	 * @author Kumar L
	 * @version 1.0
	 *
	 * @param automation
	 *            : The automation mode for web applications.
	 *
	 * @see <b> Examples </b> for calling function
	 * @see 1. browserOpen(null);
	 * @see 2. browserOpen("chrome" );
	 * @see 3. browserOpen("safari" );
	 * @see 4. browserOpen("simulated");
	 *
	 * @see :For more information refer <a href=
	 *      http://developers.perfectomobile.com/display/PD/Browser+open>browserOpen</a>
	 */
	this.browserOpen = async function (automation) {
		var params = {};
		if (!(automation == null || automation == ''))
                params['automation'] = automation;

		return await browser.driver.executeScript('mobile:browser:open', params);
	};

/**
	 * Verifies the browser application is running and page is loaded.
	 *
	 * @author Kumar L
	 * @version 1.0
	 *
	 * @param timeout
	 *            : The time, in seconds, to wait for the page to be loaded.
	 *
	 * @see <b> Examples </b> for calling function
	 * @see 1. browserSync(40);
	 * @see 2. browserSync(30);
	 *
	 * @see :For more information refer <a href=
	 *      http://developers.perfectomobile.com/display/PD/Browser+sync>browserSync</a>
	 */
	this.browserSync = async function (timeout) {
		var params = {
			'timeout':timeout
		};
		return await browser.driver.executeScript('mobile:browser:sync', params);
	};

};