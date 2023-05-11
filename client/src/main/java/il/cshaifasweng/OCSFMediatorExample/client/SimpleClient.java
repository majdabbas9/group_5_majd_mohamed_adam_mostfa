package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Data;
import il.cshaifasweng.OCSFMediatorExample.entities.MsgClass;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import java.io.IOException;
import java.util.List;

public class SimpleClient extends AbstractClient {

	private static SimpleClient client = null;
	public static  Object data;

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) throws IOException {
		if (msg.getClass().equals(Warning.class)) {
			Warning w=(Warning)msg;
			//EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}
		if(msg.getClass().equals(MsgClass.class))
		{
			MsgClass myMsg = (MsgClass) msg;
			if(myMsg.getMsg().equals("all students"))
			{
				Data.S=myMsg.getObj();

			}
		}

	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3004);
		}
		return client;
	}

}
