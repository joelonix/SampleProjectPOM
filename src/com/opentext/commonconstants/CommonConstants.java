package com.opentext.commonconstants;

import java.io.File;

public class CommonConstants 
{

	
	public static final String USERDIR="user.dir";
	public final static String CONFIGFILE="config"+File.separator+"config.xml";
	public final static String COMMONCONFIGFILE="object-repository"+File.separator+"common_UIMap.xml";
	public final static String OBJREPOFILE=System.getProperty(USERDIR)+File.separator+"object-repository"+File.separator+"objectrepository_UIMap.xml";
	//public final static String OBJECREPO=System.getProperty(USERDIR)+File.separator+"object-repository"+File.separator;
	public final static String IEDRIVERPATH=System.getProperty(USERDIR)+File.separator+"Drivers";
	public final static String CHROMEDRIVERPATH=System.getProperty(USERDIR)+File.separator+"Drivers";
	
}
