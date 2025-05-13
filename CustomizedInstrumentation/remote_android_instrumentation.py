import argparse
import base64
import hashlib
import json
# import subprocess
# from utils import BuildException
import os
import sys
import time
import urllib.request

import requests

# A mapping of API names to their environment and domain-specific URLs.
URLS = {
    "instrumentation": {
        "prod_primary": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktcHJpbWFyeS11cy1lYXN0LTEuYXdzLXByb2QucGVyZmVjdG9tb2JpbGUuY29tL2luc3RydW1lbnRhdGlvbi9hcGkvdjEvY3VzdG9tZXJJbnN0QW5kcm9pZA==",
        "prod_standby": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktc3RhbmRieS11cy1lYXN0LTEuYXdzLXByb2QucGVyZmVjdG9tb2JpbGUuY29tL2luc3RydW1lbnRhdGlvbi9hcGkvdjEvY3VzdG9tZXJJbnN0QW5kcm9pZA==",
        "dev_primary": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktcHJpbWFyeS11cy1lYXN0LTEuYXdzLWRldi5wZXJmZWN0b21vYmlsZS5jb20vaW5zdHJ1bWVudGF0aW9uL2FwaS92MS9jdXN0b21lckluc3RBbmRyb2lk",
        "dev_standby": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktc3RhbmRieS11cy1lYXN0LTEuYXdzLWRldi5wZXJmZWN0b21vYmlsZS5jb20vaW5zdHJ1bWVudGF0aW9uL2FwaS92MS9jdXN0b21lckluc3RBbmRyb2lk",
    },
    "delete": {
        "prod_primary": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktcHJpbWFyeS11cy1lYXN0LTEuYXdzLXByb2QucGVyZmVjdG9tb2JpbGUuY29tL2luc3RydW1lbnRhdGlvbi9hcGkvdjEvY3VzdG9tZXJEZWxldGVDYWNoZQ==",
        "prod_standby": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktc3RhbmRieS11cy1lYXN0LTEuYXdzLXByb2QucGVyZmVjdG9tb2JpbGUuY29tL2luc3RydW1lbnRhdGlvbi9hcGkvdjEvY3VzdG9tZXJEZWxldGVDYWNoZQ==",
        "dev_primary": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktcHJpbWFyeS11cy1lYXN0LTEuYXdzLWRldi5wZXJmZWN0b21vYmlsZS5jb20vaW5zdHJ1bWVudGF0aW9uL2FwaS92MS9jdXN0b21lckRlbGV0ZUNhY2hl",
        "dev_standby": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktc3RhbmRieS11cy1lYXN0LTEuYXdzLWRldi5wZXJmZWN0b21vYmlsZS5jb20vaW5zdHJ1bWVudGF0aW9uL2FwaS92MS9jdXN0b21lckRlbGV0ZUNhY2hl",
    },
    "delete_original": {
        "prod_primary": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktcHJpbWFyeS11cy1lYXN0LTEuYXdzLXByb2QucGVyZmVjdG9tb2JpbGUuY29tL2luc3RydW1lbnRhdGlvbi9hcGkvdjEvY3VzdG9tZXJEZWxldGVPcmlnaW5hbENhY2hl",
        "prod_standby": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktc3RhbmRieS11cy1lYXN0LTEuYXdzLXByb2QucGVyZmVjdG9tb2JpbGUuY29tL2luc3RydW1lbnRhdGlvbi9hcGkvdjEvY3VzdG9tZXJEZWxldGVPcmlnaW5hbENhY2hl",
        "dev_primary": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktcHJpbWFyeS11cy1lYXN0LTEuYXdzLWRldi5wZXJmZWN0b21vYmlsZS5jb20vaW5zdHJ1bWVudGF0aW9uL2FwaS92MS9jdXN0b21lckRlbGV0ZU9yaWdpbmFsQ2FjaGU=",
        "dev_standby": "aHR0cHM6Ly9pbnN0cnVtZW50YXRpb24tZW50cnktc3RhbmRieS11cy1lYXN0LTEuYXdzLWRldi5wZXJmZWN0b21vYmlsZS5jb20vaW5zdHJ1bWVudGF0aW9uL2FwaS92MS9jdXN0b21lckRlbGV0ZU9yaWdpbmFsQ2FjaGU=",
    }
}

def get_args():
    parser = argparse.ArgumentParser(description="Instrumentation CLI",
                                     formatter_class=argparse.RawTextHelpFormatter)
    parser.add_argument('-i', '--input-file', type=str, help='File to Instrument', required=True)
    parser.add_argument('-o', '--output-folder', type=str, help='Output Folder to place the instrumented file', required=True)
    parser.add_argument('-t', '--token', type=str, help='Text File Containing a Valid Security Token', required=True)
    parser.add_argument('-u', '--url', type=str, help='Cloud URL', required=True)
    parser.add_argument('-ih', '--instrument-hybrid', action='store_true', help='Enable Hybrid Instrumentation = spy',
                        required=False)
    parser.add_argument('-is', '--instrument-sensor', action='store_true', help='Enable Sensor Instrumentation = camera, fingerprint, activity',
                        required=False)
    parser.add_argument('-iscr', '--instrument-secured-screen', action='store_true', help='Enable Secured Screen Instrumentation',
                                    required=False)
    parser.add_argument('-cu', '--certificate-user', help='certificate user', required=False)
    parser.add_argument('-cp', '--certificate-password', help='certificate password', required=False)
    parser.add_argument('-kp', '--keystore-password', help='keystore password', required=False)
    parser.add_argument('-cf', '--certificate-file', help='Certificate File', required=False)
    parser.add_argument('-dc', '--delete-cache', action='store_true', help='delete the cache after download',
                        required=False)
    parser.add_argument('-f', '--force-instrument',
                        nargs='?',
                        const=True,
                        metavar='LABEL',
                        help="Force fresh instrumentation even if a cached version exists.\n"
                             "Optionally accepts a custom label to avoid overwriting cached versions.\n"
                             "Example: '-f unique1' or '--force-instrument=unique1'.",
                        required=False)
    parser.add_argument('-e', '--env', type=str, choices=['prod', 'dev'], default='prod', help=argparse.SUPPRESS)
    parser.add_argument('-d', '--domain', type=str, choices=['primary', 'standby'], default='primary', help=argparse.SUPPRESS)


    args = parser.parse_args()
    return args

def getChecksum(f_name):
    hash_sha = hashlib.sha256()
    with open(f_name, "rb") as f:
        for chunk in iter(lambda: f.read(4096), b""):
            hash_sha.update(chunk)
    return hash_sha.hexdigest()

def getSize(f_name):
    return os.path.getsize(f_name)

def printCertificateFile(certifate_file):
    print("certification")
    with open (certifate_file, 'rb') as file_cer:
        print(file_cer.read(16))
    return 0

def get_url(api, env="prod", domain="primary"):
    api = api.lower()
    composite_key = "{}_{}".format(env.lower(), domain.lower())

    if api not in URLS:
        raise ValueError("No URLs defined for API: {}".format(api))

    api_urls = URLS[api]
    if composite_key not in api_urls:
        raise ValueError("No URL defined for key: {} in API: {}".format(composite_key, api))

    encoded_url = api_urls[composite_key]
    return base64.b64decode(encoded_url).decode('utf-8')

def renamed_file_name(path, old_ext=".aab", new_ext=".apks"):
    return os.path.splitext(os.path.basename(path))[0] + new_ext if path.lower().endswith(old_ext.lower()) else os.path.basename(path)

def main():
    # sys.exit(0)
    params = get_args()
    print("********* script parameters *********")
    print(params)


    instrument(params)

def instrument(params):
    print("")

    if not params.instrument_hybrid and not params.instrument_sensor and not params.instrument_secured_screen:
        print("Either Hybrid (-ih), Sensor (-is), Secured-screen (-iscr) or all Flags Must Be Selected")
        sys.exit(1)

    print("********* Upload to Instrumentation Service and Get Link *********")

    with open(params.token, 'r') as token_file:
        # security_token="%s %s" % ("Bearer", my_file.read().replace('\n', ''))
        security_token = token_file.read().replace('\n', '')

    if security_token == '':
        print("Invalid Token!")
        sys.exit(1)

    # Disable for user token
    # security_token="%s %s" % ("Bearer", security_token)
    hdr = {
        'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11',
        'Perfecto-Authorization': security_token,
        'Realm': params.url,
        'Perfecto-Env': params.env,
        'Perfecto-Domain': params.domain
    }

    # instrumentation_params = {"mcmName": "dummy", "requestId": "myReqID", "platform": "android", "applicationFileName": params.input_file, "applicationHash": getChecksum(params.input_file), "hssVersion": "master", "deviceId": "123456", "hybridInstrumentation": params.instrument_hybrid, "sensorsInstrumentation": params.instrument_sensor}

    instrumentation_url = get_url("instrumentation", params.env, params.domain)
    delete_url = get_url("delete", params.env, params.domain)
    delete_original_url = get_url("delete_original", params.env, params.domain)

    application_hash = getChecksum(params.input_file)
    force_instrumentation_requested = True
    if params.force_instrument is not None:
        if params.force_instrument is True:
            print("Force instrumentation without postfix")
        else:
            print("Force instrumentation with postfix: {}".format(params.force_instrument))
            application_hash = application_hash + params.force_instrument
    else:
        print("Using cache normally (no force)")
        force_instrumentation_requested = False


    if params.certificate_file is None:
        certificate_hash = None
    else:
        certificate_hash = getChecksum(params.certificate_file)
        print("certificateHash= " + certificate_hash)
    if params.keystore_password is None:
        keystore_password = params.certificate_password
    else:
        keystore_password = params.keystore_password

    instrumentation_params = {"mcmName": "python_script", "requestId": "pyRequestID", "platform": "android",
                             "applicationName": params.input_file,
                             "applicationHash": application_hash, "hssVersion": "master",
                             "hybridInstrumentation": params.instrument_hybrid, "sensorsInstrumentation": params.instrument_sensor,
                             "cameraInstrumentation": params.instrument_sensor, "fingerprintInstrumentation": params.instrument_sensor,
                             "securedScreenInstrumentation": params.instrument_secured_screen,
                             "sync": True,
                             "doNotUseCache": force_instrumentation_requested,
                             "certificateUser": params.certificate_user,
                             "certificatePassword": params.certificate_password, "keystorePassword": keystore_password,
                             "certificateHash" : certificate_hash
                             }

    print("applicationHash= "+ application_hash)

    with open('instrumentation_params.json', 'w') as file:
        file.write(json.dumps(instrumentation_params))

    if params.certificate_file is None or params.certificate_password is None or params.certificate_user is None:
        multipart_form_data = {
            'application-file': open(params.input_file, 'rb'),
            'instrument-request': ('instrumentation_params.json', open('instrumentation_params.json', 'rb'), 'application/json')
        }
        print("no certificate file provided, using default.")
    else:
        multipart_form_data = {
            'application-file': open(params.input_file, 'rb'),
            'certificate-file': open(params.certificate_file, 'rb'),
            'instrument-request': ('instrumentation_params.json', open('instrumentation_params.json', 'rb'), 'application/json')
        }
        print("add certificate file")

    instrumented_url = requests.post(instrumentation_url, files=multipart_form_data, headers=hdr)

    print(instrumented_url.text)

    token_file.close()
    # os.remove('instrumentation_params.json')

    if instrumented_url.status_code != 200:
        print("Instrumentation Failed!")
        sys.exit(1)

    print("********* Download Link to File (May Take a While) *********")

    b_success = False
    retry = 0
    output_file_name = renamed_file_name(params.input_file)

    output_file = os.path.join(params.output_folder, output_file_name)

    while not b_success and retry < 600:
        try:
            response = urllib.request.urlretrieve(instrumented_url.text, output_file)
            urllib.request.urlcleanup()
            b_success = True
            print('Download succeeded! Instrumented File is at: {}'.format(output_file))
        except urllib.error.HTTPError:
            retry += 1
            print('Download failed, Retry #' + str(retry))
            time.sleep(1)
            pass

    # delete process
    if params.delete_cache:
        form_data = {
            'instrument-request': ('instrumentation_params.json', open('instrumentation_params.json', 'rb'), 'application/json')
        }
        deleted_response = requests.post(delete_url, files=form_data, headers=hdr)
        if deleted_response.status_code != 200 or deleted_response.text != 'true':
            time.sleep(5)
            form_data = {
                'instrument-request': ('instrumentation_params.json', open('instrumentation_params.json', 'rb'), 'application/json')
            }
            deleted_response = requests.post(delete_url, files=form_data, headers=hdr)
            if deleted_response.status_code != 200 or deleted_response.text != 'true':
                time.sleep(25)
                form_data = {
                    'instrument-request': ('instrumentation_params.json', open('instrumentation_params.json', 'rb'), 'application/json')
                }
                deleted_response = requests.post(delete_url, files=form_data, headers=hdr)
                if deleted_response.status_code != 200 or deleted_response.text != 'true':
                    print("Delete cache failed")
                else:
                    print("Delete the cache")

            else:
                print("Delete the cache")
        else:
            print("Delete the cache")

    # delete original
    if params.delete_cache:
        form_data = {
            'instrument-request': ('instrumentation_params.json', open('instrumentation_params.json', 'rb'), 'application/json')
        }
        deleted_response = requests.post(delete_original_url, files=form_data, headers=hdr)
        if deleted_response.status_code != 200 or deleted_response.text != 'true':
            print("Delete original cache failed")

    if not b_success:
        print("Could not download instrumented file, exiting")
        sys.exit(1)

    print("*************************************")

if __name__ == '__main__':
    main()
