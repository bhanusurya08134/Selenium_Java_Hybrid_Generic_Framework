package ui.creative.componentgroups;

public class ResponseTags { 
	
	public static final String TOKEN = "token";
	
	/**************************************** RAVI ********************************************************/
	
	public static final String GetFleetByDevice_ID="id";
	public static final String GetFleetByDevice_FLEETID="fleetId";
	public static final String GetFleetByDevice_DEVICETYPE="deviceType";
	public static final String GetFleetByDevice_NOTASSIGNED = "Not Assigned";
	
	public static final String ActivateLicense_LICENSEID = "licenseId";
	public static final String ActivateLicense_STATUS = "status";
	public static final String ActivateLicense_STATUSCOMMENT = "statusComment";
	
	public static final String DeactivateLicense_LICENSEID = "licenseId";
	public static final String DeactivateLicense_STATUS = "status";
	public static final String DeactivateLicense_STATUSCOMMENT = "statusComment";

	public static final String GetDeviceConfig_DATA = "data";
	
	/***********************************************************************************************************/
	
	
	
	
	
	
	/**************************************** NIANCY **********************************************************/
	
	public static final String GetAssignedLicense_ID="id";
	public static final String GetAssignedLicense_FLEET="fleet";
	public static final String GetAssignedLicense__EXPIRYDATE="expiryDate";
	public static final String GetAssignedLicense_LICENSESTATUS="licenseStatus";
	public static final String GetAssignedLicense_SLOTTYPE="slotType";

	
	public static final String GetDeviceTemplateAndSettings_TEMPLATEID="templateId";
	public static final String GetDeviceTemplateAndSettings_TEMPLATENAME="templateName";
	public static final String GetDeviceTemplateAndSettings_TEMPLATEJSON_NAME="templateJson.name";
	public static final String GetDeviceTemplateAndSettings_TEMPLATEJSON="templateJson";
	public static final String GetDeviceTemplateAndSettings_TEMPLATEJSON_ID="templateJson.id";
	public static final String GetDeviceTemplateAndSettings_DEVICESETTINGS_SETTINGSID="deviceSettings[0].settingsId";
	public static final String GetDeviceTemplateAndSettings_DEVICESETTINGS="deviceSettings";
	public static final String GetDeviceTemplateAndSettings_DEVICESETTINGS_SETTINGSJSON="deviceSettings[0].settingsJson";
	public static final String GetDeviceTemplateAndSettings_DEVICESETTINGS_SETTINGSNAME="deviceSettings[0].settingsName";
	public static final String GetDeviceTemplateAndSettings_DEVICESETTINGS_CURRENTSETTING="deviceSettings[0].currentSetting";
	public static final String GetDeviceTemplateAndSettings_ERROR="error";
	public static final String GetDeviceTemplateAndSettings_ERROR_PATH="path";
	
	
	/**********************************************************************************************************/
	public static final String GetApplicationIDDetails_APPLICATIONID= "applicationId";
	public static final String GetApplicationIDDetails_DESCRIPTION= "description";
	
	
	public static final String GetTenantDetails_TENANTID= "tenantId";
	public static final String GetTenantDetails_TENANTNAME= "tenantName";
	public static final String GetTenantDetails_TENANTTYPE= "tenantType";
	public static final String GetTenantDetails_LICENSES= "applications[0].licenses";
	public static final String GetTenantDetails_TENANTALIAS= "tenantAlias";
	public static final String GetTenantDetails_TENANTADMINEMAIL= "tenantAdminEmail";
	public static final String GetTenantDetails_DESCRIPTION= "applications[0].description";
	public static final String GetTenantDetails_APPLICATIONID= "applications[0].applicationId";
		public static final String GetTenantDetails_STATUS= "applications[0].status";
	public static final String GetUsersDetails_VALUE= "results[0].claims[0].value";
	
	////////////////
	public static final String GetUsersDetails_TYPE= "results[0].claims[1].type";
	public static final String GetUsersDetails_TENANTVALUE= "results[0].claims[1].value";
	public static final String GetUsersDetails_ADDEDVALUE= "results[0].claims[2].value";	
	
	
	/**************************************** DILEEP **********************************************************/
	
	public static final String GetAvailableLicense_ID="id";
	public static final String GetAvailableLicense_FLEET="fleet";
	public static final String GetAvailableLicense__EXPIRYDATE="expiryDate";
	public static final String GetAvailableLicense_LICENSESTATUS="licenseStatus";
	public static final String GetAvailableLicense_SLOTTYPE="slotType";
	public static final String GetAvailableLicense_SLOTS="slots";
	public static final String GetAvailableLicense_SLOTUSED="slotUsed";
	
	public static final String GetAvailableLicense_TIMESTAMP="timestamp";
	public static final String GetAvailableLicense__STATUS="status";
	public static final String GetAvailableLicense_ERROR="error";
	public static final String GetAvailableLicense_EXCEPTION="exception";
	public static final String GetAvailableLicense_MESSAGE="message";
	public static final String GetAvailableLicense_PATH="path";
	
	public static final String getSoftwareTemplateAndSetting_TEMPLATEID="templateId";
	public static final String getSoftwareTemplateAndSetting_TEMPLATENAME="templateName";
	public static final String getSoftwareTemplateAndSetting_TEMPLATEJSON="templateJson";
	public static final String getSoftwareTemplateAndSetting_SWSETTING_SETTINGSID="swSetting.settingsId";
	public static final String getSoftwareTemplateAndSetting_SWSETTING_SETTINGSJSON="swSetting.settingsJson";
	public static final String getSoftwareTemplateAndSetting_SWSETTING_SETTINGSNAME="swSetting.settingsName";
	public static final String getSoftwareTemplateAndSetting_SWSETTING_CURRENTSETTING="swSetting.currentSetting";

	public static final String getSoftwareTemplateAndSetting_TIMESTAMP="timestamp";
	public static final String getSoftwareTemplateAndSetting__STATUS="status";
	public static final String getSoftwareTemplateAndSetting_ERROR="error";
	public static final String getSoftwareTemplateAndSetting_EXCEPTION="exception";
	public static final String getSoftwareTemplateAndSetting_MESSAGE="message";
	public static final String getSoftwareTemplateAndSetting_PATH="path";
	
	/**********************************************************************************************************/
	//Dilip
	public static final String getTenantDetails_USERLICENSE= "applications[0].licenses.user";
	public static final String getTenantDetails_VEHICLELICENSE= "applications[0].licenses.vehicle";
	public static final String getTenantDetails_STUDENTTRACKINGLICENSE= "applications[0].licenses.student_tracking";
	public static final String getTenantDetails_APPLICENSE= "applications[0].licenses.app";
	public static final String getTenantDetails_STATUS= "applications[0].status";
	public static final String getTenantDetails_APPLICATIONID="applications[0].applicationId";
	public static final String getTenantDetails_OLDITEMSTATUS="applications[1].status";
	public static final String getTenantDetails_LICENSES="applications[0].licenses";
	
	/************************************/
	//Rajath
	public static final String GetTenantDetails_DEVICEID= "devices[0].deviceId";
	public static final String GetTenantDetails_DeviceType= "devices[0].deviceType";  
	
	
}
