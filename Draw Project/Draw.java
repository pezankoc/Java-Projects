/******************************************************************
 *  COURSE:             CSC231 Computer Science and Programming II
 *	Lab:			    Number 4
 *	FILE:				Draw.java
 *	TARGET:				Java 6.0 and 7.0
 *****************************************************************/

// Import Core Java packages
import java.awt.*;
import java.awt.event.*;

public class Draw extends Frame implements ActionListener, ItemListener {

	// Initial Frame size
	static final int WIDTH = 400;                // frame width
	static final int HEIGHT = 300;               // frame height

    // Color choices
    static final String COLOR_NAMES[] = {"None", "Red", "Blue", "Green"};
    static final Color COLORS[] = {null, Color.red, Color.blue, Color.green};

    // Button control
    Button circle;
    Button roundRec;
    Button threeDRec;
    Button line;
    Button square;
    Button oval;
    Button clear;
    Button reset;

    // Color choice box
    Choice colorChoice;

    // the canvas
    DrawCanvas canvas;

    /**
     * Constructor
     */
	public Draw() {
	    super("Java Draw");
        setLayout(new BorderLayout());

        // create panel for controls
        Panel topPanel = new Panel(new GridLayout(3, 1));
        add(topPanel, BorderLayout.NORTH);
               
        // create button control
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(buttonPanel);

        circle = new Button("Circle");
        buttonPanel.add(circle);
        roundRec = new Button("Rounded Rectangle");
        buttonPanel.add(roundRec);
        threeDRec = new Button("3D Rectangle");
        buttonPanel.add(threeDRec);

        // add button listener
        circle.addActionListener(this);
        roundRec.addActionListener(this);
        threeDRec.addActionListener(this);
        
        // create second button control
        Panel secondPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(secondPanel);
        
        line = new Button("Line");
        secondPanel.add(line);
        square = new Button("Square");
        secondPanel.add(square);
        oval = new Button("Oval");
        secondPanel.add(oval);
        
        // add button listener
        line.addActionListener(this);
        square.addActionListener(this);
        oval.addActionListener(this);
        
        // create panel for color choices
        Panel colorPanel = new Panel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(colorPanel);
        Label label = new Label("Filled Color:");
        colorPanel.add(label);
        colorChoice = new Choice();
        for(int i=0; i<COLOR_NAMES.length; i++) {
            colorChoice.add(COLOR_NAMES[i]);
        }
        
        // add buttons to color panel
        colorPanel.add(colorChoice);
        clear = new Button("Clear");
        colorPanel.add(clear);
        reset = new Button("Reset");
        colorPanel.add(reset);
        
        // add listeners to color panel
       colorChoice.addItemListener(this);
       clear.addActionListener(this);
       reset.addActionListener(this);       
        
        // create the canvas
        canvas = new DrawCanvas();
        add(canvas, BorderLayout.CENTER);
	} // end of constructor


    /**
     *  Implementing ActionListener
     */
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == circle) {  // circle button
        	canvas.setShape(DrawCanvas.CIRCLE);
        }
        else if(event.getSource() == roundRec) {  // rounded rectangle button
            canvas.setShape(DrawCanvas.ROUNDED_RECTANGLE);
        }
        else if(event.getSource() == threeDRec) { // 3D rectangle button
            canvas.setShape(DrawCanvas.RECTANGLE_3D);
        }
        else if(event.getSource() == line) {	// Line button
        	canvas.setShape(DrawCanvas.LINE);
        }
        else if(event.getSource() == square) {	// Square button
        	canvas.setShape(DrawCanvas.SQUARE);
        }
        else if(event.getSource() == oval) {	// Oval button
        	canvas.setShape(DrawCanvas.OVAL);
        }
        else if(event.getSource() == clear) {	// clear button
        	canvas.hideCanvas();
       }
        else if(event.getSource() == reset) {	//reset button
        	remove(canvas);    
        	canvas = new DrawCanvas();
        	add(canvas, BorderLayout.CENTER);
        	canvas.setShape(DrawCanvas.CIRCLE);
        	colorChoice.select(0);
        }
    }

    /**
     * Implementing ItemListener
     */
    public void itemStateChanged(ItemEvent event) {
        Color color = COLORS[colorChoice.getSelectedIndex()];
        canvas.setFilledColor(color);
    }

    /**
     * the main method
     */
    public static void main(String[] argv) {
        // Create a frame
        Draw frame = new Draw();
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocation(150, 100);

        // add window closing listener
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });

        // Show the frame
        frame.setVisible(true);
    }
}
