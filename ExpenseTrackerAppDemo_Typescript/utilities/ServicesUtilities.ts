import { browser } from 'protractor';

class servicesUtilities {

	/**
		 * Generates an external voice call recording to the selected destination
		 * It is possible to select multiple destinations that may include devices, users, and phone numbers.
		 * Format -  +[country code][area code][phone number]
		 *
		 * @author Praveen H
		 *
		 * @version 1.0
		 *
		 *
		 * @param PhoneNumber
		 *            :The destination phone number. It is possible to select multiple phone numbers, Example -  +17812054111
		 *
		 *
		 * @return None
		 *
		 * @see <b> Examples </b> for calling method
		 * @see 1. cloudCall("+972541234567");
		 * @see : For more information refer <a href=
		 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14876790>cloudCall</a>
		 *
		 */

	cloudCall(PhoneNumber: string) {
		var params = {
			'to.number': PhoneNumber
		};
		return browser.driver.executeScript('mobile:gateway:call', params);
	};

	/**
		 * Sends an SMS message to the selected destination.
		 * It is possible to select multiple destinations that may include devices, users, and phones.
		 * There is no default. To use, at least one destination must be selected.
		 * The destination phone number. It is possible to select multiple phone numbers.
		 *
		 * @author Praveen H
		 *
		 * @version 1.0
		 *
		 * @param PhoneNumber
		 *            :The destination phone number. It is possible to select multiple phone numbers, Example -  +17812054111
		 *
		 * @param Message
		 *            : The message text for this command, default body - "Test from Perfecto Mobile"
		 * @return None
		 *
		 * @see <b> Examples </b> for calling method
		 * @see 1. cloudSMS("+972541234567", "come to party at 1900");
		 * @see : For more information refer <a href=
		 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=14876831>cloudSMS</a>
		 *
		 */
	cloudSMS(PhoneNumber: string, Message: string) {
		var params = {
			'to.number': PhoneNumber,
			'body': Message
		};
		return browser.driver.executeScript('mobile:gateway:sms', params);
	};


	/**
		 * Use the Network virtualization start and update commands to activate and
		 * update the network conditions. Without this activation there is no effect
		 * on the device communication to the Web
		 *
		 * @author Praveen H
		 *
		 * @version 1.0
		 *
		 *
		 * @param NetworkProfile
		 *            :Suggested network virtualization profiles. 2g_gprs_good |
		 *            2g_gprs_average | 2g_gprs_poor | 2g_edge_good |
		 *            2g_edge_average | 2g_edge_poor | 3g_umts_good |
		 *            3g_umts_average | 3g_umts_poor | 3.5g_hspa_good |
		 *            3.5g_hspa_average | 3.5g_hspa_poor | 3.5g_hspa_plus_good |
		 *            3.5g_hspa_plus_average | 3.5g_hspa_plus_poor | 4g_lte_good |
		 *            4g_lte_average | 4g_lte_poor | 4g_lte_advanced_good |
		 *            4g_lte_advanced_average | 4g_lte_advanced_poor |
		 *            bandwidth_good
		 *
		 *
		 * @return None
		 *
		 * @see <b> Examples </b> for calling method
		 * @see 1. networkVirtualizationStart("4g_lte_good");
		 * @see : For more information refer <a href=
		 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19170103>networkVirtualizationStart</a>
		 *
		 */
	networkVirtualizationStart(NetworkProfile: string) {
		var params = {
			'profile': NetworkProfile
		};
		return browser.driver.executeScript('mobile:vnetwork:start', params);
	};

	/**
		 * Ends the network virtualization simulation.
		 * @author Praveen H
		 *
		 * @version 1.0
		 *
		 *
		 * @return None
		 *
		 * @see <b> Examples </b> for calling method
		 * @see 1. networkVirtualizationStop();
		 * @see : For more information refer <a href=
		 *      https://developers.perfectomobile.com/pages/viewpage.action?pageId=19170107>networkVirtualizationStop</a>
		 *
		 */
	networkVirtualizationStop() {
		var params = {};

		return browser.driver.executeScript('mobile:vnetwork:stop', params);
	};

	/**
	 * Connects the given device to the local network, using the Perfecto Connect tunnel created by the perfectoconnect client.
	 * The tunnelId parameter should provide the identifier of the tunnel to connect through.
	 * Used in conjunction with Perfecto connect detach command.
	 *
	 * @author Praveen H
	 *
	 * @version 1.0
	 *
	 *
	 * @param TunnelID
	 *              :Identifies the connection tunnel created by the perfectoconnect client, used to connect to the local network.
	 *
	 * @return None
	 *
	 * @see <b> Examples </b> for calling method
	 * @see 1. attachPerfectoConnect("Tunnel_ID");
	 * @see : For more information refer <a href=
	 *      https://developers.perfectomobile.com/display/PD/Perfecto+connect+attach>attachPerfectoConnect</a>
	 *
	 */
	attachPerfectoConnect(Tunnel_ID: string) {
		var params = {
			'tunnelId': Tunnel_ID
		};
		return browser.driver.executeScript('mobile:perfectoconnect:attach', params);
	};

	/**
		 * Disconnects the given device from the local network.
		 * The tunnelId parameter should provide the identifier of the tunnel used to connect the device
		 * Used in conjunction with Perfecto connect attach command.
		 *
		 * @author Praveen H
		 *
		 * @version 1.0
		 *
		 *
		 * @param TunnelID
		 *              :Identifies the connection tunnel created by the perfectoconnect client, used to connect to the local network.
		 *
		 * @return None
		 *
		 * @see <b> Examples </b> for calling method
		 * @see 1. detachPerfectoConnect("Tunnel_ID");
		 * @see : For more information refer <a href=
		 *      https://developers.perfectomobile.com/display/PD/Perfecto+connect+detach>detachPerfectoConnect</a>
		 *
		 */
	detachPerfectoConnect(Tunnel_ID: string) {
		var params = {
			'tunnelId': Tunnel_ID
		};
		return browser.driver.executeScript('mobile:perfectoconnect:detach', params);
	};

};

export {
	servicesUtilities
}