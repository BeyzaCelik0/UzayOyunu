import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.print.DocFlavor.URL;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



class Ates{
	
	private int x;
	private int y;
	
	
	public Ates(int x, int y) {
		
		this.x = x;
		this.y = y;
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}
	
	
	
	
	
}
public class Oyun extends JPanel implements KeyListener, ActionListener{
	
	Timer timer = new Timer(1, this);
	
	
	
	private int gecen_sure = 0;
	private int harcanan_ates = 0;
	
	private BufferedImage image;
	
	
	private ArrayList<Ates> atesler = new ArrayList<Ates>();
	
	private int atesdirY = -2;
	private int topX = 0;
	
	private int topdirX = 2;
	
	private int roketX = 0;
	
	private int dirUzayX = 20;
	
	public boolean kontrolEt() {
		
        for(Ates ates: atesler) {
			
			if(new Rectangle(ates.getX(), ates.getY() ,10,20).intersects(new Rectangle(topX,0,20,20))) {
				return true;
				
			}
				
			}
        return false;
        
		
	}
	
	public Oyun() {
		
		setBackground(Color.BLACK);
		
		java.net.URL imageUrl = getClass().getResource("/roket.png");
		if (imageUrl == null) {
		    System.out.println("Resim bulunamadı!");
		} else {
		    System.out.println("Resim bulundu: " + imageUrl.getPath());
		}
		
		try {
		    image = ImageIO.read(new File("src/roket.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
	
		timer.start();
	}
	

	@Override
	public void paint(Graphics g) {
	    super.paint(g);

	    gecen_sure += 5;
	    
	    g.setColor(Color.red);
	    g.fillOval(topX, 0, 20, 20); // Kırmızı topu çiziyoruz

	    g.drawImage(image, roketX, 490, image.getWidth() / 10, image.getHeight() / 10, this);
	    
	    
        for(Ates ates: atesler) {
			
			if(ates.getY() < 0) {
				
				atesler.remove(ates);
			}
			
		}
        
        g.setColor(Color.BLUE);
        
        for(Ates ates: atesler) {
			
			g.fillRect(ates.getX(), ates.getY(), 10, 20);
			
		}
        
        if(kontrolEt()) {
        	timer.stop();
        	
        	String message = "Kazandınız...\n"+
        	                 "Harcanan Ateş : " + harcanan_ates +
        	                 "\n Geçen Süre : " + gecen_sure/1000.0 + " saniye" ;
        	
        	JOptionPane.showMessageDialog(this,message);
        	
        	System.exit(0);
	}
	
	}

	
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(Ates ates: atesler) {
			
			ates.setY(ates.getY()+ atesdirY);
			
		}
		
		topX += topdirX;
		
		if (topX >= getWidth() - 20) { 
	        topdirX = -topdirX;
	    }
	    
	    if (topX <= 0) { 
	        topdirX = -topdirX;
	    }
        repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
		
		if(c == KeyEvent.VK_LEFT) {
			
			if(roketX <= 0) {
				
				roketX = 0;
			}
			
			else {
				
				roketX -= dirUzayX;
			}
			
			
		}
		else if(c == KeyEvent.VK_RIGHT) {
            if(roketX >= 750) {
				
				roketX = 750;
			}
			
			else {
				
				roketX += dirUzayX;
			}
		}
		else if(c == KeyEvent.VK_CONTROL) {
			
			atesler.add(new Ates(roketX+15,470));
			
			harcanan_ates ++;
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

}
