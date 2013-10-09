package interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;

public class TextBox extends NinePatch {
        private static TextBox instance;
        
        private TextBox(){
                super(new Texture(Gdx.files.internal("system/menuSkin.png")), 8, 8, 8, 8);
        }
        
        public static TextBox getInstance(){
                if(instance == null){
                        instance = new TextBox();
                }
                return instance;
        }
}