package net.kiberion.tiled.renderers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import lombok.Setter;

public class OrthogonalTiledMapRendererWithObjects extends OrthogonalTiledMapRenderer{

    private static final Logger log = LogManager.getLogger(); 
    
    private FrameBuffer occlusionFbo;
    private FrameBuffer shadowmapFbo;
    private Texture shadowmapTex;
    
    @Setter
    private int mapSizeY;
    
    private Color lightColor;
    
    private Light mouseLight;
    class Light {    	
    	public float x, y;
    	public Color color;
    	
    	public Light(float x, float y, Color color) {
    		this.x = x;
    		this.y = y;
    		this.color = color;
    	}
    }
    
    private List<Light> lights;
    
    @Setter
    private ShaderRegistry shaderRegistry;
    
    private OrthographicCamera orthoCam;
    
    
    
    public OrthogonalTiledMapRendererWithObjects(TiledMap map) {
        super(map);
        
        this.occlusionFbo = new FrameBuffer(Format.RGBA8888, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, false);
        this.shadowmapFbo = new FrameBuffer(Format.RGBA8888, Gdx.graphics.getWidth() / 2, 1, false);
        
        this.shadowmapTex = shadowmapFbo.getColorBufferTexture();
        this.shadowmapTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        this.shadowmapTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        
        this.orthoCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.orthoCam.setToOrtho(false);
        
        this.lights = new ArrayList<Light>();
        this.mouseLight = new Light(0, 0, Color.WHITE);
    }

    @Override
	public void render() {
    	Gdx.gl.glClearColor(0, 0, 0, 0);
    	
    	float mx = Gdx.input.getX();
    	float my = Gdx.graphics.getHeight() - Gdx.input.getY();
    	
    	mouseLight.x = mx;
    	mouseLight.y = my;
    	
    	if (Gdx.input.isKeyJustPressed(Keys.A)) {
    		lights.add(new Light(mx, my, new Color((float)Math.random(),(float)Math.random(),(float)Math.random(), .5f)));
    	}
    
    	for (Light light : lights) {
    		renderLight(light);
    	}
    	
    	renderLight(mouseLight);
    
    	log.info("Camera coords: "+ orthoCam.position.x+"/"+ orthoCam.position.y);
    	
    	beginRender();  {
    		for (MapLayer layer : map.getLayers()) {
    			if (layer.isVisible()) {
    				if (layer instanceof TiledMapTileLayer) {
    					renderTileLayer((TiledMapTileLayer)layer);
    				} if (layer instanceof TiledMapImageLayer) {
    					renderImageLayer((TiledMapImageLayer)layer);
    				} else {
    					renderObjects(layer);
    				}
    			}
    		}
    	} endRender();
    }
    
    @Override
    public void renderObject(MapObject object) {
        if(object instanceof TextureMapObject) {
            TextureMapObject textureObj = (TextureMapObject) object;
                batch.draw(textureObj.getTextureRegion(), textureObj.getX(), textureObj.getY());
                log.info("TextureMapObject coords: "+ textureObj.getX()+"/"+ textureObj.getY());
        }
    } 
    
    
    
    private void renderLight(Light light) {
    	float lightWidth = 600;
    	float lightHeight = 600;
    	
    	ShaderProgram shadowmapProgram = shaderRegistry.getRegisteredPrograms().get("shadowmap");
    	ShaderProgram shadowrenderProgram = shaderRegistry.getRegisteredPrograms().get("shadowrender");
    	
    	int levelIndex = 0;
    	batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
    	
    	orthoCam.setToOrtho(false, lightWidth, lightHeight);
    	orthoCam.translate(
    		light.x - lightWidth  / 2,
    		light.y - lightHeight / 2
    	);
    	orthoCam.update();
    	
    	occlusionFbo.begin(); {
        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        	    	
        	batch.setProjectionMatrix(orthoCam.combined);
        	batch.setShader(null);
        	
        	batch.begin(); {
            	for (MapLayer layer : map.getLayers()) {
            		if (levelIndex == 3) break;
            		
            		if (levelIndex == 0) {
            			levelIndex++; 
            			continue;
            		}
            		
            		levelIndex++;
     		
            		if (layer.isVisible()) {
            			if (layer instanceof TiledMapTileLayer) {
        					renderTileLayer((TiledMapTileLayer)layer);
        				} if (layer instanceof TiledMapImageLayer) {
        					renderImageLayer((TiledMapImageLayer)layer);
        				} else {
        					renderObjects(layer);
        				}
            		}
            	} 
        	} batch.end();
    	} occlusionFbo.end();
    	
    	shadowmapFbo.begin(); {    		
        	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        	
        	orthoCam.setToOrtho(false, lightWidth, 1);
    		batch.setProjectionMatrix(orthoCam.combined);
        	batch.setShader(shadowmapProgram);
    		
        	batch.begin(); {
        		shadowmapProgram.setUniformf("resolution", lightWidth, lightHeight);
        		batch.draw(occlusionFbo.getColorBufferTexture(), 0, 0, lightWidth, 1);
        	} batch.end();
    	} shadowmapFbo.end();
    	
    	orthoCam.setToOrtho(false);
    	batch.setProjectionMatrix(orthoCam.combined);
    	
    	batch.setShader(shadowrenderProgram);
		batch.setColor(light.color);
		
    	batch.begin(); {
    		shadowrenderProgram.setUniformf("resolution", lightWidth, lightHeight);
    		batch.draw(
    			shadowmapTex,
    			light.x - lightWidth  / 2, 
    			light.y - lightHeight / 2,
    			lightWidth,
    			lightHeight
    		);
    		
    	} batch.end();
    	
		batch.setColor(Color.WHITE);
    	batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    	batch.setShader(null);
    }
}
