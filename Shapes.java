import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

abstract class Shapes {

	protected int x;
	protected int y;
	protected int size;

	public Shapes(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	abstract void draw(Graphics g);

}
