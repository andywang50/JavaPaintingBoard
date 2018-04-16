
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;



/**
 * This is the Canvas Class which extends JPanel.
 * It serves as the panel on which the user can draw.
 * @author AndyWang
 *
 */

public class Canvas extends JPanel{
	
	private static final long serialVersionUID = -6317546722196419457L;
	private Graphics2D g2;
	private Point startPoint = new Point();
	/**
	 * myShape is a variable which contains the current color, filling color, and drawing mode.
	 */
	Shape myShape;
	private Image img;
	private final static float dash1[] = {10.0f};
    private final static BasicStroke dashed =
        new BasicStroke(1.0f,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER,
                        10.0f, dash1, 0.0f);
	/**
	 * This is the default Constructor.
	 * It sets the size of the canvas as 1500*1200.
	 */
	public Canvas(){
		myShape = new Shape();
		setBackground(Color.WHITE);
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setPreferredSize(new Dimension(1200,1200));
	    setMinimumSize(new Dimension(600, 600));
	    /*
	     * Let the user to be able to draw freely or erase by dragging the mouse,
	     * when the corresponding option is selected.
	     */
		addMouseMotionListener(new MouseMotionListener(){			
			@Override
			public void mouseDragged(MouseEvent e) {
				switch (myShape.currentShape){
					case Shape.FREE_DRAW:{
					
						g2.setStroke(new BasicStroke((float)myShape.width));
						g2.setColor(myShape.currentColor);
						g2.drawLine(startPoint.x, startPoint.y, e.getX(), e.getY());
						startPoint.x = e.getX();
						startPoint.y = e.getY();
						repaint();
						
						break;
					}
					case Shape.ERASER:{
						g2.setStroke(new BasicStroke((float)myShape.width));
						g2.setColor(myShape.backgroundColor);
						g2.drawLine(startPoint.x, startPoint.y, e.getX(), e.getY());
						startPoint.x = e.getX();
						startPoint.y = e.getY();
						repaint();

						break;
					}
					case Shape.DRAW_OVAL:{
						Graphics g = Canvas.this.getGraphics();
						Graphics2D g3 = (Graphics2D) g;
						g3.setColor(Color.BLACK);
						
					    g3.setStroke(dashed);
						Canvas.this.repaint();
						int x1 = startPoint.x;
						int y1 = startPoint.y;
						int x2 = e.getX();
						int y2 = e.getY();
						g3.drawOval(Math.min(x1,x2),Math.min(y1,y2),
								Math.abs(x2-x1),Math.abs(y2-y1));
						break;
					}
					case Shape.DRAW_RECT:{
						Graphics g = Canvas.this.getGraphics();
						Graphics2D g3 = (Graphics2D) g;
						g3.setColor(Color.BLACK);
						
					    g3.setStroke(dashed);
						Canvas.this.repaint();
						int x1 = startPoint.x;
						int y1 = startPoint.y;
						int x2 = e.getX();
						int y2 = e.getY();
						g3.drawRect(Math.min(x1,x2),Math.min(y1,y2),
								Math.abs(x2-x1),Math.abs(y2-y1));
						break;
					}
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				
			}

			
			
		});
		
		/*
	     * Let the user to be able to draw rectangles, ovals, or lines,
	     * when the corresponding option is selected.
	     */
		addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				startPoint = new Point(e.getX(),e.getY());	
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (startPoint != null){
					int x1 = startPoint.x;
					int y1 = startPoint.y;
					int x2 = e.getX();
					int y2 = e.getY();
					if (myShape.fillShape){
						g2.setColor(myShape.currentFillColor);
						switch (myShape.currentShape){
							case Shape.DRAW_OVAL:{
								g2.fillOval(Math.min(x1,x2),Math.min(y1,y2),
										Math.abs(x2-x1),Math.abs(y2-y1));
								g2.setColor(myShape.currentColor);
								g2.setStroke(new BasicStroke((float)myShape.width));
								g2.drawOval(Math.min(x1,x2),Math.min(y1,y2),
										Math.abs(x2-x1),Math.abs(y2-y1));
								repaint();

								break;
							}
							case Shape.DRAW_RECT:{
								g2.fillRect(Math.min(x1,x2),Math.min(y1,y2),
										Math.abs(x2-x1),Math.abs(y2-y1));
								g2.setColor(myShape.currentColor);
								g2.setStroke(new BasicStroke((float)myShape.width));
								g2.drawRect(Math.min(x1,x2),Math.min(y1,y2),
										Math.abs(x2-x1),Math.abs(y2-y1));
								repaint();

								break;
							}
							
						}
					}
					else{
						g2.setColor(myShape.currentColor);
						switch (myShape.currentShape){
							case Shape.DRAW_OVAL:{
								g2.setStroke(new BasicStroke((float)myShape.width));
								g2.drawOval(Math.min(x1,x2),Math.min(y1,y2),
										Math.abs(x2-x1),Math.abs(y2-y1));
								repaint();
								break;
							}
							case Shape.DRAW_RECT:{
								g2.setStroke(new BasicStroke((float)myShape.width));
								g2.drawRect(Math.min(x1,x2),Math.min(y1,y2),
										Math.abs(x2-x1),Math.abs(y2-y1));
								repaint();
								break;
							}
							case Shape.DRAW_LINE:{
								g2.setStroke(new BasicStroke((float)myShape.width));
								g2.drawLine(x1, y1, x2, y2);
								repaint();
								break;
							}
						}
					}
					startPoint = null;
				}

				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
		
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
		});		
		}

		/**
		 * Override the paintComponent Function.
		 * It serves to memorize the graphics on the canvas. 
		 * Otherwise, when resizing the frame, all graphics will disappear.
		 * @param g the graphics field of the JPanel.
		 */
		@Override
		public void paintComponent(Graphics g){
			
			if (img == null){
				img = createImage(getSize().width,getSize().height);
				g2 = (Graphics2D) img.getGraphics();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
						RenderingHints.VALUE_ANTIALIAS_ON);
				clear();
			}
		
			g.drawImage(img,0,0,null);
		}
		
		/**
		 * Clear the canvas.
		 */
		public void clear(){
			g2.setPaint(Color.white);
			g2.fillRect(0,0,getSize().width,getSize().height);
		}
		
	}
/**
 * This class stores the shape, color, and width that the user chooses.
 * @author AndyWang
 *
 */

class Shape{
	public static final int FREE_DRAW = 0;
	public static final int DRAW_OVAL = 1;
	public static final int DRAW_RECT = 2;
	public static final int DRAW_LINE = 3;
	public static final int ERASER = 4;
	
	public int currentShape = FREE_DRAW;
	public Color currentColor = Color.BLACK;
	public boolean fillShape = false;
	public Color currentFillColor = Color.BLACK;
	public Color backgroundColor = Color.WHITE;
	public int width = 1;

	
	Shape(){
		currentShape = FREE_DRAW;
		currentColor= Color.BLACK;
		fillShape=false;
		currentFillColor = Color.BLACK;
		backgroundColor = Color.WHITE;
		width=10;
	}
}
	

	
	

