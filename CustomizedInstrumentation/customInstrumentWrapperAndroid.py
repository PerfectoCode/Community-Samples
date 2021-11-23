import argparse
import os
# import urllib.request
import urllib
import sys
import time
import platform

import requests

def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('-i', '--apk', type=str, help='apk File to Instrument', required=True)
    parser.add_argument('-o', '--output_file', type=str, help='Output apk File', required=True)
    parser.add_argument('-t', '--token', type=str, help='Text File containing a valid security token', required=False)
    parser.add_argument('-T', '--tokenText', type=str, help='Text containing a valid security token', required=False)
    parser.add_argument('-u', '--url', type=str, help='Cloud URL', required=True)
    parser.add_argument('-v', '--version', type=str, help='Instrumentation Version', required=True)
    parser.add_argument('-ih', '--instrument_hybrid', action='store_true', help='Enable Hybrid Instrumentation = spy',
                        required=False)
    parser.add_argument('-is', '--instrument_sensor', action='store_true',
                        help='Enable Sensor Instrumentation = camera, fingerprint, activity',  required=False)
    parser.add_argument('-iscr', '--instrument_secured_screen', action='store_true', help='Enable Secured Screen Instrumentation',
                                required=False)
    parser.add_argument('-os', '--osVersion', type=str, help='android OS version', required=True)
    parser.add_argument('-nsy', '--notSync', action='store_true', help='not synchronized', required=False)
    parser.add_argument('-cu', '--certificateUser', help='certificate user', required=False)
    parser.add_argument('-cp', '--certificatePassword', help='certificate password', required=False)
    parser.add_argument('-kp', '--keystorePassword', help='keystore password', required=False)
    parser.add_argument('-cf', '--certificateFile', help='certificateFile', required=False)
    parser.add_argument('-dc', '--deleteCache', action='store_true', help='delete the cache after download',
                        required=False)
    parser.add_argument('-cach', '--cache', help='add string to the cache', required=False)
    parser.add_argument('-usn', '--unsigned', action='store_true', help='would return unsigned app', required=False)
    args = parser.parse_args()
    return args

def get_args_ios():
    parser = argparse.ArgumentParser()
    parser.add_argument('-i', '--ipa', type=str, help='ipa File to Instrument', required=True)
    parser.add_argument('-o', '--output_file', type=str, help='Output ipa File', required=True)
    parser.add_argument('-p', '--provisioning_profile', type=str, help='Provisioning Profile to be Used', required=True)
    parser.add_argument('-c', '--certificate_name', type=str, help='Name of the Certificate to be Used', required=True)
    parser.add_argument('-t', '--token', type=str, help='Text File Containing a Valid Security Token', required=True)
    parser.add_argument('-u', '--url', type=str, help='Cloud URL', required=True)
    parser.add_argument('-v', '--version', type=str, help='Instrumentation Version', required=True)
    parser.add_argument('-ih', '--instrument_hybrid', action='store_true', help='Enable Hybrid Instrumentation',
                        required=False)
    parser.add_argument('-is', '--instrument_sensor', action='store_true', help='Enable Sensor Instrumentation',
                        required=False)
    args = parser.parse_args()
    return args

def main():
    # sys.exit(0)
    params = get_args()
    print("********* script parameters *********")
    print(params)
    if not params.instrument_hybrid and not params.instrument_sensor and not params.instrument_secured_screen:
        print("Either Hybrid (-ih), Sensor (-is), Secured-screen (-iscr) or all Flags Must Be Selected")
        sys.exit(1)

    print("")

    if params.token is None and params.tokenText is None:
        print("need token parameter")
        sys.exit(1)

    if params.token is None and not(params.tokenText is None):
        with open('token.txt', 'w') as token_file:
            token_file.write(params.tokenText)
        params.token = 'token.txt'

    with open(params.token, 'r') as my_file:
        # security_token="%s %s" % ("Bearer", my_file.read().replace('\n', ''))
        security_token = my_file.read().replace('\n', '')

    if security_token == '':
        print("Invalid Token!")
        sys.exit(1)
    hdr = {
        'User-Agent': platform.system() + ',Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome',
        'Perfecto-Authorization': security_token,
        'Realm': params.url
        # 'Authorization': security_token
    }
    try:
        os.remove('customInstrumentAndroidD.py')
    except OSError:
        pass

    print("********* Download Instrumentation Script *********")
    try:
        response = requests.get("https://instrumentation-entry-us-east-1.aws-prod.perfectomobile.com/instrumentation/api/v1/getInstScriptAndroid", headers=hdr)
        # response = requests.get("https://instrumentation-entry-us-east-1.aws-dev.perfectomobile.com/instrumentation/api/v1/getInstScriptAndroid", headers=hdr)
        # response = requests.get("http://localhost:8080/instrumentation/api/v1/getInstScriptAndroid", headers=hdr);
        # response = urllib.request.urlretrieve("https://instrumentation-entry-us-east-1.aws-dev.perfectomobile.com/instrumentation/api/v1/getInstScript", 'customInstrumentAndroidD.py')
    except urllib.error.HTTPError:
        print("Failed to Download Instrumentation Script! (HTTP Error)")
        sys.exit(1)

    if response.status_code != 200:
        print("Failed to Download Instrumentation Script! HTTP Status",response.status_code)
        sys.exit(1)
    time.sleep(1)

    with open('customInstrumentAndroidD.py', 'wb') as f:
        f.write(response.content)

    if os.path.getsize('customInstrumentAndroidD.py') == 0:
        print("Failed to Download Instrumentation Script! (Download Error)")
        sys.exit(1)

    # Main Event
    import customInstrumentAndroidD
    customInstrumentAndroidD.instrument(params)

if __name__ == '__main__':
    main()
