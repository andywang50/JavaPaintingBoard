/*
 * Guoan Wang     PIC 20A JAVA With Applications
 * ID: 004268976  Spring 2016 
 * Email: andywang50@ucla.edu Assignment Extra Credit1
 * Section: sec 1B  
 * Honesty Pledge: 
 * I, Guoan Wang, pledge that this is my own independent work, 
 * which conforms to the guidelines of academic honesty as described in the course
 * syllabus. 
 * List of known bugs: 
 */ 

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Enumeration;

/**
 * This is the painting class which extends the JFrame Class.
 * It serves as the main frame in the project.
 * @author AndyWang
 * @version 1.0
 */
public class Painting extends JFrame{

	private static final long serialVersionUID = -948469007732308747L;
	private JPanel east = new JPanel(new GridLayout(0,1,0,500));
	
	private JPanel DrawModeOptions = new JPanel();
	private ButtonGroup DrawModeGroup = new ButtonGroup();
	private ButtonGroup DrawColorGroup = new ButtonGroup();
	private ButtonGroup FillColorGroup = new ButtonGroup();
	
	private Checkbox FillCB = new Checkbox("Fill color");
	private JRadioButton DrawOvalRBtn = new JRadioButton("Oval");
	private JRadioButton DrawRectRBtn = new JRadioButton("Rectangle");
	private JRadioButton DrawLineRBtn = new JRadioButton("Line");
	private JRadioButton FreeDrawRBtn = new JRadioButton("FreeDraw");
	private JRadioButton EraserRBtn = new JRadioButton("Eraser");
	
	private JPanel DrawColorOptions = new JPanel(new GridLayout(0,2));
	private JPanel CustomColorPanel1 = new JPanel(new GridLayout(1,3,0,0));
	private JPanel RPanel1 = new JPanel(new FlowLayout());
	private JPanel GPanel1 = new JPanel(new FlowLayout());
	private JPanel BPanel1 = new JPanel(new FlowLayout());
	private JLabel RLabel1 = new JLabel("R");
	private JLabel GLabel1 = new JLabel("G");
	private JLabel BLabel1 = new JLabel("B");
	private JScrollBar RChooser1 = new JScrollBar(Scrollbar.VERTICAL,0,1,0,256);
	private JScrollBar GChooser1 = new JScrollBar(Scrollbar.VERTICAL,0,1,0,256);
	private JScrollBar BChooser1 = new JScrollBar(Scrollbar.VERTICAL,0,1,0,256);	
	private JTextField RBox1 = new JTextField(3);
	private JTextField GBox1 = new JTextField(3);
	private JTextField BBox1 = new JTextField(3);
	private JPanel BtnPanel1 = new JPanel(new GridLayout( 6,1,0,10));		
	private JLabel IndicatorLabel1 = new JLabel("Current Color: ");
	private JPanel IndicatorPanel1 = new JPanel();
	private JButton SetAsDrawColorBtn = new JButton("Set As DrawColor");
	private JButton SaveColorBtn = new JButton("Save this Color");
	
	private JPanel FillColorOptions = new JPanel(new GridLayout(0,2));
	private JPanel CustomColorPanel2 = new JPanel(new GridLayout(1,3,10,0));
	private JPanel RPanel2 = new JPanel(new FlowLayout());
	private JPanel GPanel2 = new JPanel(new FlowLayout());
	private JPanel BPanel2 = new JPanel(new FlowLayout());
	private JLabel RLabel2 = new JLabel("R");
	private JLabel GLabel2 = new JLabel("G");
	private JLabel BLabel2 = new JLabel("B");
	private JScrollBar RChooser2 = new JScrollBar(Scrollbar.VERTICAL,0,1,0,256);
	private JScrollBar GChooser2 = new JScrollBar(Scrollbar.VERTICAL,0,1,0,256);
	private JScrollBar BChooser2 = new JScrollBar(Scrollbar.VERTICAL,0,1,0,256);	
	private JTextField RBox2 = new JTextField(3);
	private JTextField GBox2 = new JTextField(3);
	private JTextField BBox2 = new JTextField(3);
	private JButton SetAsFillColorBtn = new JButton("Set As FillColor");
	private JPanel BtnPanel2 = new JPanel(new GridLayout( 6,1,0,10));		
	private JLabel IndicatorLabel2 = new JLabel("Current Color: ");
	private JPanel IndicatorPanel2 = new JPanel();
	private JButton SaveColorBtn2 = new JButton("Save this Color");

	private JPanel west = new JPanel(new GridLayout(1,2,10,0));
	private JPanel west1 = new JPanel(new GridLayout(0,1,10,30));
	private JPanel west2 = new JPanel(new GridLayout(0,1,10,30));
	private JPanel ScrollBarPanel = new JPanel();
	private JScrollBar WidthChooser = new JScrollBar(Scrollbar.VERTICAL,10,1,1,30);
	private JLabel widthLabel1 = new JLabel("Width:");
	private JLabel widthLabel2 = new JLabel( Integer.toString(WidthChooser.getValue()));
	private JPanel WidthIndicatorPanel = new JPanel();
	private Canvas canvas;
	
	private JPanel center = new JPanel();
	private JScrollPane canvasScrollPane = new JScrollPane(center);

	private JMenuBar mainMenuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenuItem openItem = new JMenuItem();
	private JMenuItem saveItem = new JMenuItem();
	private JMenuItem exitItem = new JMenuItem();
	

	/**
	 * Add one color option to the color-selection area on the left.
	 * @param name the name of the color, which will appear on the panel.
	 * @param color specifying the color in the Color Class
	 */
	private void addColorOption(String name, Color color){
		JRadioButton ColorBtn = new JRadioButton(name);
		ColorBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				canvas.myShape.currentColor = color;
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				RChooser1.setValue(r);
				GChooser1.setValue(g);
				BChooser1.setValue(b);
				

			}
		});
		JRadioButton FillColorBtn = new JRadioButton(name);
		FillColorBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				canvas.myShape.currentFillColor = color;
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				RChooser2.setValue(r);
				GChooser2.setValue(g);
				BChooser2.setValue(b);
			}
		});
		DrawColorOptions.add(ColorBtn);
		FillColorOptions.add(FillColorBtn);
		DrawColorGroup.add(ColorBtn);
		FillColorGroup.add(FillColorBtn);
	}
	
	/**
	 * Add one color option to the color-selection area on the left. 
	 * Then, specify whether this color option is selected as drawing color, 
	 * and filling color, by default.
	 * @param name the name of the color, which will appear on the panel.
	 * @param color specifying the color in the Color Class
	 * @param ColorSelected Whether the color is the default drawing color.
	 * @param FillColorSelected  Whether the color is the default filling color.
	 */
	private void addColorOption(String name, 
			Color color, boolean ColorSelected, boolean FillColorSelected){
		JRadioButton ColorBtn = new JRadioButton(name);
		ColorBtn.setSelected(ColorSelected);
		ColorBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				canvas.myShape.currentColor = color;
			}
		});
		JRadioButton FillColorBtn = new JRadioButton(name);
		FillColorBtn.setSelected(FillColorSelected);
		FillColorBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				canvas.myShape.currentFillColor = color;
			}
		});
		DrawColorOptions.add(ColorBtn);
		FillColorOptions.add(FillColorBtn);
		DrawColorGroup.add(ColorBtn);
		FillColorGroup.add(FillColorBtn);
	}
	
	/**
	 * Creating and showing the shape, color options panel on the right.
	 */
	private void initializeDrawingOptionPanels(){
		DrawModeOptions.setLayout(new GridLayout(0,2));
		
		JLabel label1 = new JLabel("Draw  ");
		JLabel label2 = new JLabel("Mode:" );
		label1.setFont(new Font("Verdana",Font.PLAIN,28));
		label2.setFont(new Font("Verdana",Font.PLAIN,28));
	
		
		DrawModeOptions.add(label1);
		DrawModeOptions.add(label2);
		DrawOvalRBtn.setSelected(false);
		DrawOvalRBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (DrawOvalRBtn.isSelected()) 
					FillCB.setEnabled(true);
					SetEnableColorGroup(true);
					if (FillCB.getState()){
						SetEnableFillColorGroup(true);
					}
					else{
						SetEnableFillColorGroup(false);
					}
					canvas.myShape.currentShape = Shape.DRAW_OVAL;
			}
			
		});		
	
		DrawRectRBtn.addActionListener(new ActionListener(){	
			@Override
			public void actionPerformed(ActionEvent e) {
				if (DrawRectRBtn.isSelected()) 
					FillCB.setEnabled(true);
					SetEnableColorGroup(true);
					if (FillCB.getState()){
						SetEnableFillColorGroup(true);
					}
					else{
						SetEnableFillColorGroup(false);
					}					
					canvas.myShape.currentShape = Shape.DRAW_RECT;
			}
			
		});
		DrawLineRBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (DrawLineRBtn.isSelected()) 
					canvas.myShape.currentShape = Shape.DRAW_LINE;
					FillCB.setState(false);					
					FillCB.setEnabled(false);
					SetEnableFillColorGroup(false);
					SetEnableColorGroup(true);

			}
			
		});
		FreeDrawRBtn.setSelected(true);
		FreeDrawRBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (FreeDrawRBtn.isSelected()) 
					canvas.myShape.currentShape = Shape.FREE_DRAW;
					FillCB.setState(false);
					FillCB.setEnabled(false);
					SetEnableFillColorGroup(false);
					SetEnableColorGroup(true);
			}
			
		});
		EraserRBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (EraserRBtn.isSelected()) 
					canvas.myShape.currentShape = Shape.ERASER;
					FillCB.setEnabled(false);
					SetEnableFillColorGroup(false);
					SetEnableColorGroup(false);
			}			
		});
		DrawModeGroup.add(DrawOvalRBtn);
		DrawModeGroup.add(DrawRectRBtn);
		DrawModeGroup.add(DrawLineRBtn);
		DrawModeGroup.add(FreeDrawRBtn);
		DrawModeGroup.add(EraserRBtn);
		
		DrawModeOptions.add(FreeDrawRBtn);
		DrawModeOptions.add(DrawOvalRBtn);
		DrawModeOptions.add(DrawRectRBtn);
		DrawModeOptions.add(DrawLineRBtn);
		DrawModeOptions.add(EraserRBtn);
		
		east.setPreferredSize(new Dimension(200,1300));
	

		//east.add(label);
		east.add(DrawModeOptions);
		this.add(east,BorderLayout.EAST);
		
		initializeWidthChooserBar();
	
	}
	
	/**
	 * Creating and showing the width-Scroll-Bar panel on the right.
	 */
	private void initializeWidthChooserBar(){
		ScrollBarPanel.setLayout(new GridLayout(0,2,10,40));
		
		widthLabel1.setFont(new Font("Verdana",Font.PLAIN,15));
		widthLabel2.setFont(new Font("Verdana",Font.PLAIN,15));		
		WidthIndicatorPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.CENTER;
		gbc.weightx= 0.0;
		gbc.weighty = 0.0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		WidthIndicatorPanel.add(widthLabel1,gbc);
		gbc.gridy = 2;
		WidthIndicatorPanel.add(widthLabel2,gbc);
		ScrollBarPanel.add(WidthIndicatorPanel);
		ScrollBarPanel.add(WidthChooser);
		WidthChooser.setPreferredSize(new Dimension(50,200));
		WidthChooser.addAdjustmentListener(new AdjustmentListener(){

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				widthLabel2.setText(Integer.toString(WidthChooser.getValue()));
				canvas.myShape.width=WidthChooser.getValue();
			}
			
		});
	
	
		ScrollBarPanel.setPreferredSize(new Dimension(50,500));
		east.add(ScrollBarPanel);		
	}
	
	/**
	 * Creating and showing the canvas panel in the middle.
	 */
	private void initializeCanvas(){
		canvas = new Canvas();
		center.add(canvas);
		this.add(canvasScrollPane,BorderLayout.CENTER);
	}
	
	/**
	 * Creating and showing the menuBar.
	 */
	private void initializeMenu(){
		mainMenuBar.add(fileMenu);
		this.setJMenuBar(mainMenuBar);
		Action CreateNew = new AbstractAction("Create New"){
			private static final long serialVersionUID = -7146260145439404411L;
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/*
				 * Catch any possible input errors in which cases the input cannot be read
				 * as two integers, or the number of inputs is not enough.
				 */
				try{
					String response = JOptionPane.showInputDialog(Painting.this,
							"Give me a size separated by comma,"
							+ "eg: 800,500 means width:800px, height:500px",  "800,500");
					String[] str = response.split(",");
					int width = Integer.parseInt(str[0]);
					int height = Integer.parseInt(str[1]);
					canvas.setSize(new Dimension(width,height));
					canvas.setPreferredSize(new Dimension(width,height));
					canvas.clear();
					canvas.repaint();
					Painting.this.pack();				}
				catch(NumberFormatException err){
					JOptionPane.showMessageDialog(Painting.this, "cannot be parsed into int", 
							"alert", JOptionPane.ERROR_MESSAGE);
				}
				catch(ArrayIndexOutOfBoundsException err){
					JOptionPane.showMessageDialog(Painting.this,
							"Please enter correctly, specifying exactly two dimensions",
							"alert",JOptionPane.ERROR_MESSAGE);
				}
				catch(NullPointerException err){
					JOptionPane.showMessageDialog(Painting.this,"You clicked cancel",
							"alert",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		};
		
		
		fileMenu.add(openItem);
		
		Action Save = new AbstractAction("Save"){

			private static final long serialVersionUID = -2077564210178151418L;

			@Override
			public void actionPerformed(ActionEvent e) {
				 Point p = new Point(0, 0);
				    SwingUtilities.convertPointToScreen(p, canvas);    

				    /*
				     * Try to screenShot the current image on the canvas and save it as jpg or png.
				     * Catch possible extensions such as AWTException or file exceptions.
				     */
				    try {
				        BufferedImage awtImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);						
				        Graphics g = awtImage.getGraphics();
				        canvas.printAll(g);
				        
				        JFileChooser fileSaveChooser = new JFileChooser("Save a file");
						FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", 
								"jpg", "jpeg");						
						fileSaveChooser.setFileFilter(filter);
						fileSaveChooser.addChoosableFileFilter(new 
								FileNameExtensionFilter("PNG","png"));

						int returnVal = fileSaveChooser.showSaveDialog(fileSaveChooser);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
						    File fileToSave = fileSaveChooser.getSelectedFile();
							String ends = fileSaveChooser.getFileFilter().getDescription();
							ends = ends.toLowerCase();
							String fileName = fileToSave.getAbsolutePath().toLowerCase();
							
							/*
							 * If the user does not put extension at the end of the file name,
							 * automatically create one.
							 */
							if (fileName.endsWith(".jpeg") || fileName.endsWith(".jpg" )||
									fileName.endsWith(".png")) {								 
								ImageIO.write( awtImage,ends,fileToSave);
							} else {
								File newFile =new File(fileToSave.getAbsolutePath() + "."+ends);
								ImageIO.write( awtImage,ends,newFile);
							}
						}
				        
				    } 
				    catch(IOException e1){
						System.out.println("Problems reading.");
					}
			
			}
			
		};

		fileMenu.add(saveItem);
		
		Action Quit = new AbstractAction("Quit"){

			private static final long serialVersionUID = 1653126690159543368L;
			@Override
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(
					    Painting.this,
					    "Are you sure you want to exit?",
					    "Exit",
					    JOptionPane.YES_NO_OPTION);
				if (n==0) System.exit(0);		
			}	
		};
		openItem.setAction(CreateNew);
		saveItem.setAction(Save);
		exitItem.setAction(Quit);
		
		fileMenu.add(exitItem);
	}
	/**
	 * Create the color chooser panel on the left.
	 */
	private void initializeColors(){
		FillColorOptions.add(FillCB);
		addColorOption("BLACK",Color.BLACK,true,true);
		addColorOption("RED",Color.RED);
		addColorOption("GREEN",Color.GREEN);
		addColorOption("BLUE",Color.BLUE);
		addColorOption("Cyan",Color.CYAN);
		addColorOption("Yellow",Color.YELLOW);
		addColorOption("Pink",Color.PINK);
		addColorOption("Magenta",Color.MAGENTA);
		addColorOption("Orange",Color.ORANGE);
		addColorOption("Gray",Color.GRAY);
		addColorOption("White",Color.WHITE);	
		SetEnableFillColorGroup(false);	
		this.initializeColorIndicatorPanel();
		this.initializeFillColorIndicatorPanel();
		this.add(west,BorderLayout.WEST);
	}
	/**
	 * Create the user-custom border-color chooser panel on the left.
	 */
	
	private void initializeColorIndicatorPanel(){
	
		west1.add(DrawColorOptions);

		RBox1.setText(Integer.toString(RChooser1.getValue()));
		
		GBox1.setText(Integer.toString(GChooser1.getValue()));
		
		BBox1.setText(Integer.toString(BChooser1.getValue()));

		KeyListener ColorBoxKeyListener = new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getSource() == RBox1){
					try{
						RChooser1.setValue(Integer.parseInt(RBox1.getText()));
					}
					catch(NumberFormatException err){
						RChooser1.setValue(0);
					}
				}
				else if (e.getSource() == GBox1){
					try{
						GChooser1.setValue(Integer.parseInt(GBox1.getText()));
					}
					catch(NumberFormatException err){
						GChooser1.setValue(0);
					}
				}
				else if (e.getSource() == BBox1){
					try{
						BChooser1.setValue(Integer.parseInt(BBox1.getText()));
					}
					catch(NumberFormatException err){
						BChooser1.setValue(0);
					}
				}
			}
			
		};
		
		RBox1.addKeyListener(ColorBoxKeyListener);
		GBox1.addKeyListener(ColorBoxKeyListener);
		BBox1.addKeyListener(ColorBoxKeyListener);

		
		RChooser1.setPreferredSize(new Dimension(30,150));
		RLabel1.setPreferredSize(new Dimension(30,50));
		RBox1.setPreferredSize(new Dimension(30,50));

		GChooser1.setPreferredSize(new Dimension(30,150));
		GLabel1.setPreferredSize(new Dimension(30,50));
		GBox1.setPreferredSize(new Dimension(30,50));
		
		BChooser1.setPreferredSize(new Dimension(30,150));
		BLabel1.setPreferredSize(new Dimension(30,50));
		BBox1.setPreferredSize(new Dimension(30,50));
		
		RPanel1.setPreferredSize(new Dimension(30,500));
		GPanel1.setPreferredSize(new Dimension(30,500));
		BPanel1.setPreferredSize(new Dimension(30,500));

		RPanel1.add(RChooser1);
		RPanel1.add(RLabel1);
		RPanel1.add(RBox1);
		
		GPanel1.add(GChooser1);
		GPanel1.add(GLabel1);
		GPanel1.add(GBox1);
		
		BPanel1.add(BChooser1);
		BPanel1.add(BLabel1);
		BPanel1.add(BBox1);
		
		
		CustomColorPanel1.add(RPanel1);
		CustomColorPanel1.add(GPanel1);
		CustomColorPanel1.add(BPanel1);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weightx= 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		CustomColorPanel1.setPreferredSize(new Dimension(150,800));
		west1.add(CustomColorPanel1);
		

		BtnPanel1.setPreferredSize(new Dimension(150,250));
		
		IndicatorLabel1.setHorizontalAlignment(JLabel.CENTER);
		IndicatorLabel1.setFont(new Font("Verdena", Font.PLAIN,20));
		IndicatorPanel1.setPreferredSize(new Dimension(150,200));
		IndicatorPanel1.setBackground(Color.BLACK);
		IndicatorPanel1.setOpaque(true);
		BtnPanel1.add(IndicatorLabel1);
		BtnPanel1.add(IndicatorPanel1);
		BtnPanel1.add(SetAsDrawColorBtn);
		BtnPanel1.add(SaveColorBtn);
		gbc.weightx= 0.0;
		gbc.weighty = 1.0;
		gbc.gridwidth=3;
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		
		west1.add(BtnPanel1);

		
		AdjustmentListener ColorAdjustmentListener = new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				int r = RChooser1.getValue();
				int g = GChooser1.getValue();
				int b = BChooser1.getValue();
				if (e.getSource()==RChooser1)
					RBox1.setText(Integer.toString(r));
				else if (e.getSource()==GChooser1)
					GBox1.setText(Integer.toString(g));
				else if (e.getSource() == BChooser1)
					BBox1.setText(Integer.toString(b));
				IndicatorPanel1.setBackground(new Color(r,g,b,255));
			}	
		};
		
		RChooser1.addAdjustmentListener(ColorAdjustmentListener);
		
		GChooser1.addAdjustmentListener(ColorAdjustmentListener);
		
		BChooser1.addAdjustmentListener(ColorAdjustmentListener);
		
		this.SetAsDrawColorBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int r = RChooser1.getValue();
				int g = GChooser1.getValue();
				int b = BChooser1.getValue();
				canvas.myShape.currentColor = new Color(r,g,b,255);
			}
			
		});
		
		this.SaveColorBtn.addActionListener(new ActionListener(){
			

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(Painting.this,
						"Give the name for this color.");
				if(name!=null && !name.equals("")){
					Color newColor = new Color(RChooser1.getValue(),
							GChooser1.getValue(),BChooser1.getValue(),255);
					addColorOption(name,newColor);
					SetEnableFillColorGroup(FillCB.getState());
					DrawColorOptions.revalidate();
					DrawColorOptions.repaint();
				}
			}
		});
		
		JLabel nameLabel = new JLabel("Border Color");
		JLabel nameLabel2 = new JLabel("Chooser");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setFont(new Font("Verdana",Font.PLAIN,20));
		nameLabel2.setHorizontalAlignment(JLabel.CENTER);
		nameLabel2.setFont(new Font("Verdana",Font.PLAIN,20));
		BtnPanel1.add(nameLabel);
		BtnPanel1.add(nameLabel2);
		
		west1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		west.add(west1);

	}
	
	/**
	 * Create the user-custom fill-color chooser panel on the left.
	 */
	private void initializeFillColorIndicatorPanel(){
		FillCB.setEnabled(false);
		
		FillCB.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent e) {
					SetEnableFillColorGroup(FillCB.getState());				
				}
			
			});
	
		this.SetEnableFillColorGroup(false);
		west2.add(FillColorOptions);
		
		RBox2.setText(Integer.toString(RChooser2.getValue()));
		
		GBox2.setText(Integer.toString(GChooser2.getValue()));
		
		BBox2.setText(Integer.toString(BChooser2.getValue()));

		KeyListener ColorBoxKeyListener = new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getSource() == RBox1){
					try{
						RChooser2.setValue(Integer.parseInt(RBox2.getText()));
					}
					catch(NumberFormatException err){
						RChooser2.setValue(0);
					}
				}
				else if (e.getSource() == GBox1){
					try{
						GChooser2.setValue(Integer.parseInt(GBox2.getText()));
					}
					catch(NumberFormatException err){
						GChooser2.setValue(0);
					}
				}
				else if (e.getSource() == BBox1){
					try{
						BChooser2.setValue(Integer.parseInt(BBox2.getText()));
					}
					catch(NumberFormatException err){
						BChooser2.setValue(0);
					}
				}
			}
			
		};
		
		RBox2.addKeyListener(ColorBoxKeyListener);
		GBox2.addKeyListener(ColorBoxKeyListener);
		BBox2.addKeyListener(ColorBoxKeyListener);

		
		RChooser2.setPreferredSize(new Dimension(30,150));
		RLabel2.setPreferredSize(new Dimension(30,50));
		RBox2.setPreferredSize(new Dimension(30,50));

		GChooser2.setPreferredSize(new Dimension(30,150));
		GLabel2.setPreferredSize(new Dimension(30,50));
		GBox2.setPreferredSize(new Dimension(30,50));
		
		BChooser2.setPreferredSize(new Dimension(30,150));
		BLabel2.setPreferredSize(new Dimension(30,50));
		BBox2.setPreferredSize(new Dimension(30,50));
		
		RPanel2.setPreferredSize(new Dimension(30,500));
		GPanel2.setPreferredSize(new Dimension(30,500));
		BPanel2.setPreferredSize(new Dimension(30,500));

		RPanel2.add(RChooser2);
		RPanel2.add(RLabel2);
		RPanel2.add(RBox2);
		
		GPanel2.add(GChooser2);
		GPanel2.add(GLabel2);
		GPanel2.add(GBox2);
		
		BPanel2.add(BChooser2);
		BPanel2.add(BLabel2);
		BPanel2.add(BBox2);
		
		
		CustomColorPanel2.add(RPanel2);
		CustomColorPanel2.add(GPanel2);
		CustomColorPanel2.add(BPanel2);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.weightx= 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		CustomColorPanel2.setPreferredSize(new Dimension(150,800));
		west2.add(CustomColorPanel2);
		

		BtnPanel2.setPreferredSize(new Dimension(150,250));
		
		IndicatorLabel2.setHorizontalAlignment(JLabel.CENTER);
		IndicatorLabel2.setFont(new Font("Verdena", Font.PLAIN,20));
		IndicatorPanel2.setPreferredSize(new Dimension(150,200));
		IndicatorPanel2.setBackground(Color.BLACK);
		IndicatorPanel2.setOpaque(true);
		BtnPanel2.add(IndicatorLabel2);
		BtnPanel2.add(IndicatorPanel2);
		BtnPanel2.add(SetAsFillColorBtn);
		BtnPanel2.add(SaveColorBtn2);
		
		JLabel nameLabel = new JLabel("Fill Color");
		JLabel nameLabel2 = new JLabel("Chooser");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setFont(new Font("Verdana",Font.PLAIN,20));
		nameLabel2.setHorizontalAlignment(JLabel.CENTER);
		nameLabel2.setFont(new Font("Verdana",Font.PLAIN,20));
		BtnPanel2.add(nameLabel);
		BtnPanel2.add(nameLabel2);
		
		gbc.weightx= 0.0;
		gbc.weighty = 1.0;
		gbc.gridwidth=3;
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		west2.add(BtnPanel2);
		
		AdjustmentListener ColorAdjustmentListener = new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				int r = RChooser2.getValue();
				int g = GChooser2.getValue();
				int b = BChooser2.getValue();
				if (e.getSource()==RChooser2)
					RBox2.setText(Integer.toString(r));
				else if (e.getSource()==GChooser2)
					GBox2.setText(Integer.toString(g));
				else if (e.getSource() == BChooser2)
					BBox2.setText(Integer.toString(b));
				IndicatorPanel2.setBackground(new Color(r,g,b,255));
			}	
		};
		
		RChooser2.addAdjustmentListener(ColorAdjustmentListener);
		
		GChooser2.addAdjustmentListener(ColorAdjustmentListener);
		
		BChooser2.addAdjustmentListener(ColorAdjustmentListener);
		
		this.SetAsFillColorBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int r = RChooser2.getValue();
				int g = GChooser2.getValue();
				int b = BChooser2.getValue();
				canvas.myShape.currentFillColor = new Color(r,g,b,255);
			}
			
		});

		this.SaveColorBtn2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog(Painting.this,
						"Give the name for this color.");
				if(name!=null && !name.equals("")){
					Color newColor = new Color(RChooser2.getValue(),
							GChooser2.getValue(),BChooser2.getValue(),255);
					addColorOption(name,newColor);
					SetEnableFillColorGroup(FillCB.getState());
					DrawColorOptions.revalidate();
					DrawColorOptions.repaint();
				}
			}
		});
		west.add(west2);
		
		west2.setBorder(BorderFactory.createLineBorder(Color.WHITE));

	}

	/**
	 * Enable or disable the filling color group. 
	 * (When you draw a line, you do not need to fill it with color).
	 * @param b specifying whether the group is enabled or disabled.
	 */
	private void SetEnableFillColorGroup(boolean b){
		Enumeration<AbstractButton> FillBtn = FillColorGroup.getElements();
		
		/*
		 * Loop over all elements in the option group, and enable/disable all of them.
		 */
		while (FillBtn.hasMoreElements()){
			AbstractButton btn = FillBtn.nextElement();
			btn.setEnabled(b);
			canvas.myShape.fillShape = b;
		}
		this.SetAsFillColorBtn.setEnabled(b);
	}
	
	/**
	 * Enable or disable the drawing color group.
	 * (When erasing, you do not need to specify the color).
	 * @param b specifying whether the group is enabled or disabled.
	 */
	private void SetEnableColorGroup(boolean b){
		Enumeration<AbstractButton> ColorBtn = DrawColorGroup.getElements();
		
		/*
		 * Loop over all elements in the option group, and enable/disable all of them.
		 */
		while (ColorBtn.hasMoreElements()){
			AbstractButton btn = ColorBtn.nextElement();
			btn.setEnabled(b);
		}
		this.SetAsDrawColorBtn.setEnabled(b);

	}
	
	/**
	 * Default Constructor of the main frame.
	 * Creates and shows the main frame, also creating the panels contained,
	 * and adds the window listener of exiting.
	 */
	Painting(){
		this.setTitle("Painting");
		this.setPreferredSize(new Dimension(1500,1000));
		this.setMinimumSize(new Dimension(1000,800));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(
					    Painting.this,
					    "Are you sure you want to exit?",
					    "Exit",
					    JOptionPane.YES_NO_OPTION);
				if (n==0) System.exit(0);		
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			
		});
		this.getContentPane().setLayout(new BorderLayout());	
		this.initializeCanvas();		
		this.initializeColors();
		this.initializeDrawingOptionPanels();						
		this.initializeMenu();			
		this.pack();
		this.setVisible(true);	

	}
	
	/**
	 * This is the main function. It initialize a new frame.
	 * @param args User can specify input via console. Not in use for this homework.
	 */
	public static void main(String[] args) {
		new Painting();
	}
}
