# ported from package: com.perfectomobile.selenium.util.EclipseConnector
import socket

class EclipseConnector():
    LOCALHOST = "localhost"
    PORT = 3287
    GET_HOST = "getHost"
    GET_USER = "getUser"
    GET_PASSWORD = "getPassword"
    GET_EXECUTION_ID = "getExecutionId"
    SET_EXECUTION_ID = "setExecutionId"
    GET_DEVICE_ID = "getDeviceId"
    NO_DEVICE = "No device"
    ECLIPSE_EXECUTION_ID = "eclipseExecutionId"
    _socket = None
    _out = None
    _in = None

    def connect(self, cmd):
        try:
            self._out = cmd + '\n'
            self._socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self._socket.connect((self.LOCALHOST, self.PORT))
            self._socket.sendall(self._out.encode(encoding='utf_8', errors='strict'))
            self._in = self._socket.recv(1024).decode('utf_8').strip('\r\n')
            self.close()
        except socket.herror as e:
            print("Can't connect to MobileCloud for Eclipse plugin - unknown host: " + self.LOCALHOST, e)
            raise e
        except socket.error as e:
            print("Failed to connect to MobileCloud for Eclipse plugin on host: " + self.LOCALHOST + " and port: " + self.PORT + ". Possible reasons are: The code doesn't run in Eclipse or the user doesn't have ECLIPSE role or the MobileCloud recording view is not open." + " In these cases use the MobileDriver constructor that receives the host, user and password.", e)
            raise e
        return self._in

    def close(self):
        try:
            self._socket.close()
        except Exception as e:
            print ("Failed to close socket")
            print(e)
            raise e

    def getExecutionId(self):
        executionId = self.connect(self.GET_EXECUTION_ID)
        print("Received eclipse execution id: " + executionId)
        return executionId

    def getDeviceId(self):
        deviceId = self.connect(self.GET_DEVICE_ID)
        print("Received eclipse device id: " + deviceId)
        if self.NO_DEVICE == deviceId:
            deviceId = None
        return deviceId
 
    def getHost(self):
        host = self.connect(self.GET_HOST)
        print("Received eclipse host: " + host)
        return host
 
    def getUser(self):
        user = self.connect(self.GET_USER)
        print("Received eclipse user: " + user)
        return user
 
    def getPassword(self):
        password = self.connect(self.GET_PASSWORD)
        msg = None
        if password == '':
            msg = "Received empty eclipse password."
        else:
            msg = "Received eclipse password."
        print(msg)
        return password
 
    def setExecutionId(self, executionId):
        self.connect(self.SET_EXECUTION_ID + " " + executionId)
        print("Set execution id: " + executionId)
 
    @classmethod
    def main(cls, args):
        clientSocket = EclipseConnector()
        clientSocket.getExecutionId()
        clientSocket.getHost()
        clientSocket.getUser()
        clientSocket.getPassword()
        clientSocket.getExecutionId()
        clientSocket.close()
        print("done")
 
 
if __name__ == '__main__':
    import sys
    EclipseConnector.main(sys.argv)

