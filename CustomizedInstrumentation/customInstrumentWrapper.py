import argparse
import os
import urllib.request
import sys
import time

import requests

def get_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('-i', '--ipa', type=str, help='ipa File to Instrument', required=True)
    parser.add_argument('-o', '--output_file', type=str, help='Output ipa File', required=True)
    parser.add_argument('-p', '--provisioning_profile', type=str, help='Provisioning Profile to be Used', required=True)
    parser.add_argument('-c', '--certificate_name', type=str, help='Name of the Certificate to be Used', required=True)
    parser.add_argument('-t', '--token', type=str, help='Text File Containing a Valid Security Token', required=True)
    parser.add_argument('-u', '--url', type=str, help='Cloud URL', required=True)
    parser.add_argument('-v', '--version', type=str, help='Instrumentation Version', required=False)
    parser.add_argument('-ent', '--entitlements_file', type=str, help='(Optional) Entitlements File', required=False)
    parser.add_argument('-ih', '--instrument_hybrid', action='store_true', help='Enable Hybrid Instrumentation',
                        required=False)
    parser.add_argument('-is', '--instrument_sensor', action='store_true', help='Enable Sensor Instrumentation',
                        required=False)
    parser.add_argument('-crp', '--custom_resign_paths', type=str, help='(Optional) Plist file containing Custom Resign Path definitions', required=False)
    args = parser.parse_args()
    return args

def main():
    params = get_args()
    print("********* script parameters *********")
    print(params)
    if not params.instrument_hybrid and not params.instrument_sensor:
        print("Either Hybrid (-ih), Sensor (-is) or Both Flags Must Be Selected")
        sys.exit(1)

    if params.entitlements_file:
        if not os.path.isfile(os.path.expanduser(params.entitlements_file)):
            print("Specified custom entitlements file, however it wasn't found. Exiting!")
            sys.exit(1)


    print("")

    with open(params.token, 'r') as my_file:
        security_token = my_file.read().replace('\n', '')

    if security_token == '':
        print("Invalid Token!")
        sys.exit(1)
    hdr = {
        'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11',
        'Perfecto-Authorization': security_token,
        'Realm': params.url
    }
    try:
        os.remove('customInstrument.pyc')
    except OSError:
        pass
    try:
        os.remove('customInstrument.py')
    except OSError:
        pass
    response=""
    print("********* Download Instrumentation Script *********")
    try:
        response = requests.get("https://instrumentation-entry-primary-us-east-1.aws-prod.perfectomobile.com/instrumentation/api/v1/getInstScript?universal=true", headers=hdr)
    except urllib.error.HTTPError:
        print("Failed to Download Instrumentation Script! (HTTP Error)")
        sys.exit(1)

    if(response.status_code != 200):
        print("Failed to Download Instrumentation Script! HTTP Status",response.status_code)
        sys.exit(1)
    time.sleep(1)

    with open('customInstrument.py', 'wb') as f:
        f.write(response.content)

    if os.path.getsize('customInstrument.py') == 0:
        print("Failed to Download Instrumentation Script! (Download Error)")
        sys.exit(1)

    import customInstrument
    customInstrument.instrument(params)

if __name__ == '__main__':
    main()
