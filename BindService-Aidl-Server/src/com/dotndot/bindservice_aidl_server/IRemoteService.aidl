package com.dotndot.bindservice_aidl_server;

interface IRemoteService{
	int connect();
	int disconnect();
	boolean isConnect();
}