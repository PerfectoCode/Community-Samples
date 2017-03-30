using ALMReportingBridge.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using TDAPIOLELib;
using static ALMReportingBridge.Entities.ReturnResult;

namespace ALMReportingBridge
{
    public class ALMEntity
    {
        protected string ServerUrl;
        protected string Username;
        protected string Password;
        protected string Domain;
        protected string Project;
        public ReturnResult rr; 

        public ALMEntity(string ServerUrl, string Username, string Password, string Domain, string Project)
        {
            this.ServerUrl = ServerUrl;
            this.Username = Username;
            this.Password = Password;
            this.Domain = Domain;
            this.Project = Project;
            rr = new ReturnResult();
        }

        protected static TDConnection tdc = new TDConnection();

        /// <exception cref="COMException">Server connection error.</exception>
        protected bool Connect(String serverUrl, String username, string password, string domain, string project)
        {
            tdc = new TDConnection();
            try { tdc.InitConnectionEx(serverUrl); }
            catch (System.Runtime.InteropServices.COMException ce)
            {
                rr.AddErrorLine(HandleException(ce));
                //Environment.Exit(0);
            }

            if (tdc.Connected)
            {
                try
                {
                    tdc.Login(username, password);
                }
                catch (System.Runtime.InteropServices.COMException ce)
                {
                    rr.AddErrorLine(HandleException(ce));
                    return false;
                    //Environment.Exit(0);
                }

                if (tdc.LoggedIn)
                {
                    try
                    {
                        tdc.Connect(domain, project);
                    }
                    catch (COMException ce)
                    {
                        rr.AddErrorLine(HandleException(ce));
                        //Environment.Exit(0);
                    }

                    if (tdc.ProjectConnected) { return true; } else { return false; }
                }
                else { return false; }
                
            } else { return false; }
        }

        protected static string HandleException(COMException e)
        {
            string returnString = default(string);
            switch (e.ErrorCode)
            {
                //Not connected
                case -2147219904:
                    returnString = returnString + "Error: " + e.Message;
                    break;

                //Bad URL
                case -2147418113:
                case -2147482494:
                    
                    returnString = returnString + "Error: " + e.Message + "\n";
                    returnString = returnString + "Check the server URL. Server URLs must be in the following format: \n";
                    returnString = returnString + "http://hostname:port/qcbin\n";
                    returnString = returnString + "Example: http://alm.mydomain.com:8080/qcbin\n";
                    break;

                //Bad credentials
                case -2147191498:
                    returnString = returnString + "Error: " + e.Message + "\n";
                    break;

                //Can't connect to project
                //Attachment rename fails
                case -2147220502:
                    returnString = returnString + "Error: " + e.Message + "\n";

                    switch (e.GetHashCode())
                    {
                        case 37320431:
                        case 12506886:
                        case 46804768:
                        case 8865809:
                            returnString = returnString + "Verify the domain and project name are correct and the user has access to the project" + "\n";
                            break;

                        case 54308798:
                            returnString = returnString + "Verify the the attachment upload path is correct." + "\n";
                            break;

                        case 43231651:
                            returnString = returnString + "Make sure the attachment name is not already in use." + "\n";
                            break;

                        default:
                            returnString = returnString + "Unhandled -2147220502" + "\n";
                            returnString = returnString + e.GetHashCode() + "\n";
                            returnString = returnString + "Error: " + e.Message + "\n";
                            break;
                    }

                    break;

                //Create TS fail - name already in use
                case -2147220424:
                    returnString = returnString + "Error creating test set. Verify the name is not already in use." + "\n";
                    break;

                //Create TS fail - no permission
                case -2147220083:
                    returnString = returnString + "Error: " + e.Message + "\n";
                    returnString = returnString + "The user has no permission to create or copy a test set" + "\n";
                    break;

                //Copy/create TS - Folder not found
                //Item not found in list during TS field update
                //User not in user list
                case -2147220481:
                    returnString = returnString + "Error: " + e.Message + "\n";

                    switch (e.GetHashCode())
                    {
                        case 38750844:
                        case 40639578:
                            returnString = returnString + "Check the test set folder path" + "\n";
                            returnString = returnString + "Example valid test set folder path:" + "\n";
                            returnString = returnString + "Root\\Sample Application\\Release 1\\Cycle 1" + "\n";
                            break;

                        case 49044892:
                            returnString = returnString + "Failed to write data to field bound to project lookup list." + "\n";
                            returnString = returnString + "Check the HPE ALM project customization for valid field options." + "\n";
                            break;

                        case 17455632:
                            returnString = returnString + "Failed to write data to field bound to user list." + "\n";
                            returnString = returnString + "Check the HPE ALM project customization for valid field options." + "\n";
                            break;

                        default:
                            returnString = returnString + "Unhandled: -2147220481" + "\n";
                            returnString = returnString + e.GetHashCode() + "\n";
                            break;
                    }
                    break;

                //Copy test set - template name already exists in folder
                case -2147220422:
                    returnString = returnString + "Error: a test set with the same name as the template already exists in the test set folder" + "\n";
                    returnString = returnString + "The new test set was created, but will have the suffix \"_copy_ appended to the name" + "\n";
                    break;

                //Run or test config doesn't exist
                case -2147220084:
                    returnString = returnString + "Error: " + e.Message + "\n";

                    switch (e.GetHashCode())
                    {
                        case 26148945:
                            returnString = returnString + "Run does not exist." + "\n";
                            break;

                        case 3429838:
                            returnString = returnString + "Test configuration does not exist." + "\n";
                            break;

                        default:
                            returnString = returnString + "Unhandled: 2147220084" + "\n";
                            returnString = returnString + e.GetHashCode() + "\n";
                            break;
                    }

                    break;

                //Invalid field name
                case -2147220196:
                    returnString = returnString + "Error: " + e.Message + "\n";
                    returnString = returnString + "Refer to the HPE ALM Project Database Reference for valid field names" + "\n";
                    break;

                //Attempt to put wrong data type in field
                case -2147220199:
                    returnString = returnString + "Error: " + e.Message + "\n";
                    returnString = returnString + "Refer to the HPE ALM Project Database Reference or the project customization for the correct data type for the field" + "\n";
                    break;

                //Failure of transition rules
                case -2147220198:
                    returnString = returnString + "Error: " + e.Message + "\n";
                    returnString = returnString + "Refer to the HPE ALM project customization for valid transitions for this field." + "\n";
                    break;

                //Missing required fields on TS create
                case -2147220197:
                    returnString = returnString + "Error: missing values assigned to required fields." + "\n";
                    returnString = returnString + "Refer to the HPE ALM project customization for required field requirements for this entity." + "\n"; 
                    break;

                //Attempt to modify read only field
                case -2147220201:
                    returnString = returnString + "Error: Refer to the HPE ALM project customization for valid transitions, values, and modifications for this field." + "\n";
                    break;

                //Test set doesn't exist
                case -2147210754:
                    returnString = returnString + "Error: " + e.Message + "\n";
                    returnString = returnString + "Test set does not exist." + "\n";
                    break;

                default:
                    returnString = returnString + "Unhandled Error" + "\n";
                    returnString = returnString + "Error: " + e.Message + "\n";
                    returnString = returnString + e.ErrorCode + "\n";
                    break;
            }

            return returnString;
        }

        protected void Disconnect()
        {
            try
            {
                if (tdc.Connected) {
                    if (tdc.LoggedIn)
                    {
                        if (tdc.ProjectConnected)
                        {
                            tdc.Disconnect();
                        }

                        tdc.Logout();
                    }

                    tdc.ReleaseConnection();
                }

            }
            catch (COMException ce)
            {
                rr.AddErrorLine(HandleException(ce));
            }
        }

        protected static bool CanPerformAction(string actionName)
        {
            ActionPermission actPerimission;
            actPerimission = (ActionPermission)tdc.ActionPermission;

            return actPerimission.ActionEnabled[actionName];
        }

        public enum TestStatus
        {
            No_Run, Not_Completed, Failed, Passed, NA, Blocked
        }

        public enum AttachmentType
        {
            File, URL, url, file, Url
        }

        public bool Test()
        {
            bool returnValue = false;

            try {
                if(!Connect(ServerUrl, Username, Password, Domain, Project))
                {
                    Disconnect();
                    returnValue = false;
                }
                else
                {

                    if (tdc.Connected)
                    {
                        if (tdc.ProjectConnected)
                        {
                            returnValue = true;
                        }
                        else
                        {
                            returnValue = false;
                        }

                    }
                    else { returnValue = false; }
                }
            }
            catch(COMException ce)
            {
                rr.AddErrorLine (HandleException(ce));
            }
            finally { Disconnect(); }

            //WriteResult(Operation.Test, returnValue);
            return returnValue;
        }

        public void WriteResult(Operation op, bool result)
        {
            
            rr.Op = op;
            rr.Result = result;
            rr.BuildXml();
            rr.WriteToConsole();
        }

        public void WriteResult(Operation op, bool result, int entityId)
        {
            //ReturnResult rr = new ReturnResult();
            rr.Op = op;
            rr.Result = result;
            rr.EntityId = entityId;
            rr.BuildXml();
            rr.WriteToConsole();
        }

    }
}
