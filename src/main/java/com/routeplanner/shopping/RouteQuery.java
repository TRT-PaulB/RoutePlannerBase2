package com.routeplanner.shopping;

public class RouteQuery extends DataModel 
{
	private String currRouteStart;
	
	private String currRouteDest;
	
	private String routeInfo;
	
	public RouteQuery() {
		
	}

	public RouteQuery(String currRouteStart, String currRouteDest, String routeInfo) {
		this.currRouteStart = currRouteStart;
		this.currRouteDest = currRouteDest;
		this.routeInfo = routeInfo;
	}


	public String getCurrRouteStart() {
		return currRouteStart;
	}


	public void setCurrRouteStart(String currRouteStart) {
		this.currRouteStart = currRouteStart;
	}


	public String getCurrRouteDest() {
		return currRouteDest;
	}


	public void setCurrRouteDest(String currRouteDest) {
		this.currRouteDest = currRouteDest;
	}


	public String getRouteInfo() {
		return routeInfo;
	}


	public void setRouteInfo(String routeInfo) {
		this.routeInfo = routeInfo;
	}


	@Override
	public String toString() {
		return "RouteQuery [currRouteStart=" + currRouteStart + ", currRouteDest=" + currRouteDest + ", routeInfo="
				+ routeInfo + "]";
	}
	
}
