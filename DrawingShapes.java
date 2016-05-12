import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

class DrawingShapes extends JFrame {

	public static void main(String[] args) {
		DrawingShapes app = new DrawingShapes();
	}

	public DrawingShapes() {
		super("Drawing Shapes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ButtonPanel buttons = new ButtonPanel();
		ShapesPanel pane = new ShapesPanel(buttons);
		setLayout(new BorderLayout());
		add(buttons, BorderLayout.SOUTH);
		add(pane, BorderLayout.CENTER);
		pack();

		setVisible(true);
	}
}

class ButtonPanel extends JPanel implements ActionListener {
	private JRadioButton circleButton = new JRadioButton("Circle");
	private JRadioButton squareButton = new JRadioButton("Square");
	private JRadioButton triangleButton = new JRadioButton("Triangle");
	private JRadioButton smileyButton = new JRadioButton("Smiley Face");
	private JRadioButton rectangleButton = new JRadioButton("Rectangle");

	private JSpinner sizeSpinner = new JSpinner(new SpinnerNumberModel(100,
                                              1, 200, 10));
	public static final int CIRCLE_MODE = 0;
	public static final int SQUARE_MODE = 1;
	public static final int TRIANGLE_MODE = 2;
	public static final int SMILEY_MODE = 3;
	public static final int RECTANGLE_MODE = 4;

	private int mode = CIRCLE_MODE;

	public ButtonPanel() {

		setLayout(new GridLayout(4, 2));

		ButtonGroup group = new ButtonGroup();
		circleButton.setSelected(true);
		group.add(circleButton);
		group.add(squareButton);
		group.add(triangleButton);
		group.add(smileyButton);
		group.add(rectangleButton);

		circleButton.addActionListener(this);
		squareButton.addActionListener(this);
		triangleButton.addActionListener(this);
		smileyButton.addActionListener(this);
		rectangleButton.addActionListener(this);

		add(circleButton);
		add(squareButton);
		add(triangleButton);
		add(smileyButton);
		add(rectangleButton);

		JPanel sizePanel = new JPanel();
		JLabel sizeLabel = new JLabel("Size");
        sizePanel.add(sizeLabel);
        sizeLabel.setLabelFor(sizeSpinner);
        sizePanel.add(sizeSpinner);

		add(sizePanel);
	}

	public void actionPerformed(ActionEvent event) {

		if(event.getSource() == circleButton) {
			mode = CIRCLE_MODE;
		}
		else if(event.getSource() == squareButton) {
			mode = SQUARE_MODE;
		}
		else if(event.getSource() == triangleButton) {
			mode = TRIANGLE_MODE;
		}
		else if(event.getSource() == smileyButton) {
			mode = SMILEY_MODE;
		}
		else if(event.getSource() == rectangleButton) {
			mode = RECTANGLE_MODE;
		}
	}

	public int getShapeSize() {
		return (int)((SpinnerNumberModel)sizeSpinner.getModel()).getNumber();
	}

	public int getMode() {
		return mode;
	}
}

class ShapesPanel extends JPanel {

	private ArrayList<Shapes> shapes = new ArrayList<>();

	private ButtonPanel bp;

	public ShapesPanel(ButtonPanel panel) {
		bp = panel;
		addMouseListener(new DrawingShapesMouseListener());
		setOpaque(true);
		setBackground(Color.lightGray);
	}

	private class DrawingShapesMouseListener extends MouseAdapter
	{
		public void mouseClicked(MouseEvent source) {

			if(bp.getMode() == ButtonPanel.CIRCLE_MODE) {
				shapes.add(new Circle(source.getX(), source.getY(), bp.getShapeSize()));
			}
			else if(bp.getMode() == ButtonPanel.SQUARE_MODE) {
				shapes.add(new Square(source.getX(), source.getY(), bp.getShapeSize()));
			}
			else if(bp.getMode() == ButtonPanel.TRIANGLE_MODE) {
				shapes.add(new Triangle(source.getX(), source.getY(), bp.getShapeSize()));
			}
			else if(bp.getMode() == ButtonPanel.SMILEY_MODE) {
				shapes.add(new Smiley(source.getX(), source.getY(), bp.getShapeSize()));
			}
			else if(bp.getMode() == ButtonPanel.RECTANGLE_MODE) {
				shapes.add(new Rectangle(source.getX(), source.getY(), bp.getShapeSize()));
			}

			repaint();
		}
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		for(Shapes shape: shapes) {
			shape.draw(g);
		}
	}

	public Dimension getPreferredSize()
	{
		return new Dimension(300, 300);
	}
}

class Circle extends Shapes {

	public Circle(int x, int y, int size) {
		super(x, y, size);
	}

	public void draw(Graphics g) {
		g.drawOval(x-size/2,y-size/2,size,size);
	}
}

class Square extends Shapes{

	public Square(int x, int y, int size) {
		super(x, y, size);
	}

	public void draw(Graphics g) {
		g.drawRect(x-size/2, y-size/2, size, size);
	}
}

class Triangle extends Shapes {

	private int[] arrayX = {x, x+size/2, x-size/2};
	private int[] arrayY = {y-size/2, y+size/2, y+size/2};

	public Triangle(int x, int y, int size) {
		super(x, y, size);
	}

	public void draw(Graphics g) {
		g.drawPolygon(arrayX, arrayY, 3);
	}
}

class Rectangle extends Shapes{

	public Rectangle(int x, int y, int size) {
		super(x, y, size);
	}

	public void draw(Graphics g) {
		g.drawRect(x-size/2, y-size/2, size, size/2);
	}
}

class Smiley extends Shapes {

	public Smiley(int x, int y, int size) {
		super(x, y, size);
	}

	public void draw(Graphics g) {
		g.drawOval(x-size/2, y-size/2, size, size);
		g.drawOval(x-size/3, y-size/3, size/4, size/4);
		g.drawOval(x+size/12, y-size/3, size/4, size/4);
		g.drawArc(x-size/4, y-size/10, size/2, size/2, 180, 180);
	}
}
