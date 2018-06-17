package com.opentext.objects;

public class ObjectFactory 
{

	private static HomePage homepage;
	private static PlanPage planpage;
	
	public static HomePage getHomePageInstance()
	{
		if(homepage==null){
			homepage=new HomePage();
		}
		
		return homepage;
	}
	
	public static PlanPage getPlanPageInstance()
	{
		if(planpage==null){
			planpage=new PlanPage();
		}
		
		return planpage;
	}
	
}
