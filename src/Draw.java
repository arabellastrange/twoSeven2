import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Draw implements MouseMotionListener, MouseListener {
	ArrayList<Rectangle> gamePieces =  new ArrayList<Rectangle>();
	ArrayList<BufferedImage> draggedPieces = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> wPieces = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> bPieces = new ArrayList<BufferedImage>();
	BufferedImage dragged;
	Point lastLoc;
	int piY;
	int piX;
	
	public Draw(JPanel panel){
		panel.add(makeABoard(), BorderLayout.CENTER);
	}
	
	public JPanel makeABoard(){
		JPanel board = null;
		 try {
		    	final BufferedImage gboard = ImageIO.read(new File("cutsomGameBoard.jpg"));
		    	final BufferedImage featPanel = ImageIO.read(new File("extraPanel.png"));
		    	
		    	//load white game pieces
		    	wPieces.add(ImageIO.read(new File("rect-lightOrange.png")));
		    	wPieces.add(ImageIO.read(new File("rect-navy.png")));
		    	wPieces.add(ImageIO.read(new File("rect-maroon.png")));	
		    	wPieces.add(ImageIO.read(new File("rect-lilac.png")));
		    	wPieces.add(ImageIO.read(new File("rect-yellow.png")));
		    	wPieces.add(ImageIO.read(new File("rect-orange.png")));
		    	wPieces.add(ImageIO.read(new File("rect-green.png")));
		    	wPieces.add(ImageIO.read(new File("rect-brown.png")));

		    	//load black game pieces
		    	bPieces.add(ImageIO.read(new File("rev-brown.png")));
		    	bPieces.add(ImageIO.read(new File("rev-green.png")));
		    	bPieces.add(ImageIO.read(new File("rev-orange.png")));
		    	bPieces.add(ImageIO.read(new File("rev-yellow.png")));
		    	bPieces.add(ImageIO.read(new File("rev-lilac.png")));
		    	bPieces.add(ImageIO.read(new File("rev-maroon.png")));
		    	bPieces.add(ImageIO.read(new File("rev-navy.png")));
		    	bPieces.add(ImageIO.read(new File("rev-lighOrange.png")));
		    	
		    	board = new JPanel(){
		            protected void paintComponent(Graphics g) {
		            	Graphics2D g2 = (Graphics2D) g;
		                super.paintComponent(g);
		                g.drawImage(gboard, 0, 0, this);
		                int x = 5;
		                int y = 5;
		                for(int i = 0; i <8; i++){
		                	g.drawImage(wPieces.get(i), x, y, this);
		                	x += 60;
		                }
		                x = 5;
		                y = 425;
		                for(int i = 0; i <8; i++){
		                	g.drawImage(bPieces.get(i), x, y, this);
		                	x += 60;
		                }
		                
		                for(int i = 0; i < draggedPieces.size()-1; i++)
		        			if(draggedPieces.get(i) != null){
		        				g.drawImage(draggedPieces.get(i), piX, piY, this);
		        		}
		                
		        		//draw rect pieces
		        		for(int i = 5; i < 480; i+= 60){	
		        			Rectangle b = new Rectangle(i, 425, 40, 40);
		        			gamePieces.add(b);
		        			g2.draw(b);
		        		}
		        		
		        		for(int i = 5; i < 480; i+= 60){
		        			Rectangle w = new Rectangle(i, 5, 40, 40);
		        			gamePieces.add(w);
		        			g2.draw(w);
		        		}
		                
		            }
		            @Override
		            public Dimension getPreferredSize() {
		                return new Dimension(480, 480);
		            }   
				};
			}
		    catch (IOException e) {
				e.printStackTrace();
			}
		return board;
		    
	}
	
	public void mouseClicked(MouseEvent e) {

		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent e) {      
        for(BufferedImage img: wPieces){
     	   for(Rectangle r: gamePieces){
         	   if(r.contains(e.getPoint())){
         		   System.out.println("in");
         		   dragged = img;
         		   //piece =  this one but to string
         		   lastLoc = e.getPoint();
         		   break;
         	   }
         	   else{
         		   System.out.println("in");
         	   }
     	   }
        }
     }
	
 	public void mouseReleased(MouseEvent e) {
 			piX = e.getX(); 
     		piY = e.getY();
     		//square = this one but to string
     		
     		//if(gameInterface().movePiece()){
     			draggedPieces.add(dragged);
     		//}
     		dragged = null;
     		lastLoc = null;
            System.out.println("release");
 		
 	}
 	
 	public void mouseDragged(MouseEvent e) {
 		System.out.println("X is: " + e.getX()); 
 		System.out.println("y is: " + e.getY());
 		Graphics g = null;
			if (dragged != null) {
				Graphics2D g2 = (Graphics2D) g;
				AffineTransform at = new AffineTransform();
				at.translate(e.getX() - lastLoc.x, e.getY() - lastLoc.y);
				lastLoc = e.getPoint();
				g2.drawImage(dragged, at, null);
				System.out.println("dragged");
			}	
 	}

}