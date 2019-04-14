import { browser } from 'protractor';

class cookiesUtilities {

    /**
     * Add a specific cookie
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param name
     *            : The name of the cookie.
     * @param value
     *            : The cookie value.
     * @param path
     *            : The path the cookie is visible to. If left blank will be set
     *            to /.
     * @param domain
     *            : The domain the cookie is visible to.
     * @param expiry
     *            : The cookie's expiration date. The expiry value is the date
     *            in milliseconds(Epoch Time), according to the device date. For
     *            example, 1450948962293.
     * @param secure
     *            : Whether this cookie requires a secure connection.
     * @param httpOnly
     *            : Whether this cookie is a httpOnly cookie.
     *
     * @see <b> Examples </b> for calling method
     * @see 1. addCookie( "xyz", "123", "", "", "", "secure","httpOnly");
     * @see 2. addCookie( "myCookie", "67890", "mydomain.org","1535714230000", "/", null, null);
     *
     * @see :For more information refer <a href=https://developers.perfectomobile.com/display/PD/Add+cookie>addCookie</a>
     */
    async addCookie(name: string, value: string, path: string, domain: string, expiry: string, secure: string, httpOnly: string) {
        var params = {
            'name': name,
            'value': value
        };
        if (!(path == null || path == ''))
            params['path'] = path;
        if (!(domain == null || domain == ''))
            params['domain'] = domain;
        if (!(expiry == null || expiry == ''))
            params['expiry'] = expiry;
        if (!(secure == null || secure == ''))
            params['secure'] = secure;
        if (!(httpOnly == null || httpOnly == ''))
            params['httpOnly'] = httpOnly;

        return await browser.driver.executeScript('mobile:cookie:add', params);
    };

    /**
     * Deletes all cookies from the browser cache. Browser must be open in order
     * for the command to function.
     *
     * <p><b>Note: </b>Delete all cookies will not work if there
     * are Cookies that have the Secure or HttpOnly flag set. Use the Delete
     * cookie command to delete each non-secure cookie.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @see <b> Examples </b> for calling method
     * @see 1. deleteAllCookies();
     *
     * @see :For more information refer <a href=https://developers.perfectomobile.com/display/PD/Delete+all+cookies>deleteAllCookies</a>
     */
    async deleteAllCookies() {
        var params = {};
        return await browser.driver.executeScript('mobile:cookies:delete', params);
    };

    /**
     * Deletes a cookie from the browser cache.
     *
     * <p><b>Note: </b>Delete cookie cannot be used to
     * delete a Cookie that has the Secure or HttpOnly flag set.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param cookieName
     *            : The name of the cookie.
     *
     * @see <b> Examples </b> for calling method
     * @see 1. deleteCookie("myCookie");
     *
     * @see :For more information refer <a href=https://developers.perfectomobile.com/display/PD/Delete+cookie>deleteCookie</a>
     */
    async deleteCookie(cookieName: string) {
        var params = {
            'name': cookieName
        };
        return await browser.driver.executeScript('mobile:cookie:delete', params);
    };

    /**
     * All cookies for the browser are returned in the result data table. Each
     * entry in the data table includes the cookie identifier and the link to
     * the page.
     *
     * @author Kumar L
     * @version 1.0
     *
     * @see <b> Examples </b> for calling method
     * @see 1. getAllCookies();
     *
     * @see :For more information refer <a href=https://developers.perfectomobile.com/display/PD/Get+all+cookies>getAllCookies</a>
     */
    async getAllCookies() {
        var params = {};
        return await browser.driver.executeScript('mobile:cookies:get', params);
    };

    /**
     * Get the value of a specific cookie
     *
     * @author Kumar L
     * @version 1.0
     *
     * @param cookieName
     *            : The name of the cookie.
     *
     * @see <b> Examples </b> for calling method
     * @see 1. getCookie("myCookie");
     *
     * @see :For more information refer <a href=https://developers.perfectomobile.com/display/PD/Get+cookie>getCookie</a>
     */
    async getCookie(cookieName: string) {
        var params = {
            'name': cookieName
        };
        return await browser.driver.executeScript('mobile:cookie:get', params);
    };


};

export {
    cookiesUtilities
}