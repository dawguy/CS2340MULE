package interfaces;

import com.badlogic.gdx.Input.TextInputListener;

/**
 * A simple implementation of libgdx's TextInputListener
 * @author antonio
 * @since 9/17/13
 */
public class MyTextInputListener implements TextInputListener{

	private String data;
	
	public MyTextInputListener(){
		data = "";
	}
	
	@Override
	public void input(String text) {
		data = text;		
	}

	@Override
	public void canceled() {
		data = "";		
	}
	
	public String getData(){
		return data;
	}
}
