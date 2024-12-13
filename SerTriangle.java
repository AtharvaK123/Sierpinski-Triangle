import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class SerTriangle extends JPanel
{
	JFrame frame;

	ArrayList<Point> pointList = new ArrayList<Point>();

	Polygon triangle;
	Point lastPoint, top, bottomRight, bottomLeft;

	public SerTriangle()
	{
		frame = new JFrame("Sierpinski Triangle Generator");
		frame.setSize(2100, 800);
		frame.add(this);



		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		startTriangle(g);
	}


	public void startTriangle(Graphics g)
	{
		top = new Point(this.getWidth()/2, 100, Color.RED);
		bottomRight = new Point(100, this.getHeight()-100, Color.BLUE);
		bottomLeft = new Point(this.getWidth()-100, this.getHeight()-100, Color.GREEN);

		pointList.add(top);
		pointList.add(bottomRight);
		pointList.add(bottomLeft);

		int[] xValues = {top.getX(), bottomRight.getX(), bottomLeft.getX()};
		int[] yValues = {top.getY(), bottomRight.getY(), bottomLeft.getY()};
		triangle=new Polygon(xValues,yValues,3);
		//g.drawPolygon(xValues, yValues, 3);

		g.setColor(top.getColor());
		g.fillOval(top.getX(), top.getY(), 5, 5);
		g.setColor(bottomRight.getColor());
		g.fillOval(bottomRight.getX(), bottomRight.getY(), 5, 5);
		g.setColor(bottomLeft.getColor());
		g.fillOval(bottomLeft.getX(), bottomLeft.getY(), 5, 5);
		doTriangle(g);
	}

	public void doTriangle(Graphics g)
	{
		int random = (int)(Math.random()*3)+1;

		int randomX = (int)(Math.random()*(this.getWidth()-100))+(this.getWidth()+100);
		int randomY = (int)(Math.random()*(this.getHeight()-100))+(this.getHeight()+100);

		while((!triangle.contains(randomX, randomY)))
		{
			randomX = (int)(Math.random()*(this.getWidth()-100))+1;
			randomY = (int)(Math.random()*(this.getHeight()-100))+1;
		}

		Point newPoint = new Point(randomX, randomY, Color.RED);//Change Color
		g.setColor(newPoint.getColor());
		g.fillOval(newPoint.getX(), newPoint.getY(), 5, 5);
		pointList.add(newPoint);
		lastPoint = newPoint;


		int ySlope, xSlope, newY, newX;
		switch(random)
		{
			case 1:
				ySlope = (top.getY() - lastPoint.getY());
				xSlope = (top.getX() - lastPoint.getX());

				newY = top.getY() + (Math.abs(ySlope/2));
				newX = top.getX() + (Math.abs(xSlope/2));

				g.fillOval(newX, newY, 5, 5);
				break;

			case 2:
				ySlope = (bottomRight.getY() - lastPoint.getY());
				xSlope = (bottomRight.getX() - lastPoint.getX());

				newY = bottomRight.getY() + (Math.abs(ySlope/2));
				newX = bottomRight.getX() + (Math.abs(xSlope/2));

				g.fillOval(newX, newY, 5, 5);
				break;

			case 3:
				ySlope = (bottomLeft.getY() - lastPoint.getY());
				xSlope = (bottomLeft.getX() - lastPoint.getX());

				newY = bottomLeft.getY() + (Math.abs(ySlope/2));
				newX = bottomLeft.getX() + (Math.abs(xSlope/2));

				g.fillOval(newX, newY, 5, 5);
				break;
		}
	}

	public class Point
	{
		int xPos, yPos;
		Color color;

		public Point(int x, int y, Color col)
		{
			xPos = x;
			yPos = y;
			color = col;
		}

		public int getX()
		{
			return xPos;
		}
		public int getY()
		{
			return yPos;
		}
		public Color getColor()
		{
			return color;
		}
	}

	public static void main (String[]args)
	{
		int random = (int)(Math.random()*3)+1;
		System.out.println(random);
		SerTriangle sT = new SerTriangle();
	}
}